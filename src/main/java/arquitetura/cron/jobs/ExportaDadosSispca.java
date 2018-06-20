package arquitetura.cron.jobs;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.enterprise.inject.spi.CDI;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.utils.CSVUtils;
import arquitetura.utils.SispcaLogger;
import monitoramento.model.Execucao;
import monitoramento.service.ExecucaoService;
import qualitativo.model.Acao;
import qualitativo.model.Programa;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;

 

public class ExportaDadosSispca    implements Job{
	
	public static final String ftpServer = "ftp.ma.gov.br";
	public static final String user = "sisppa";
	public static final String pass = "Sisppa@2016#maranhao";
	
	public static String Diretorio = "sispca";
	
	//public static String DiretorioServidor = "/var/sispca-documents/tmp/";
	public static String DiretorioServidor = "";
	
	public static String acao = "acao_sispca_ANO.csv";
	public static String programa = "programa_sispca_ANO.csv";
	public static String meta = "elaboracao_meta_fisica_sispca_ANO.csv";
	public static String execucao = "execucao_meta_fisica_sispca_ANO.csv";

	private AcaoService acaoService;
	private ProgramaService programaService;
	private ExecucaoService execucaoService;
	private ExercicioService exercicioService;
	
	 private  Exercicio exercicio ;
	
	public ExportaDadosSispca(){
		
		acaoService 	 = CDI.current().select(AcaoService.class).get();
		programaService  = CDI.current().select(ProgramaService.class).get();
		execucaoService  = CDI.current().select(ExecucaoService.class).get();
		exercicioService = CDI.current().select(ExercicioService.class).get();
		
		Optional<Exercicio> exerc = exercicioService.exercicioVigente();
		
		if(exerc.isPresent()) {
			exercicio = exerc.get();
		} 

		
		acao 	 = acao.replace(	"ANO", exercicio.getAno().toString());
		programa = programa.replace("ANO", exercicio.getAno().toString());
		meta 	 = meta.replace(	"ANO", exercicio.getAno().toString());
		execucao = execucao.replace("ANO", exercicio.getAno().toString());
		
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		List<Acao> listAcao = acaoService.exportarBI(exercicio.getId());
		List<Programa> listPrograma = programaService.exportarBI(exercicio.getId());
		List<Acao> listMeta = acaoService.exportarBIMetas(exercicio.getId());
		List<Execucao> listExecucao =execucaoService.exportarBI(exercicio.getId());
		
		FTPClient ftpClient = conexaoFTP(ftpServer, user, pass);
		try {
			
			File arquivoAcao = new File(DiretorioServidor +acao);
			File arquivoPrograma = new File(DiretorioServidor + programa);
			File arquivoMeta = new File(DiretorioServidor + meta);
			File arquivoExecucao = new File(DiretorioServidor +execucao);
			
			CSVUtils.generateCsvFile(arquivoAcao, listAcao, Acao.class);
			CSVUtils.generateCsvFile(arquivoPrograma, listPrograma, Programa.class);
			CSVUtils.generateCsvFile(arquivoMeta, listMeta, Acao.class);
			CSVUtils.generateCsvFile(arquivoExecucao, listExecucao, Execucao.class);
			
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoAcao);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoPrograma);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoMeta);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoExecucao);
			
			SispcaLogger.logWarn("Exportação arquivos sispca com sucesso!");
			
			removeArq(arquivoAcao);
			removeArq(arquivoPrograma);
			removeArq(arquivoMeta);
			removeArq(arquivoExecucao);
 			
			ftpClient.logout();
			ftpClient.disconnect();
		} catch (IOException e) {
			SispcaLogger.logError("Erro ao exportar arquivos sispca para ftp");
			SispcaLogger.logError(e);
		}
		
		
	}
	
	@SuppressWarnings("static-access")
	public static void tranferirArquivoParaFTP(FTPClient ftpClient, String directoryName, File arquivo) {
        
		try {
			
			if(ftpClient.changeWorkingDirectory( Diretorio)){
				System.out.println("Diretório selecionado >>" + ftpClient.printWorkingDirectory());
			}
			
			ftpClient.setFileTransferMode(ftpClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			FileInputStream fis = new FileInputStream(arquivo);
			ftpClient.storeFile(arquivo.getName(), fis);
			
			SispcaLogger.logWarn("Arquivo "+ arquivo.getName()+ " transferido para ftp");
			fis.close();
		} catch (IOException e) {
			SispcaLogger.logError(e);
		}
    }
	
	public static FTPClient conexaoFTP(String ftpserver, String username, String password){
		FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpserver);
            SispcaLogger.logWarn(ftpClient.getReplyString());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                SispcaLogger.logWarn("Conexão recusada.");
                return null;
            }
            ftpClient.login(username, password);
            SispcaLogger.logWarn("Conectado servidor ftp");
        }catch (IOException e) {
        	
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException exception) {
                	SispcaLogger.logError(exception);
                }
            }
            SispcaLogger.logError(e);
        }
        return ftpClient;
	}
	
	public static void limparPastaFtp(FTPClient ftpClient, String directoryName){
		try {
			ftpClient.changeWorkingDirectory(directoryName);
			ftpClient.deleteFile(acao);
			SispcaLogger.logWarn("arquivo" + acao + " deletado.");
			ftpClient.deleteFile(programa);
			SispcaLogger.logWarn("arquivo" + programa + " deletado.");
			ftpClient.deleteFile(meta);
			SispcaLogger.logWarn("arquivo" + meta + " deletado.");
			ftpClient.deleteFile(execucao);
			SispcaLogger.logWarn("arquivo" + execucao + " deletado.");
		} catch (IOException e) {
			 SispcaLogger.logError("Erro ao deletar arquivos");
			 SispcaLogger.logError(e);
		}
	}
	
	public static void removeArq(File arquivo) {
		if(arquivo.delete()){
			SispcaLogger.logWarn(arquivo.getName() + " deletado!");
		}else{
			 SispcaLogger.logError("Falha na operação de Delete."+  arquivo.getName());
		}
	}

}

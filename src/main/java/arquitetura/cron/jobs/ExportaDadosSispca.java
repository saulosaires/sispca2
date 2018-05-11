package arquitetura.cron.jobs;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import qualitativo.model.Acao;
import qualitativo.model.Programa;

 

public class ExportaDadosSispca implements Job {
	
	public static final String ftpServer = "ftp.ma.gov.br";
	public static final String user = "sisppa";
	public static final String pass = "Sisppa@2016#maranhao";
	
	public static String Diretorio = "sispca";
	
	//public static String DiretorioServidor = "/var/sispca-documents/tmp/";
	public static String DiretorioServidor = "";
	
	public static final String acao = "acao_sispca_2017.csv";
	public static final String programa = "programa_sispca_2017.csv";
	public static final String meta = "elaboracao_meta_fisica_sispca_2017.csv";
	public static final String execucao = "execucao_meta_fisica_sispca_2017.csv";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	//public static void main(String[] args) {
		List<Acao> listAcao = new AcaoDao().retornaAcaoPorAno(2017);
		List<Programa> listPrograma = new ProgramaDao().retornaProgramas();
		List<ElaboracaoMetaFisica> listMeta = new AcaoDao().retornaMetaByAcao(2017);
		List<ExecucaoMetaFisica> listExecucao = new AcaoDao().retornaExecucaoByAno(2017);
		
		FTPClient ftpClient = conexaoFTP(ftpServer, user, pass);
		try {
			
			File arquivoAcao = new File(DiretorioServidor +acao);
			File arquivoPrograma = new File(DiretorioServidor + programa);
			File arquivoMeta = new File(DiretorioServidor + meta);
			File arquivoExecucao = new File(DiretorioServidor +execucao);
			CSVUtils.generateCsvFile(arquivoAcao, listAcao, Acao.class);
			CSVUtils.generateCsvFile(arquivoPrograma, listPrograma, Programa.class);
			CSVUtils.generateCsvFile(arquivoMeta, listMeta, ElaboracaoMetaFisica.class);
			CSVUtils.generateCsvFile(arquivoExecucao, listExecucao, ExecucaoMetaFisica.class);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoAcao);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoPrograma);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoMeta);
			tranferirArquivoParaFTP(ftpClient, Diretorio, arquivoExecucao);
			System.out.println("Exportação arquivos sispca 2017 com sucesso!");
			//System.out.println(InetAddress.getLocalHost().getHostAddress());

			removeArq(arquivoAcao);
			removeArq(arquivoPrograma);
			removeArq(arquivoMeta);
			removeArq(arquivoExecucao);
			//Para teste, remover após conferir a tranferência.
			//limparPastaFtp(ftpClient, Diretorio);
			
			ftpClient.logout();
			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao exportar arquivos sispca para ftp");
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
			
			System.out.println("Arquivo "+ arquivo.getName()+ " transferido para ftp");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static FTPClient conexaoFTP(String ftpserver, String username, String password){
		FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpserver);
            System.out.print(ftpClient.getReplyString());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                System.out.println("Conexão recusada.");
                return null;
            }
            ftpClient.login(username, password);
            System.out.println("Conectado servidor ftp");
        }catch (IOException e) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException f) {
                	f.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return ftpClient;
	}
	
	public static void limparPastaFtp(FTPClient ftpClient, String directoryName){
		try {
			ftpClient.changeWorkingDirectory(directoryName);
			ftpClient.deleteFile(acao);
			System.out.println("arquivo" + acao + " deletado.");
			ftpClient.deleteFile(programa);
			System.out.println("arquivo" + programa + " deletado.");
			ftpClient.deleteFile(meta);
			System.out.println("arquivo" + meta + " deletado.");
			ftpClient.deleteFile(execucao);
			System.out.println("arquivo" + execucao + " deletado.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao deletar arquivos");
		}
	}
	
	public static void removeArq(File arquivo) {
		if(arquivo.delete()){
			//System.out.println(arquivo.getName() + " deletado!");
		}else{
			System.out.println("Falha na operação de Delete."+  arquivo.getName());
		}
	}

}
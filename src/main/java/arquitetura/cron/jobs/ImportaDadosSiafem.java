package arquitetura.cron.jobs;

 
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Named;

import org.apache.commons.net.ftp.FTPClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.utils.MathUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.model.UnidadeGestora;
import qualitativo.service.AcaoService;
import qualitativo.service.UnidadeGestoraService;
import siafem.enums.NaturezaDespeza;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;
 
@Named
public class ImportaDadosSiafem implements Job {

	private String ARQUIVO_ORIGEM  = "E:/siafANO.txt";
	private String ARQUIVO_DESTINO = "E:/tem_siafANO.txt";
	
	private LayoutSiafemTxt MES;
	private LayoutSiafemTxt UNIDADE_GESTORA;
	private LayoutSiafemTxt UNIDADE_ORCAMENTARIA;
	private LayoutSiafemTxt PROGRAMA;
	private LayoutSiafemTxt ACAO;
	private LayoutSiafemTxt ACAO_DESCRICAO;
	private LayoutSiafemTxt PLANO_INTERNO;
	private LayoutSiafemTxt PLANO_INTERNO_DESCRICAO;
	private LayoutSiafemTxt FONTE;
	private LayoutSiafemTxt NATUREZA;
	private LayoutSiafemTxt REGIAO;
	private LayoutSiafemTxt DOTACAO_INICIAL;
	private LayoutSiafemTxt NEG_DOTACAO_INICIAL;
	private LayoutSiafemTxt DISPONIVEL;
	private LayoutSiafemTxt NEG_DISPONIVEL;
	private LayoutSiafemTxt EMPENHADO;
	private LayoutSiafemTxt NEG_EMPENHADO;
	private LayoutSiafemTxt LIQUIDADO;
	private LayoutSiafemTxt NEG_LIQUIDADO;
	private LayoutSiafemTxt PAGO;
	private LayoutSiafemTxt NEG_PAGO;
 

	 private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	 private AcaoService acaoService;
	 private UnidadeGestoraService unidadeGestoraService;
	 private ExercicioService exercicioService;
	
	 private  Exercicio exercicio ;
	 
	 BigDecimal num100 = new BigDecimal(100);
	 
	public  ImportaDadosSiafem(){
		
		fisicoFinanceiroMensalSiafemService = CDI.current().select(FisicoFinanceiroMensalSiafemService.class).get();
		acaoService 						= CDI.current().select(AcaoService.class).get();
		exercicioService 				    = CDI.current().select(ExercicioService.class).get();
		unidadeGestoraService  			    = CDI.current().select(UnidadeGestoraService.class).get();
		
		Optional<Exercicio> exerc = exercicioService.exercicioVigente();
		
		if(exerc.isPresent()) {
			exercicio = exerc.get();
		} 
		
		ARQUIVO_ORIGEM  = ARQUIVO_ORIGEM.replace("ANO", exercicio.getAno().toString());
		ARQUIVO_DESTINO = ARQUIVO_DESTINO.replace("ANO",exercicio.getAno().toString());
		
	}
	


	public void execute(JobExecutionContext context) {
 
		try {
			
			defineLayoutSiafemTxt();
			baixaSiafemTxtViaFtp(ARQUIVO_ORIGEM, ARQUIVO_DESTINO);
			importaTxtParaBancoDeDados(ARQUIVO_ORIGEM);
 			
		} catch (Exception e) {
			SispcaLogger.logError("Erro ao realizar importação SIAFEM");
			SispcaLogger.logError(e);
		}

	}

	public  void defineLayoutSiafemTxt() {
		
		List<LayoutSiafemTxt> layoutSiafemTxt = new ArrayList<>();
		
		layoutSiafemTxt.add(new LayoutSiafemTxt("mes", 					  2));
		layoutSiafemTxt.add(new LayoutSiafemTxt("unidade_gestora",		  6));
		layoutSiafemTxt.add(new LayoutSiafemTxt("unidade_orcamentaria",   5));
		layoutSiafemTxt.add(new LayoutSiafemTxt("programa",				  4));
		layoutSiafemTxt.add(new LayoutSiafemTxt("acao",					  4));
		layoutSiafemTxt.add(new LayoutSiafemTxt("acao_descricao",		  45));
		layoutSiafemTxt.add(new LayoutSiafemTxt("plano_interno",		  11));
		layoutSiafemTxt.add(new LayoutSiafemTxt("plano_interno_descricao",45));
		layoutSiafemTxt.add(new LayoutSiafemTxt("fonte",				  10));
		layoutSiafemTxt.add(new LayoutSiafemTxt("natureza",				  6));
		layoutSiafemTxt.add(new LayoutSiafemTxt("regiao", 				  2));
		layoutSiafemTxt.add(new LayoutSiafemTxt("dotacao_inicial", 		  17, true));
		layoutSiafemTxt.add(new LayoutSiafemTxt("neg_dotacao_inicial",	  1));
		layoutSiafemTxt.add(new LayoutSiafemTxt("disponivel", 			  17, true));
		layoutSiafemTxt.add(new LayoutSiafemTxt("neg_disponivel", 		  1));
		layoutSiafemTxt.add(new LayoutSiafemTxt("empenhado", 			  17, true));
		layoutSiafemTxt.add(new LayoutSiafemTxt("neg_empenhado", 		  1));
		layoutSiafemTxt.add(new LayoutSiafemTxt("liquidado", 			  17, true));
		layoutSiafemTxt.add(new LayoutSiafemTxt("neg_liquidado", 		  1));
		layoutSiafemTxt.add(new LayoutSiafemTxt("pago", 				  17, true));
		layoutSiafemTxt.add(new LayoutSiafemTxt("neg_pago", 			  1));
		

		LayoutSiafemTxt primeiraColuna = layoutSiafemTxt.get(0);

		primeiraColuna.setPosicaoInicial(0);
		primeiraColuna.setPosicaoFinal(primeiraColuna.getTamanho());

		for (int i = 1; i < layoutSiafemTxt.size(); i++) {

			LayoutSiafemTxt coluna = layoutSiafemTxt.get(i);
			coluna.setPosicaoInicial(layoutSiafemTxt.get(i - 1).getPosicaoFinal());
			coluna.setPosicaoFinal(coluna.getPosicaoInicial() + coluna.getTamanho());

		}

		MES 				    = layoutSiafemTxt.get(0);
		UNIDADE_GESTORA 		= layoutSiafemTxt.get(1);
		UNIDADE_ORCAMENTARIA 	= layoutSiafemTxt.get(2);
		PROGRAMA 				= layoutSiafemTxt.get(3);
		ACAO 					= layoutSiafemTxt.get(4);
		ACAO_DESCRICAO 			= layoutSiafemTxt.get(5);
		PLANO_INTERNO 			= layoutSiafemTxt.get(6);
		PLANO_INTERNO_DESCRICAO = layoutSiafemTxt.get(7);
		FONTE 					= layoutSiafemTxt.get(8);
		NATUREZA 			    = layoutSiafemTxt.get(9);
		REGIAO 					= layoutSiafemTxt.get(10);
		DOTACAO_INICIAL 		= layoutSiafemTxt.get(11);
		NEG_DOTACAO_INICIAL 	= layoutSiafemTxt.get(12);
		DISPONIVEL 			    = layoutSiafemTxt.get(13);
		NEG_DISPONIVEL 			= layoutSiafemTxt.get(14);
		EMPENHADO			    = layoutSiafemTxt.get(15);
		NEG_EMPENHADO		    = layoutSiafemTxt.get(16);
		LIQUIDADO				= layoutSiafemTxt.get(17);
		NEG_LIQUIDADO			= layoutSiafemTxt.get(18);
		PAGO 					= layoutSiafemTxt.get(19);
		NEG_PAGO 				= layoutSiafemTxt.get(20);
		
		
		
	}

	public void baixaSiafemTxtViaFtp(String arquivoOrigem,String arquivoTemporarioDestino) throws IOException {

		FTPClient ftp = new FTPClient();

		ftp.connect("ftp.ma.gov.br");

		ftp.login("sisppa", "Sisppa@2016#maranhao");

		FileOutputStream fos = new FileOutputStream(arquivoTemporarioDestino);

		try {
			ftp.retrieveFile(arquivoOrigem, fos);
		} catch (Exception e) {
			SispcaLogger.logError("Erro ao baixar arquivo via FTP");
			SispcaLogger.logError(e);
			
		}

	}

	public  void importaTxtParaBancoDeDados(String nomeArquivo) throws IOException {
		
		 
		List<String> list = new ArrayList<>();
		
		Path path = Paths.get(nomeArquivo);
		
		if(path==null || exercicio==null) return;
 		
 		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			
			fisicoFinanceiroMensalSiafemService.deleteByYear(exercicio.getAno());
		 
			List<FisicoFinanceiroMensalSiafem> listSiafem = new ArrayList<>();
			
			list = br.lines().collect(Collectors.toList());
	 

			list.forEach(linha -> {
					 
					try {
					
						String mes                   = linha.substring(MES.getPosicaoInicial(),					   MES.getPosicaoFinal()).trim();
						String unidadeGestoraCodigo  = linha.substring(UNIDADE_GESTORA.getPosicaoInicial(),		   UNIDADE_GESTORA.getPosicaoFinal()).trim();
						String unidadeOrcamentaria	 = linha.substring(UNIDADE_ORCAMENTARIA.getPosicaoInicial(),   UNIDADE_ORCAMENTARIA.getPosicaoFinal()).trim();
						String programa				 = linha.substring(PROGRAMA.getPosicaoInicial(),			   PROGRAMA.getPosicaoFinal()).trim();
						String acaoCodigo		     = linha.substring(ACAO.getPosicaoInicial(),				   ACAO.getPosicaoFinal()).trim();	
						String acaDescricao 	     = linha.substring(ACAO_DESCRICAO.getPosicaoInicial(),		   ACAO_DESCRICAO.getPosicaoFinal()).trim();
						String planoInterno			 = linha.substring(PLANO_INTERNO.getPosicaoInicial(),		   PLANO_INTERNO.getPosicaoFinal()).trim();
						String planoInternoDescricao = linha.substring(PLANO_INTERNO_DESCRICAO.getPosicaoInicial(),PLANO_INTERNO_DESCRICAO.getPosicaoFinal()).trim();
						String fonte			     = linha.substring(FONTE.getPosicaoInicial(),				   FONTE.getPosicaoFinal()).trim();
						String natureza				 = linha.substring(NATUREZA.getPosicaoInicial(),			   NATUREZA.getPosicaoFinal()).trim();
						String regiao			     = linha.substring(REGIAO.getPosicaoInicial(),				   REGIAO.getPosicaoFinal()).trim();
						String dotacaoInicial		 = linha.substring(DOTACAO_INICIAL.getPosicaoInicial(),		   DOTACAO_INICIAL.getPosicaoFinal()).trim();
						String negDotacaoInicial     = linha.substring(NEG_DOTACAO_INICIAL.getPosicaoInicial(),	   NEG_DOTACAO_INICIAL.getPosicaoFinal()).trim();
						String disponivel			 = linha.substring(DISPONIVEL.getPosicaoInicial(),			   DISPONIVEL.getPosicaoFinal()).trim();
						String negDisponivel		 = linha.substring(NEG_DISPONIVEL.getPosicaoInicial(),		   NEG_DISPONIVEL.getPosicaoFinal()).trim();
						String empenhado			 = linha.substring(EMPENHADO.getPosicaoInicial(),			   EMPENHADO.getPosicaoFinal()).trim();
						String negEmpenhado		     = linha.substring(NEG_EMPENHADO.getPosicaoInicial(),		   NEG_EMPENHADO.getPosicaoFinal()).trim();
						String liquidado			 = linha.substring(LIQUIDADO.getPosicaoInicial(),			   LIQUIDADO.getPosicaoFinal()).trim();
						String negLiquidado			 = linha.substring(NEG_LIQUIDADO.getPosicaoInicial(),		   NEG_LIQUIDADO.getPosicaoFinal()).trim();
						String pago					 = linha.substring(PAGO.getPosicaoInicial(),				   PAGO.getPosicaoFinal()).trim();
						String negPago				 = linha.substring(NEG_PAGO.getPosicaoInicial(),			   NEG_PAGO.getPosicaoFinal()).trim();
						
						
						BigDecimal dotInicial = new BigDecimal(dotacaoInicial);
						BigDecimal disp 	  = new BigDecimal(disponivel);
						BigDecimal emp 	      = new BigDecimal(empenhado);
						BigDecimal liq	      = new BigDecimal(liquidado);
						BigDecimal pag	      = new BigDecimal(pago);
						
						dotInicial = verificarSinal(dotInicial,negDotacaoInicial);
						disp 	   = verificarSinal(disp,negDisponivel);
						emp 	   = verificarSinal(emp,negEmpenhado);
						liq 	   = verificarSinal(liq,negLiquidado);
						pag 	   = verificarSinal(pag,negPago);
 	  
						dotInicial = MathUtils.divide(dotInicial, num100);
						disp 	   = MathUtils.divide(disp, num100);
						emp		   = MathUtils.divide(emp, num100);
						liq 	   = MathUtils.divide(liq, num100);
						pag 	   = MathUtils.divide(pag, num100);
						
						Mes m = new Mes(Long.parseLong(mes));
						 
						 Acao acao =null; 
						 List<Acao> listAcao = acaoService.buscar(acaoCodigo, unidadeOrcamentaria,programa,exercicio.getId());
	 
						 if(!listAcao.isEmpty()) {
							 acao = listAcao.get(0);
						 }else {//Senao tiver a tupla no banco, busca qualquer acao com o codigo
							 
							 listAcao = acaoService.buscar(acaoCodigo, null,null,exercicio.getId());
							 if(!listAcao.isEmpty()) {
								 acao = listAcao.get(0);
							  }//Se mesmo assim nao encontrar a acao, deixa nulo mesmo
							 
							 
						 }
						 
						 UnidadeGestora gestora =null;
						 List<UnidadeGestora> listUnidadeGestora = unidadeGestoraService.buscar(unidadeGestoraCodigo, null, null, null);
						 if(!listUnidadeGestora.isEmpty()) {
							 gestora = listUnidadeGestora.get(0);
						 }
				 
						 String naturezaDescricao= "";
						 if(!Utils.emptyParam(natureza) && natureza.length()>2) {
							 
							 
							 naturezaDescricao= NaturezaDespeza.getLabel(natureza.substring(0, 2));
						 }
						 
						 
						 Integer reg = MathUtils.parseInt(regiao);
						 
						 
						 
						 listSiafem.add(new FisicoFinanceiroMensalSiafem( m,
								 										  unidadeGestoraCodigo,
								 										  gestora,
								  										  unidadeOrcamentaria,
								  										  programa,
								  										  acaoCodigo,
																	      acaDescricao,
																	      planoInterno,
																	      planoInternoDescricao,
																	      fonte,
																	      natureza,
																	      naturezaDescricao,
																	      reg,
								  										  acao,
								  										  dotInicial,
								  										  disp,
								  										  emp,
								  										  liq,
								  										  pag,
								  										  exercicio.getAno()));
							 

						 
					 
					} catch (Exception e) {
						SispcaLogger.logError(e);
					} 
					
				});
			
			
			fisicoFinanceiroMensalSiafemService.create(listSiafem);
			
			SispcaLogger.logWarn("Importação SIAFEM realizada com sucesso");
			
		} catch (Exception e) {
			SispcaLogger.logError(e);
		} finally {
			removeArq(nomeArquivo);
			 
		}
		
	}
	
 
	
	public static void removeArq(String nomeArquivo) throws IOException {
		
		 Path path = Paths.get(nomeArquivo);
		 Files.delete(path);
		
			
	}
	
    private BigDecimal verificarSinal(BigDecimal number,String neg) {
    	
    	if(!Utils.emptyParam(neg) ) {
			return number.negate();
		}
    	
    	return number;
    }
	
	public static class LayoutSiafemTxt {

		private String nomeCampo;
		private int posicaoInicial;
		private int posicaoFinal;
		
		private int tamanho;
		private boolean verificaSinal;

		public LayoutSiafemTxt(String nomeCampo, int tamanho) {
			super();
			this.nomeCampo = nomeCampo;
			 
			
			this.tamanho = tamanho;
			this.verificaSinal = false;
		}

		public LayoutSiafemTxt(String nomeCampo, int tamanho,
				boolean verificaSinal) {
			super();
			this.nomeCampo = nomeCampo;
			this.tamanho = tamanho;
			this.verificaSinal = verificaSinal;
		}
		public String getNomeCampo() {
			return nomeCampo;
		}

		public void setNomeCampo(String nomeCampo) {
			this.nomeCampo = nomeCampo;
		}

		public int getPosicaoInicial() {
			return posicaoInicial;
		}

		public void setPosicaoInicial(int posicaoInicial) {
			this.posicaoInicial = posicaoInicial;
		}

		 

		public int getPosicaoFinal() {
			return posicaoFinal;
		}

		public void setPosicaoFinal(int posicaoFinal) {
			this.posicaoFinal = posicaoFinal;
		}


		public int getTamanho() {
			return tamanho;
		}

		public void setTamanho(int tamanho) {
			this.tamanho = tamanho;
		}

		public boolean isVerificaSinal() {
			return verificaSinal;
		}

		public void setVerificaSinal(boolean verificaSinal) {
			this.verificaSinal = verificaSinal;
		}

	}
	

}

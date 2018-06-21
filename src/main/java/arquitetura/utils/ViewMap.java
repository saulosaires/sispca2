package arquitetura.utils;

import java.util.HashMap;
import java.util.Map;

public class ViewMap  {

	public static final String    ERROR_PAGE="/sispca/error.xhtml";
	public static final String    LOGIN_PAGE="/sispca/public/login.xhtml";
	public static final String    LAND_PAGE="/sispca/";
	
	private static Map<String, String> mapLinks;

	static {

		mapLinks = new HashMap<>();

		 
		mapLinks.put("/sispca/private/index.xhtml","home"); 

		mapLinks.put("/sispca/private/administrativo/permissao/list.xhtml","permissaoAtualizar");
		
		mapLinks.put("/sispca/private/administrativo/objeto/list.xhtml","objetoListar");
		mapLinks.put("/sispca/private/administrativo/objeto/form.xhtml","objetoSalvar");
		mapLinks.put("/sispca/private/administrativo/objeto/edit.xhtml","objetoAtualizar");
		
		mapLinks.put("/sispca/private/administrativo/link/list.xhtml","linkListar");
		mapLinks.put("/sispca/private/administrativo/link/form.xhtml","linkSalvar");
		mapLinks.put("/sispca/private/administrativo/link/edit.xhtml","linkAtualizar");

		mapLinks.put("/sispca/private/administrativo/mensagem/list.xhtml","mensagemListar");
		mapLinks.put("/sispca/private/administrativo/mensagem/form.xhtml","mensagemSalvar");
		mapLinks.put("/sispca/private/administrativo/mensagem/edit.xhtml","mensagemAtualizar");
		
		mapLinks.put("/sispca/private/administrativo/exercicio/list.xhtml","administrativoExercicioListar");
		mapLinks.put("/sispca/private/administrativo/exercicio/form.xhtml","administrativoExercicioSalvar"); 
		mapLinks.put("/sispca/private/administrativo/exercicio/view.xhtml","administrativoExercicioVisualizar");
		
		mapLinks.put("/sispca/private/administrativo/ppa/list.xhtml","ppaListar");
		mapLinks.put("/sispca/private/administrativo/ppa/form.xhtml","ppaSalvar"); 

		mapLinks.put("/sispca/private/administrativo/usuario/list.xhtml",     "usuarioListar");
		mapLinks.put("/sispca/private/administrativo/usuario/form.xhtml",     "usuarioSalvar");
		mapLinks.put("/sispca/private/administrativo/usuario/edit.xhtml",     "usuarioAtualizar");
		mapLinks.put("/sispca/private/administrativo/usuario/report.xhtml",   "usuarioRelatorio");
		mapLinks.put("/sispca/private/administrativo/usuario/meusdados.xhtml","usuarioMeusDados");
		
		mapLinks.put("/sispca/private/qualitativo/acao/list.xhtml","planejamentoQualitativoAcaoListar");
		mapLinks.put("/sispca/private/qualitativo/acao/form.xhtml","planejamentoQualitativoAcaoSalvar");
		mapLinks.put("/sispca/private/qualitativo/acao/edit.xhtml","planejamentoQualitativoAcaoAtualizar");
		mapLinks.put("/sispca/private/qualitativo/acao/view.xhtml","planejamentoQualitativoAcaoVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/indicador/list.xhtml","planejamentoQualitativoIndicadorListar");
		mapLinks.put("/sispca/private/qualitativo/indicador/form.xhtml","planejamentoQualitativoIndicadorSalvar");
		mapLinks.put("/sispca/private/qualitativo/indicador/edit.xhtml","planejamentoQualitativoIndicadorAtualizar");
		mapLinks.put("/sispca/private/qualitativo/indicador/view.xhtml","planejamentoQualitativoIndicadorVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/orgao/list.xhtml","planejamentoQualitativoOrgaoListar");
		mapLinks.put("/sispca/private/qualitativo/orgao/form.xhtml","planejamentoQualitativoOrgaoSalvar");
		mapLinks.put("/sispca/private/qualitativo/orgao/edit.xhtml","planejamentoQualitativoOrgaoAtualizar");
		mapLinks.put("/sispca/private/qualitativo/orgao/view.xhtml","planejamentoQualitativoOrgaoVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/planointerno/list.xhtml","planejamentoQualitativoPlanoInternoListar");
		mapLinks.put("/sispca/private/qualitativo/planointerno/form.xhtml","planejamentoQualitativoPlanoInternoSalvar");
		mapLinks.put("/sispca/private/qualitativo/planointerno/edit.xhtml","planejamentoQualitativoPlanoInternoAtualizar");
		mapLinks.put("/sispca/private/qualitativo/planointerno/view.xhtml","planejamentoQualitativoPlanoInternoVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/programa/list.xhtml","planejamentoQualitativoProgramaListar");
		mapLinks.put("/sispca/private/qualitativo/programa/form.xhtml","planejamentoQualitativoProgramaSalvar");
		mapLinks.put("/sispca/private/qualitativo/programa/edit.xhtml","planejamentoQualitativoProgramaAtualizar");
		mapLinks.put("/sispca/private/qualitativo/programa/view.xhtml","planejamentoQualitativoProgramaVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/unidadegestora/list.xhtml","planejamentoQualitativoUnidadeGestoraListar");
		mapLinks.put("/sispca/private/qualitativo/unidadegestora/form.xhtml","planejamentoQualitativoUnidadeGestoraSalvar");
		mapLinks.put("/sispca/private/qualitativo/unidadegestora/edit.xhtml","planejamentoQualitativoUnidadeGestoraAtualizar");
		mapLinks.put("/sispca/private/qualitativo/unidadegestora/view.xhtml","planejamentoQualitativoUnidadeGestoraVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/unidademedida/list.xhtml","planejamentoQualitativoUnidadeMedidaListar");
		mapLinks.put("/sispca/private/qualitativo/unidademedida/form.xhtml","planejamentoQualitativoUnidadeMedidaSalvar");
		mapLinks.put("/sispca/private/qualitativo/unidademedida/edit.xhtml","planejamentoQualitativoUnidadeMedidaAtualizar");
		mapLinks.put("/sispca/private/qualitativo/unidademedida/view.xhtml","planejamentoQualitativoUnidadeMedidaVisualizar");
		
		mapLinks.put("/sispca/private/qualitativo/unidadeorcamentaria/list.xhtml","planejamentoQualitativoUnidadeOrcamentariaListar");
		mapLinks.put("/sispca/private/qualitativo/unidadeorcamentaria/form.xhtml","planejamentoQualitativoUnidadeOrcamentariaSalvar");
		mapLinks.put("/sispca/private/qualitativo/unidadeorcamentaria/edit.xhtml","planejamentoQualitativoUnidadeOrcamentariaAtualizar");
		mapLinks.put("/sispca/private/qualitativo/unidadeorcamentaria/view.xhtml","planejamentoQualitativoUnidadeOrcamentariaVisualizar");
		
		mapLinks.put("/sispca/private/quantitativo/fisicofinanceiro/anual/list.xhtml","planejamentoQuantitativoFisicoFinanceiroAnualListar");
		mapLinks.put("/sispca/private/quantitativo/fisicofinanceiro/anual/form.xhtml","planejamentoQuantitativoFisicoFinanceiroAnualAtualizar");
		mapLinks.put("/sispca/private/quantitativo/fisicofinanceiro/anual/view.xhtml","planejamentoQuantitativoFisicoFinanceiroAnualRelatorio");
		
		mapLinks.put("/sispca/private/quantitativo/fisicofinanceiro/mensal/list.xhtml","planejamentoQuantitativoFisicoFinanceiroMensalListar");
		mapLinks.put("/sispca/private/quantitativo/fisicofinanceiro/mensal/form.xhtml","planejamentoQuantitativoFisicoFinanceiroMensalAtualizar");
		mapLinks.put("/sispca/private/quantitativo/fisicofinanceiro/mensal/view.xhtml","planejamentoQuantitativoFisicoFinanceiroMensalRelatorio");
		
		mapLinks.put("/sispca/private/monitoramento/fisicofinanceiro/mensal/list.xhtml","monitoramentoFisicoFinanceiroMensalListar");
		mapLinks.put("/sispca/private/monitoramento/fisicofinanceiro/mensal/form.xhtml","monitoramentoFisicoFinanceiroMensalAtualizar");
		mapLinks.put("/sispca/private/monitoramento/fisicofinanceiro/mensal/view.xhtml","monitoramentoFisicoFinanceiroMensalRelatorio");
		
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/list.xhtml",					"avaliacaoSetorialProgramaListar");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/diretrizAssociada.xhtml",		"avaliacaoSetorialProgramaDiretrizAssociada");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/painelAssociado.xhtml",		"avaliacaoSetorialProgramaPainelAssociado");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/indicadorIntermediario.xhtml","avaliacaoSetorialProgramaIntermediario");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/analise.xhtml",			    "avaliacaoSetorialProgramaAnalise");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/fisicoFinanceiro.xhtml",	    "avaliacaoSetorialProgramaFisicoFinanceiro");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/resultado.xhtml",	   			"avaliacaoSetorialProgramaResultado");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/recomendacao.xhtml",	   		"avaliacaoSetorialProgramaRecomendacao");
		mapLinks.put("/sispca/private/avaliacao/setorialPrograma/report.xhtml",	   			"avaliacaoSetorialRelatorio");
		
		mapLinks.put("/sispca/private/grafico/fisicofinanceiro/list.xhtml","graficoAcompanhamentoFinanceiroOrcamento");
			
		mapLinks.put("/sispca/private/metas/atividade/list.xhtml","metasAtividadeList");
		mapLinks.put("/sispca/private/metas/atividade/view.xhtml","metasAtividadeView");
		mapLinks.put("/sispca/private/metas/atividade/form.xhtml","metasAtividadeEdit");

		mapLinks.put("/sispca/private/relatorio/menu.xhtml","relatorioCategoria");
		
		mapLinks.put("/sispca/private/relatorio/finalidadeacao/list.xhtml",       "relatorioQuantitativoFinalidadeAcao");
		mapLinks.put("/sispca/private/relatorio/planoobjetivo/list.xhtml",        "relatorioPlanejamentoQualitativoObjetivoPlanoInterno");
		mapLinks.put("/sispca/private/relatorio/planotrabalho/list.xhtml", 	   "relatorioPlanejamentoQualitativoObjetivoPlanoTrabalho");
		mapLinks.put("/sispca/private/relatorio/relatorioqualitativo/list.xhtml", "relatorioPlanejamentoQualitativoProgramasAcoes");
		
		mapLinks.put("/sispca/private/relatorio/fisicofinanceiroplanejadoano/list.xhtml",  "relatorioPlanejamentoQuantitativoPlanejadoPorAno");
		mapLinks.put("/sispca/private/relatorio/fisicofinanceiroplanejado/list.xhtml",     "relatorioPlanejamentoQuantitativoPlanejadoPorRegiao");
		mapLinks.put("/sispca/private/relatorio/fisicofinanceiroplanejadogeral/list.xhtml","relatorioPlanejamentoQuantitativoFisicoFinanceiro");
		mapLinks.put("/sispca/private/relatorio/relatorioquantitativo/list.xhtml",		    "relatorioPlanejamentoQuantitativoAnual");
		mapLinks.put("/sispca/private/relatorio/relatoriofisicofinanceiro/list.xhtml",	    "relatorioPlanejamentoQuantitativoAnualPorUO");
		
		mapLinks.put("/sispca/private/relatorio/financeirodetalhamentoacao/list.xhtml", "relatorioFisicoFinanceiroDetalhamentoAcao");
		mapLinks.put("/sispca/private/relatorio/financeironaturezadespesa/list.xhtml",  "relatorioFisicoFinanceiroNaturezaDespesa");
		mapLinks.put("/sispca/private/relatorio/relatorioplanofinanceiro/list.xhtml",   "relatorioFisicoFinanceiroPlanoInterno");
		mapLinks.put("/sispca/private/relatorio/metafisicofinanceiro/list.xhtml",  	 "relatorioFisicoFinanceiroMetaFinanceiro");
		mapLinks.put("/sispca/private/relatorio/financeiroprograma/list.xhtml",  		 "relatorioFisicoFinanceiroMetaFinanceiroPrograma");

		mapLinks.put("/sispca/private/relatorio/relatorioexecucao/list.xhtml",  		 "relatorioMonitoramentoAcompanhamentoMensal");
		
		mapLinks.put("/sispca/private/relatorio/relatoriofinanceiroacao/list.xhtml",      "relatorioGerencialTCEDespesaAcao");
		mapLinks.put("/sispca/private/relatorio/relatoriofinanceiroprogacao/list.xhtml",  "relatorioGerencialTCEDespesaPrograma");
		mapLinks.put("/sispca/private/relatorio/relatoriofinanceirougprogacao/list.xhtml","relatorioGerencialTCEDespesaUnidade");
		
		mapLinks.put("/sispca/private/relatorio/relatoriobi/view.xhtml","relatorioBIExecucaOrcamentaria");
		
	}

	private  ViewMap() {
		 throw new IllegalStateException("View Utility class");
	}
 

	public static String get(String key) {
		return mapLinks.getOrDefault(key, "");
	}
	
	public static boolean isPublic(String url) {
		return  url.equals(LAND_PAGE) || url.equals(LOGIN_PAGE);
	}
	
	
}

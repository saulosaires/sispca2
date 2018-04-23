package arquitetura.utils;

import java.util.HashMap;
import java.util.Map;

public class ViewMap  {

	public static final String    ERROR_PAGE="/sispca2/error.xhtml";
	public static final String    LOGIN_PAGE="/sispca2/public/login.xhtml";
	public static final String    LAND_PAGE="/sispca2/";
	
	private static Map<String, String> mapLinks;

	static {

		mapLinks = new HashMap<>();

		 
		mapLinks.put("/sispca2/private/index.xhtml","home"); 

		mapLinks.put("/sispca2/private/administrativo/permissao/list.xhtml","permissaoAtualizar");
		
		mapLinks.put("/sispca2/private/administrativo/objeto/list.xhtml","objetoListar");
		mapLinks.put("/sispca2/private/administrativo/objeto/form.xhtml","objetoSalvar");
		mapLinks.put("/sispca2/private/administrativo/objeto/edit.xhtml","objetoAtualizar");
		
		mapLinks.put("/sispca2/private/administrativo/link/list.xhtml","linkListar");
		mapLinks.put("/sispca2/private/administrativo/link/form.xhtml","linkSalvar");
		mapLinks.put("/sispca2/private/administrativo/link/edit.xhtml","linkAtualizar");

		mapLinks.put("/sispca2/private/administrativo/mensagem/list.xhtml","mensagemListar");
		mapLinks.put("/sispca2/private/administrativo/mensagem/form.xhtml","mensagemSalvar");
		mapLinks.put("/sispca2/private/administrativo/mensagem/edit.xhtml","mensagemAtualizar");
		
		mapLinks.put("/sispca2/private/administrativo/exercicio/list.xhtml","administrativoExercicioListar");
		mapLinks.put("/sispca2/private/administrativo/exercicio/form.xhtml","administrativoExercicioSalvar"); 
		mapLinks.put("/sispca2/private/administrativo/exercicio/view.xhtml","administrativoExercicioVisualizar");
		
		mapLinks.put("/sispca2/private/administrativo/ppa/list.xhtml","ppaListar");
		mapLinks.put("/sispca2/private/administrativo/ppa/form.xhtml","ppaSalvar"); 

		mapLinks.put("/sispca2/private/administrativo/usuario/list.xhtml",     "usuarioListar");
		mapLinks.put("/sispca2/private/administrativo/usuario/form.xhtml",     "usuarioSalvar");
		mapLinks.put("/sispca2/private/administrativo/usuario/edit.xhtml",     "usuarioAtualizar");
		mapLinks.put("/sispca2/private/administrativo/usuario/report.xhtml",   "usuarioRelatorio");
		mapLinks.put("/sispca2/private/administrativo/usuario/meusdados.xhtml","usuarioMeusDados");
		
		mapLinks.put("/sispca2/private/qualitativo/acao/list.xhtml","planejamentoQualitativoAcaoListar");
		mapLinks.put("/sispca2/private/qualitativo/acao/form.xhtml","planejamentoQualitativoAcaoSalvar");
		mapLinks.put("/sispca2/private/qualitativo/acao/edit.xhtml","planejamentoQualitativoAcaoAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/acao/view.xhtml","planejamentoQualitativoAcaoVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/indicador/list.xhtml","planejamentoQualitativoIndicadorListar");
		mapLinks.put("/sispca2/private/qualitativo/indicador/form.xhtml","planejamentoQualitativoIndicadorSalvar");
		mapLinks.put("/sispca2/private/qualitativo/indicador/edit.xhtml","planejamentoQualitativoIndicadorAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/indicador/view.xhtml","planejamentoQualitativoIndicadorVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/orgao/list.xhtml","planejamentoQualitativoOrgaoListar");
		mapLinks.put("/sispca2/private/qualitativo/orgao/form.xhtml","planejamentoQualitativoOrgaoSalvar");
		mapLinks.put("/sispca2/private/qualitativo/orgao/edit.xhtml","planejamentoQualitativoOrgaoAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/orgao/view.xhtml","planejamentoQualitativoOrgaoVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/planointerno/list.xhtml","planejamentoQualitativoPlanoInternoListar");
		mapLinks.put("/sispca2/private/qualitativo/planointerno/form.xhtml","planejamentoQualitativoPlanoInternoSalvar");
		mapLinks.put("/sispca2/private/qualitativo/planointerno/edit.xhtml","planejamentoQualitativoPlanoInternoAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/planointerno/view.xhtml","planejamentoQualitativoPlanoInternoVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/programa/list.xhtml","planejamentoQualitativoProgramaListar");
		mapLinks.put("/sispca2/private/qualitativo/programa/form.xhtml","planejamentoQualitativoProgramaSalvar");
		mapLinks.put("/sispca2/private/qualitativo/programa/edit.xhtml","planejamentoQualitativoProgramaAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/programa/view.xhtml","planejamentoQualitativoProgramaVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/unidadegestora/list.xhtml","planejamentoQualitativoUnidadeGestoraListar");
		mapLinks.put("/sispca2/private/qualitativo/unidadegestora/form.xhtml","planejamentoQualitativoUnidadeGestoraSalvar");
		mapLinks.put("/sispca2/private/qualitativo/unidadegestora/edit.xhtml","planejamentoQualitativoUnidadeGestoraAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/unidadegestora/view.xhtml","planejamentoQualitativoUnidadeGestoraVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/unidademedida/list.xhtml","planejamentoQualitativoUnidadeMedidaListar");
		mapLinks.put("/sispca2/private/qualitativo/unidademedida/form.xhtml","planejamentoQualitativoUnidadeMedidaSalvar");
		mapLinks.put("/sispca2/private/qualitativo/unidademedida/edit.xhtml","planejamentoQualitativoUnidadeMedidaAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/unidademedida/view.xhtml","planejamentoQualitativoUnidadeMedidaVisualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/unidadeorcamentaria/list.xhtml","planejamentoQualitativoUnidadeOrcamentariaListar");
		mapLinks.put("/sispca2/private/qualitativo/unidadeorcamentaria/form.xhtml","planejamentoQualitativoUnidadeOrcamentariaSalvar");
		mapLinks.put("/sispca2/private/qualitativo/unidadeorcamentaria/edit.xhtml","planejamentoQualitativoUnidadeOrcamentariaAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/unidadeorcamentaria/view.xhtml","planejamentoQualitativoUnidadeOrcamentariaVisualizar");
		
		mapLinks.put("/sispca2/private/quantitativo/fisicofinanceiro/anual/list.xhtml","planejamentoQuantitativoFisicoFinanceiroAnualListar");
		mapLinks.put("/sispca2/private/quantitativo/fisicofinanceiro/anual/form.xhtml","planejamentoQuantitativoFisicoFinanceiroAnualAtualizar");
		mapLinks.put("/sispca2/private/quantitativo/fisicofinanceiro/anual/view.xhtml","planejamentoQuantitativoFisicoFinanceiroAnualRelatorio");
		
		mapLinks.put("/sispca2/private/quantitativo/fisicofinanceiro/mensal/list.xhtml","planejamentoQuantitativoFisicoFinanceiroMensalListar");
		mapLinks.put("/sispca2/private/quantitativo/fisicofinanceiro/mensal/form.xhtml","planejamentoQuantitativoFisicoFinanceiroMensalAtualizar");
		mapLinks.put("/sispca2/private/quantitativo/fisicofinanceiro/mensal/view.xhtml","planejamentoQuantitativoFisicoFinanceiroMensalRelatorio");
		
		mapLinks.put("/sispca2/private/monitoramento/fisicofinanceiro/mensal/list.xhtml","monitoramentoFisicoFinanceiroMensalListar");
		mapLinks.put("/sispca2/private/monitoramento/fisicofinanceiro/mensal/form.xhtml","monitoramentoFisicoFinanceiroMensalAtualizar");
		mapLinks.put("/sispca2/private/monitoramento/fisicofinanceiro/mensal/view.xhtml","monitoramentoFisicoFinanceiroMensalRelatorio");
		
		mapLinks.put("/sispca2/private/avaliacao/setorialPrograma/list.xhtml","avaliacaoSetorialProgramaListar");
		mapLinks.put("/sispca2/private/avaliacao/setorialPrograma/diretrizAssociada.xhtml","avaliacaoSetorialProgramaDiretrizAssociada");

		
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

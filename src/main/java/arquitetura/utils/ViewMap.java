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
		mapLinks.put("/sispca2/private/qualitativo/acao/view.xhtml","planejamentoQualitativoAcaoVizualizar");
		
		mapLinks.put("/sispca2/private/qualitativo/indicador/list.xhtml","planejamentoQualitativoIndicadorListar");
		mapLinks.put("/sispca2/private/qualitativo/indicador/form.xhtml","planejamentoQualitativoIndicadorSalvar");
		mapLinks.put("/sispca2/private/qualitativo/indicador/edit.xhtml","planejamentoQualitativoIndicadorAtualizar");
		mapLinks.put("/sispca2/private/qualitativo/indicador/view.xhtml","planejamentoQualitativoIndicadorVizualizar");
		
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

package qualitativo.beans.acao;

import java.io.Serializable;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.model.Funcao;
import qualitativo.model.Programa;
import qualitativo.model.SubFuncao;
import qualitativo.model.TipoAcao;
import qualitativo.model.TipoCalculoMeta;
import qualitativo.model.TipoFormaImplementacao;
import qualitativo.model.TipoHorizonteTemporal;
import qualitativo.model.TipoOrcamento;
import qualitativo.model.UnidadeMedida;
import qualitativo.model.UnidadeOrcamentaria;

public class AcaoValidate implements Validate<Acao>,Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850827058229914224L;
	
	public static final String ACAO_REQUIRED 	   			= "Falha inesperada ao Salvar Ação";
	public static final String CODIGO_REQUIRED 	   			= "Código é um campo obrigatório";
	public static final String DENOMINACAO_REQUIRED 	    = "Denominação é um campo obrigatório";
	public static final String PROGRAMA_REQUIRED 	   		= "Programa é um campo obrigatório";
	public static final String FUNCAO_REQUIRED 	   		    = "Função é um campo obrigatório";
	public static final String SUBFUNCAO_REQUIRED 	        = "SubFunção é um campo obrigatório";
	public static final String UNIDADE_ORCAMENTARIA_REQUIRED= "Unidade Orçamentária é um campo obrigatório";
	public static final String DESCRICAO_REQUIRED 	   		= "Descrição é um campo obrigatório";
	public static final String UNIDADE_MEDIDA_REQUIRED      = "Unidade Medida é um campo obrigatório";
	public static final String TIPO_CALCULO_REQUIRED        = "Tipo Calculo Meta é um campo obrigatório";
	
	
 
	
	@Override
	public boolean validar(Acao acao) {

		
		if (acao == null) {
			Messages.addMessageError(ACAO_REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(acao.getCodigo())) {
			Messages.addMessageError(CODIGO_REQUIRED);
			valido= false;
		}

		if (Utils.emptyParam(acao.getDenominacao())) {
			Messages.addMessageError(DENOMINACAO_REQUIRED);
			valido= false;
		}

		if (acao.getPrograma() ==null || Utils.invalidId(acao.getPrograma().getId())) {
			Messages.addMessageError(PROGRAMA_REQUIRED);
			valido= false;
		}

		if (acao.getFuncao() ==null || Utils.invalidId(acao.getFuncao().getId())) {
			Messages.addMessageError(FUNCAO_REQUIRED);
			valido= false;
		}
		
		if (acao.getSubfuncao() ==null || Utils.invalidId(acao.getSubfuncao().getId())) {
			Messages.addMessageError(SUBFUNCAO_REQUIRED);
			valido= false;
		}
		
		if (acao.getUnidadeOrcamentaria() ==null || Utils.invalidId(acao.getUnidadeOrcamentaria().getId())) {
			Messages.addMessageError(UNIDADE_ORCAMENTARIA_REQUIRED);
			valido= false;
		}

		if (acao.getUnidadeMedida() ==null || Utils.invalidId(acao.getUnidadeMedida().getId())) {
			Messages.addMessageError(UNIDADE_MEDIDA_REQUIRED);
			valido= false;
		}

		if (acao.getTipoCalculoMeta() ==null || Utils.invalidId(acao.getTipoCalculoMeta().getId())) {
			Messages.addMessageError(TIPO_CALCULO_REQUIRED);
			valido= false;
		}
		
		
		if (Utils.emptyParam(acao.getDescricao())) {
			Messages.addMessageError(DESCRICAO_REQUIRED);
			valido= false;
		}
		
		
		
		return valido;
	}


	public Acao init(Acao acao) {
 
		if(acao.getPrograma()==null) {
			acao.setPrograma(new Programa());	
		}else {
			acao.setPrograma(new Programa(acao.getPrograma().getId()));
		}
		
		if(acao.getUnidadeOrcamentaria()==null) {
			acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());	
		}else {
			acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria(acao.getUnidadeOrcamentaria().getId()));
		}
		
		if(acao.getFuncao()==null) {
			acao.setFuncao(new Funcao());	
		}else {
			acao.setFuncao(new Funcao(acao.getFuncao().getId()));
		}
		
		if(acao.getSubfuncao()==null) {
			acao.setSubfuncao(new SubFuncao());	
		}else {
			acao.setSubfuncao(new SubFuncao(acao.getSubfuncao().getId()));
		}

		if(acao.getTipoAcao()==null) {
			acao.setTipoAcao(new TipoAcao());	
		}else {
			acao.setTipoAcao(new TipoAcao(acao.getTipoAcao().getId()));	
		}
		
		if(acao.getTipoCalculoMeta()==null) {
			acao.setTipoCalculoMeta(new TipoCalculoMeta());	
		}else {
			acao.setTipoCalculoMeta(new TipoCalculoMeta(acao.getTipoCalculoMeta().getId()));	
		}
		
		if(acao.getTipoFormaImplementacao()==null) {
			acao.setTipoFormaImplementacao(new TipoFormaImplementacao());	
		}else {
			acao.setTipoFormaImplementacao(new TipoFormaImplementacao(acao.getTipoFormaImplementacao().getId()));
		}
		
		if(acao.getTipoHorizonteTemporal()==null) {
			acao.setTipoHorizonteTemporal(new TipoHorizonteTemporal());	
		}else {
			acao.setTipoHorizonteTemporal(new TipoHorizonteTemporal(acao.getTipoHorizonteTemporal().getId()));
		}

		if(acao.getTipoOrcamento()==null) {
			acao.setTipoOrcamento(new TipoOrcamento());	
		}else {
			acao.setTipoOrcamento(new TipoOrcamento(acao.getTipoOrcamento().getId()));	
		}
		
		if(acao.getUnidadeMedida()==null) {
			acao.setUnidadeMedida(new UnidadeMedida());	
		}else {
			acao.setUnidadeMedida(new UnidadeMedida(acao.getUnidadeMedida().getId()));	
		}
	
		
		return acao;
		
	}

	public void beforePersist(Acao acao) {
		 
		if( Utils.invalidId(acao.getUnidadeMedida().getId())) {
			acao.setUnidadeMedida(null);
		}

		if( Utils.invalidId(acao.getTipoAcao().getId())) {
			acao.setTipoAcao(null);
		}

		if( Utils.invalidId(acao.getTipoHorizonteTemporal().getId())) {
			acao.setTipoHorizonteTemporal(null);
		}
		
		if( Utils.invalidId(acao.getTipoFormaImplementacao().getId())) {
			acao.setTipoFormaImplementacao(null);
		}
		
		if( Utils.invalidId(acao.getTipoOrcamento().getId())) {
			acao.setTipoOrcamento(null);
		}
		
		if( Utils.invalidId(acao.getTipoCalculoMeta().getId())) {
			acao.setTipoCalculoMeta(null);
		}
		
	}

}

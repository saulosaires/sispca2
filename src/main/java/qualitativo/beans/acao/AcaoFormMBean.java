package qualitativo.beans.acao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
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
import qualitativo.service.AcaoService;
import qualitativo.service.FuncaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.SubFuncaoService;
import qualitativo.service.TipoAcaoService;
import qualitativo.service.TipoCalculoMetaService;
import qualitativo.service.TipoFormaImplementacaoService;
import qualitativo.service.TipoHorizonteTemporalService;
import qualitativo.service.TipoOrcamentoService;
import qualitativo.service.UnidadeMedidaService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class AcaoFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_SAVE = "Falha inesperada ao tentar Salvar Ação";
	 
  
	
	private Acao acao = new Acao();
	
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<Programa> listPrograma;
	private List<Funcao> listFuncao;
	private List<SubFuncao> listSubfuncao;
	private List<UnidadeMedida> listUnidadeMedida;
	private List<TipoAcao> listTipoAcao;
	private List<TipoHorizonteTemporal> listTipoHorizonteTemporal;
	private List<TipoFormaImplementacao> listTipoFormaImplementacao;
	private List<TipoOrcamento> listTipoOrcamento;
	private List<TipoCalculoMeta>listTipoCalculoMeta;
	
	private AcaoService acaoService;
	private ExercicioService exercicioService;
	private AcaoValidate acaoValidate;
	
	@Inject
	public AcaoFormMBean(AcaoService acaoService,
						 UnidadeOrcamentariaService    unidadeOrcamentariaService,
						 ProgramaService 		       programaService,
						 FuncaoService 				   funcaoService,
						 SubFuncaoService 			   subFuncaoService,
						 UnidadeMedidaService          unidadeMediaService,
						 TipoAcaoService 			   tipoAcaoService,
						 TipoHorizonteTemporalService  tipoHorizonteTemporalService,
						 TipoFormaImplementacaoService tipoFormaImplementacaoService,
						 TipoOrcamentoService          tipoOrcamentoService,
						 ExercicioService 			   exercicioService,
						 TipoCalculoMetaService        tipoCalculoMetaService,
						 AcaoValidate 				   acaoValidate) {
		
		this.acaoService = acaoService;
		this.exercicioService =exercicioService;
		this.acaoValidate =acaoValidate;
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listUnidadeOrcamentaria    = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
		listPrograma	           = programaService.findAllOrderByDenominacao();
		listFuncao 			       = funcaoService.findAllOrderByCodigo();
		listSubfuncao 		       = subFuncaoService.findAllOrderByCodigo();
		listUnidadeMedida 	       = unidadeMediaService.findAllOrderByDecricao();
		listTipoAcao			   = tipoAcaoService.findAllOrderByDecricao();
		listTipoHorizonteTemporal  = tipoHorizonteTemporalService.findAll();
		listTipoFormaImplementacao = tipoFormaImplementacaoService.findAll();
		listTipoOrcamento 	  	   = tipoOrcamentoService.findAll();
		listTipoCalculoMeta        = tipoCalculoMetaService.findAll();
		
		
		
	}

	public String salvar() {

		try {

			if (!acaoValidate.validar(acao)) {
				return "";
			}
			
			acaoValidate.beforePersist(acao);
			
			Optional<Exercicio> ex = exercicioService.exercicioVigente();
			if(ex.isPresent())
				acao.setExercicio(ex.get());
			
			
			acaoService.create(acao);

			return "acaoQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

	public Acao getAcao() {
		return acao;
	}


	public void setAcao(Acao acao) {
		this.acao = acao;
	}


	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}


	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}


	public List<Programa> getListPrograma() {
		return listPrograma;
	}


	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}


	public List<Funcao> getListFuncao() {
		return listFuncao;
	}


	public void setListFuncao(List<Funcao> listFuncao) {
		this.listFuncao = listFuncao;
	}


	public List<SubFuncao> getListSubfuncao() {
		return listSubfuncao;
	}


	public void setListSubfuncao(List<SubFuncao> listSubfuncao) {
		this.listSubfuncao = listSubfuncao;
	}


	public List<UnidadeMedida> getListUnidadeMedida() {
		return listUnidadeMedida;
	}


	public void setListUnidadeMedida(List<UnidadeMedida> listUnidadeMedida) {
		this.listUnidadeMedida = listUnidadeMedida;
	}


	public List<TipoAcao> getListTipoAcao() {
		return listTipoAcao;
	}


	public void setListTipoAcao(List<TipoAcao> listTipoAcao) {
		this.listTipoAcao = listTipoAcao;
	}


	public List<TipoHorizonteTemporal> getListTipoHorizonteTemporal() {
		return listTipoHorizonteTemporal;
	}


	public void setListTipoHorizonteTemporal(List<TipoHorizonteTemporal> listTipoHorizonteTemporal) {
		this.listTipoHorizonteTemporal = listTipoHorizonteTemporal;
	}


	public List<TipoFormaImplementacao> getListTipoFormaImplementacao() {
		return listTipoFormaImplementacao;
	}


	public void setListTipoFormaImplementacao(List<TipoFormaImplementacao> listTipoFormaImplementacao) {
		this.listTipoFormaImplementacao = listTipoFormaImplementacao;
	}


	public List<TipoOrcamento> getListTipoOrcamento() {
		return listTipoOrcamento;
	}


	public void setListTipoOrcamento(List<TipoOrcamento> listTipoOrcamento) {
		this.listTipoOrcamento = listTipoOrcamento;
	}


	public List<TipoCalculoMeta> getListTipoCalculoMeta() {
		return listTipoCalculoMeta;
	}


	public void setListTipoCalculoMeta(List<TipoCalculoMeta> listTipoCalculoMeta) {
		this.listTipoCalculoMeta = listTipoCalculoMeta;
	}

	 
 
	
 
}

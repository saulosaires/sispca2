package qualitativo.beans.programa;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.service.PpaService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Orgao;
import qualitativo.model.Programa;
import qualitativo.model.TipoPrograma;
import qualitativo.service.OrgaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.TipoProgramaService;

@Named
@ViewScoped
public class ProgramaListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE 	  = "Falha inesperada ao tentar deletar Programa";
	public static final String SUCCESS_DELETE = "Programa deletado com Sucesso";
	public static final String FAIL_SEARCH 	  = "Falha ao pesquisar Programa";
	public static final String  NO_RECORDS	  = "Nenhum Programa retornado";
	
	private String codigo;
	private String denominacao;
	private Long orgao;
	private Long tipoPrograma;
 
	private List<TipoPrograma> listTipoPrograma;
	private List<Orgao> listOrgoes;
	
	private List<Programa> listPrograma;
 
	private Long ppaId;
	private List<Ppa> listPpa;
	
	private Long exercicioId;
	private List<Exercicio> listExercicio;
		
	private PpaService ppaService;
	private ProgramaService programaService;
 	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public ProgramaListMBean(ProgramaService programaService, 
							 PpaService ppaService,
							 TipoProgramaService tipoProgramaService, 
							 OrgaoService orgaoService) {
		
		this.ppaService 	 = ppaService;
		this.programaService = programaService;

		listTipoPrograma = tipoProgramaService.findAll();
		listOrgoes =  orgaoService.findAllOrderByDescricao();
		
		listPpa = ppaService.findAll();
		
		initPpa();
		initExercicio();

		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoVisualizar");
		
	}

	public void buscar() {

		try {
			
			listPrograma = programaService.buscar(codigo,denominacao,orgao,tipoPrograma,exercicioId,null);

			if(listPrograma.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(Programa programa) {

		try {

			programaService.delete(programa);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_DELETE);

		}

		buscar();

		return "";

	}

    public void onChangePpa() {
    	
    	if(ppaId!=null) {
    		listExercicio = ppaService.findById(ppaId).getExercicios();
    	}else {
    		exercicioId=null;
    		listExercicio=null;	
    		}
    }
	
	
	public void initPpa() {
		
		for(Ppa ppa: listPpa) {
			if(ppa.getVigente()) {
				ppaId = ppa.getId();
				break;
			}
		}
		
	}
	
	public void initExercicio() {
	
		if(ppaId!=null) {
			listExercicio = ppaService.findById(ppaId).getExercicios();
			
			for(Exercicio e: listExercicio) {
				
				if(e.getVigente()) {
					exercicioId = e.getId();
					break;
				}
				
			}
			
		}
		
	}
	
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public Long getTipoPrograma() {
		return tipoPrograma;
	}

	public void setTipoPrograma(Long tipoPrograma) {
		this.tipoPrograma = tipoPrograma;
	}

	public List<TipoPrograma> getListTipoPrograma() {
		return listTipoPrograma;
	}

	public void setListTipoPrograma(List<TipoPrograma> listTipoPrograma) {
		this.listTipoPrograma = listTipoPrograma;
	}

	public List<Orgao> getListOrgoes() {
		return listOrgoes;
	}

	public void setListOrgoes(List<Orgao> listOrgoes) {
		this.listOrgoes = listOrgoes;
	}

	public List<Programa> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}

	public boolean isAtualizar() {
		return atualizar;
	}

	public void setAtualizar(boolean atualizar) {
		this.atualizar = atualizar;
	}

	public boolean isDeletar() {
		return deletar;
	}

	public void setDeletar(boolean deletar) {
		this.deletar = deletar;
	}

	public boolean isSalvar() {
		return salvar;
	}

	public void setSalvar(boolean salvar) {
		this.salvar = salvar;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public Long getPpaId() {
		return ppaId;
	}

	public void setPpaId(Long ppaId) {
		this.ppaId = ppaId;
	}

	public List<Ppa> getListPpa() {
		return listPpa;
	}

	public void setListPpa(List<Ppa> listPpa) {
		this.listPpa = listPpa;
	}

	public Long getExercicioId() {
		return exercicioId;
	}

	public void setExercicioId(Long exercicioId) {
		this.exercicioId = exercicioId;
	}

	public List<Exercicio> getListExercicio() {
		return listExercicio;
	}

	public void setListExercicio(List<Exercicio> listExercicio) {
		this.listExercicio = listExercicio;
	}

	 
	
	
	
}

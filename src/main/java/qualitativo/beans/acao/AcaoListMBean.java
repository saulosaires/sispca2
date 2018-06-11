package qualitativo.beans.acao;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.model.Usuario;
import administrativo.service.PpaService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Acao;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class AcaoListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE = "Falha inesperada ao tentar Deletar Ação";
	public static final String SUCCESS_DELETE = "Ação deletado com sucesso";
	public static final String FAIL_SEARCH = "Falha ao pesquisar Ações";
	public static final String  ACAO_NO_RECORDS="Nenhuma Ação retornada";
	
	private String codigo;
	private String denominacao;
	private Long unidadeOrcamentariaId;
	private Long programaId;
	
	private List<Acao> listAcoes;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<Programa> listPrograma;
	
	private Long ppaId;
	private List<Ppa> listPpa;
	
	private Long exercicioId;
	private List<Exercicio> listExercicio;
	
	private PpaService ppaService;
	private AcaoService acaoService;
	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public AcaoListMBean(AcaoService acaoService,
						 UnidadeOrcamentariaService unidadeOrcamentariaService,
						 ProgramaService programaService,
						 PpaService ppaService) {
		
		this.acaoService = acaoService;
		this.ppaService = ppaService;
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listPpa = ppaService.findAll();
		
		initPpa();
		initExercicio();
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
		listPrograma	        = programaService.findAllOrderByDenominacao();
		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoAcaoAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoAcaoDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoAcaoSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoAcaoVisualizar");
	}

	public void buscar() {

		try {
			listAcoes = acaoService.buscar(codigo, denominacao, unidadeOrcamentariaId, programaId,exercicioId);

			if(listAcoes.isEmpty()) {
				Messages.addMessageWarn(ACAO_NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(Acao acao) {

		try {

		 	acaoService.delete(acao);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_DELETE);

		}

		buscar();

		return "";

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

	
    public void onChangePpa() {
    	
    	if(ppaId!=null) {
    		listExercicio = ppaService.findById(ppaId).getExercicios();
    	}else {
    		exercicioId=null;
    		listExercicio=null;	
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

	public Long getUnidadeOrcamentariaId() {
		return unidadeOrcamentariaId;
	}

	public void setUnidadeOrcamentariaId(Long unidadeOrcamentariaId) {
		this.unidadeOrcamentariaId = unidadeOrcamentariaId;
	}

	public Long getProgramaId() {
		return programaId;
	}

	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}

	public List<Acao> getListAcoes() {
		return listAcoes;
	}

	public void setListAcoes(List<Acao> listAcoes) {
		this.listAcoes = listAcoes;
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

package administrativo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class HomeMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
 
	private Integer qtdAcao;
	private Integer qtdPrograma;
	private Integer exercicioVigente;
	
	private Long unidadeGestoraId;
	private List<UnidadeGestora>listUnidadeGestora;
	
	private Long unidadeOrcamentariaId;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long acaoId;
	private List<Acao> listAcao;
	
	private AcaoService acaoService;
	
	@Inject
	public HomeMBean(AcaoService acaoService, 
					 ProgramaService programaService,
					 UnidadeGestoraService unidadeGestoraService,
					 UnidadeOrcamentariaService orcamentariaService,
					 ExercicioService exercicioService) {
		 
 
		this.acaoService=acaoService;

		
		Optional<Exercicio> exercicio = exercicioService.exercicioVigente();
		
		if(exercicio.isPresent()) {
			exercicioVigente = exercicio.get().getAno();
			
			qtdAcao = acaoService.buscarByExercicio(exercicio.get().getId()).size();
			qtdPrograma = programaService.buscar(null, null, null, null, exercicio.get().getId(), null).size();

		}
		
		listUnidadeGestora = unidadeGestoraService.findAll();
		
		listUnidadeOrcamentaria = orcamentariaService.findAll();
		
	}

	public void changeUnidadeGestora() {
		
	}
	
	public void changeUnidadeOrcamentaria() {
		
		if(!Utils.invalidId(unidadeOrcamentariaId))
			listAcao = acaoService.buscarByUnidadeOrcamentaria(unidadeOrcamentariaId);
		
	}
	
	public void changeAcao() {
		
	}
	
	
	public Integer getQtdAcao() {
		return qtdAcao;
	}

	public void setQtdAcao(Integer qtdAcao) {
		this.qtdAcao = qtdAcao;
	}

	public Integer getQtdPrograma() {
		return qtdPrograma;
	}

	public void setQtdPrograma(Integer qtdPrograma) {
		this.qtdPrograma = qtdPrograma;
	}

	public Integer getExercicioVigente() {
		return exercicioVigente;
	}

	public void setExercicioVigente(Integer exercicioVigente) {
		this.exercicioVigente = exercicioVigente;
	}

	public Long getUnidadeGestoraId() {
		return unidadeGestoraId;
	}

	public void setUnidadeGestoraId(Long unidadeGestoraId) {
		this.unidadeGestoraId = unidadeGestoraId;
	}

	public List<UnidadeGestora> getListUnidadeGestora() {
		return listUnidadeGestora;
	}

	public void setListUnidadeGestora(List<UnidadeGestora> listUnidadeGestora) {
		this.listUnidadeGestora = listUnidadeGestora;
	}

	public Long getUnidadeOrcamentariaId() {
		return unidadeOrcamentariaId;
	}

	public void setUnidadeOrcamentariaId(Long unidadeOrcamentariaId) {
		this.unidadeOrcamentariaId = unidadeOrcamentariaId;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public Long getAcaoId() {
		return acaoId;
	}

	public void setAcaoId(Long acaoId) {
		this.acaoId = acaoId;
	}

	public List<Acao> getListAcao() {
		return listAcao;
	}

	public void setListAcao(List<Acao> listAcao) {
		this.listAcao = listAcao;
	}
	
	
	
}

package quantitativo.beans.fisicofinanceiro.anual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.service.PpaService;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
import quantitativo.model.TipoRegiao;
import quantitativo.service.FisicoFinanceiroService;
import quantitativo.service.RegiaoMunicipioService;
import quantitativo.service.RegiaoService;
import quantitativo.service.TipoRegiaoService;

@Named
@ViewScoped
public class FisicoFinanceiroAnualFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_SEARCH = "Falha ao pesquisar Ações";
	public static final String NO_RECORDS = "Nenhuma Ação Retornada";

	private Long id;

	private Acao acao;
	private Long tipoRegiaoId;
	private Long regiaoId;

	private Ppa ppa;
	private List<TipoRegiao> listTipoRegioes;
	private List<Regiao> listRegioes;
    private List<RegiaoMunicipio> listRegiaoMunicipio;
	
    private int exercicioInicio;
    private int exercicioFim;
    
	private AcaoService acaoService;
	private TipoRegiaoService tipoRegiaoService;
	private RegiaoService regiaoService;
	private RegiaoMunicipioService regiaoMunicipioService;
	private FisicoFinanceiroService fisicoFinanceiroService;

	@Inject
	public FisicoFinanceiroAnualFormMBean(AcaoService acaoService,
										  RegiaoMunicipioService regiaoMunicipioService,
										  FisicoFinanceiroService fisicoFinanceiroService,
										  TipoRegiaoService tipoRegiaoService,
										  RegiaoService regiaoService,
										  PpaService ppaService,
										  ProgramaService programaService) {

		this.acaoService = acaoService;
		this.tipoRegiaoService = tipoRegiaoService;
		this.regiaoService = regiaoService;
		this.regiaoMunicipioService=regiaoMunicipioService;
		this.fisicoFinanceiroService=fisicoFinanceiroService;
		
		listTipoRegioes = tipoRegiaoService.findAllOrderByDescricao();
		
		Optional<Ppa> ppaOptinal = ppaService.ppaVigente();
		
		if(!ppaOptinal.isPresent()) {
		  Messages.addMessageError("Nenhuma PPA Vigente encontrada ");
			return;
		}
		
		ppa = ppaOptinal.get();
		
		if(!ppa.getExercicios().isEmpty()) {
			
			exercicioInicio = ppa.getExercicios().get(0).getAno();
			exercicioFim = ppa.getExercicios().get(ppa.getExercicios().size()-1).getAno();
			
		}
		
		
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			acao = acaoService.findById(id);

		}

	}

	public void onChangeTipoRegiao() {

		if(Utils.invalidId(tipoRegiaoId)) {
			listRegiaoMunicipio=null;
			return;
		}
		
		listRegiaoMunicipio = regiaoMunicipioService.findAllByTipoRegiao(tipoRegiaoId);
		
		listRegioes = new ArrayList<>();
 
		
		for(RegiaoMunicipio regiaoMunicipio:listRegiaoMunicipio) {
			
			if(!Utils.invalidId(regiaoMunicipio.getRegiao().getId()))
				listRegioes.add(regiaoMunicipio.getRegiao());
		}
		
		initRegiaoMunicipio();
	}

	public void onChangeRegiao() {

		if(Utils.invalidId(regiaoId)) {
			onChangeTipoRegiao();
		}else {
			listRegiaoMunicipio = regiaoMunicipioService.findByRegiao(regiaoId);
			initRegiaoMunicipio();
		}
	}
	
	private void initRegiaoMunicipio() {
 
		 
		for(RegiaoMunicipio regiaoMunicipio:listRegiaoMunicipio) {
			
			regiaoMunicipio.setFisicoFinanceiro(new ArrayList<>());
			
			for(Exercicio exercicio:ppa.getExercicios()) {
				
				Optional<FisicoFinanceiro> ff = fisicoFinanceiroService.findByRegiaoMunicipioAndExercicioAndAcao(regiaoMunicipio.getId(), exercicio.getId(),acao.getId());
				
				if(ff.isPresent()) {
					regiaoMunicipio.getFisicoFinanceiro().add(ff.get());
				}else {
					regiaoMunicipio.getFisicoFinanceiro().add(new FisicoFinanceiro());
				}
			}
			
			
			
		}
		
		
	}
	
	public String inserePlanejamento() {
		
		System.out.println(listRegiaoMunicipio);
		
		return "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Long getTipoRegiaoId() {
		return tipoRegiaoId;
	}

	public void setTipoRegiaoId(Long tipoRegiaoId) {
		this.tipoRegiaoId = tipoRegiaoId;
	}

	public Long getRegiaoId() {
		return regiaoId;
	}

	public void setRegiaoId(Long regiaoId) {
		this.regiaoId = regiaoId;
	}

	public List<TipoRegiao> getListTipoRegioes() {
		return listTipoRegioes;
	}

	public void setListTipoRegioes(List<TipoRegiao> listTipoRegioes) {
		this.listTipoRegioes = listTipoRegioes;
	}

	public List<Regiao> getListRegioes() {
		return listRegioes;
	}

	public void setListRegioes(List<Regiao> listRegioes) {
		this.listRegioes = listRegioes;
	}

	public List<RegiaoMunicipio> getListRegiaoMunicipio() {
		return listRegiaoMunicipio;
	}

	public void setListRegiaoMunicipio(List<RegiaoMunicipio> listRegiaoMunicipio) {
		this.listRegiaoMunicipio = listRegiaoMunicipio;
	}

	public int getExercicioInicio() {
		return exercicioInicio;
	}

	public void setExercicioInicio(int exercicioInicio) {
		this.exercicioInicio = exercicioInicio;
	}

	public int getExercicioFim() {
		return exercicioFim;
	}

	public void setExercicioFim(int exercicioFim) {
		this.exercicioFim = exercicioFim;
	}

	
	
}

package quantitativo.beans.fisicofinanceiro.anual;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
import quantitativo.model.TipoRegiao;
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

	private List<TipoRegiao> listTipoRegioes;
	private List<Regiao> listRegioes;

	private AcaoService acaoService;
	private TipoRegiaoService tipoRegiaoService;
	private RegiaoService regiaoService;
	private RegiaoMunicipioService regiaoMunicipioService;

	@Inject
	public FisicoFinanceiroAnualFormMBean(AcaoService acaoService,
										  RegiaoMunicipioService regiaoMunicipioService,
										  TipoRegiaoService tipoRegiaoService,
										  RegiaoService regiaoService, 
										  ProgramaService programaService) {

		this.acaoService = acaoService;
		this.tipoRegiaoService = tipoRegiaoService;
		this.regiaoService = regiaoService;
		this.regiaoMunicipioService=regiaoMunicipioService;
		
		listTipoRegioes = tipoRegiaoService.findAllOrderByDescricao();
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			acao = acaoService.findById(id);

		}

	}

	public void onChangeTipoRegiao() {

		List<RegiaoMunicipio> listRegiaoMunicipio = regiaoMunicipioService.findAllByTipoRegiao(tipoRegiaoId);
		
		
		listRegioes = regiaoService.findAllByTipoRegiao(tipoRegiaoId);
	}

	public void onChangeRegiao() {

		List<RegiaoMunicipio> listRegiaoMunicipio = regiaoMunicipioService.findAllByRegiao(regiaoId);
		
		System.out.println(listRegiaoMunicipio.size());
		
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

}

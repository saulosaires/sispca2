package quantitativo.beans.fisicofinanceiro.mensal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.exception.JpaException;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.service.AcaoService;
import qualitativo.service.MesService;
import qualitativo.service.ProgramaService;
import quantitativo.model.FisicoFinanceiroMensal;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
import quantitativo.model.TipoRegiao;
import quantitativo.service.FisicoFinanceiroMensalService;
import quantitativo.service.RegiaoMunicipioService;
import quantitativo.service.TipoRegiaoService;

@Named
@ViewScoped
public class FisicoFinanceiroMensalFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;
	public static final String FAIL_SAVE="Falha ao Salvar Planejamento";
	public static final String SUCCESS_SAVE="Planejamento Salvo com sucesso";
	 

	private Long id;

	private Acao acao;
	private Long tipoRegiaoId;
	private Long regiaoId;

	private Exercicio exercicio;
	private List<TipoRegiao> listTipoRegioes;
	private List<Regiao> listRegioes;
    private List<RegiaoMunicipio> listRegiaoMunicipio;
	
    private List<Mes> listaMes;
    
	private AcaoService acaoService;
	private RegiaoMunicipioService regiaoMunicipioService;
	private FisicoFinanceiroMensalService fisicoFinanceiroMensalService;

	@Inject
	public FisicoFinanceiroMensalFormMBean(AcaoService acaoService,
										  RegiaoMunicipioService regiaoMunicipioService,
										  FisicoFinanceiroMensalService fisicoFinanceiroMensalService,
										  TipoRegiaoService tipoRegiaoService,
										  MesService mesService,
										  ExercicioService exercicioService,
										  ProgramaService programaService) {

		this.acaoService = acaoService;
		this.regiaoMunicipioService=regiaoMunicipioService;
		this.fisicoFinanceiroMensalService=fisicoFinanceiroMensalService;
 		
		listaMes = mesService.findallOrderById();
		
		listTipoRegioes = tipoRegiaoService.findAllOrderByDescricao();
		
		Optional<Exercicio> exercicioOptinal = exercicioService.exercicioVigente();
		
		if(!exercicioOptinal.isPresent()) {
		  Messages.addMessageError("Nenhuma Exercicio Vigente encontrado ");
			return;
		}
		
		exercicio = exercicioOptinal.get();
			
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
			
			regiaoMunicipio.setFisicoFinanceiroMensal(new ArrayList<>());
			 
			for(Mes mes: listaMes) {
				
				Optional<FisicoFinanceiroMensal> ff =  fisicoFinanceiroMensalService.findByRegiaoMunicipioAndExercicioAndAcaoAndMes(regiaoMunicipio.getId(), 
																																    exercicio.getId(),
																																    acao.getId(),
																																    mes.getId()
																																   );
				
				if(ff.isPresent()) {
					regiaoMunicipio.getFisicoFinanceiroMensal().add(ff.get());
				}else {
					
					FisicoFinanceiroMensal fisicoFinanceiro = new FisicoFinanceiroMensal();
									 	   fisicoFinanceiro.setAcao(acao);
									 	   fisicoFinanceiro.setExercicio(exercicio);
									 	   fisicoFinanceiro.setRegiaoMunicipio(regiaoMunicipio);
									 	   fisicoFinanceiro.setMes(mes);
					
					regiaoMunicipio.getFisicoFinanceiroMensal().add(fisicoFinanceiro);
				}
			}
			
						
		}
		
		
	}
	
	public String inserePlanejamento() {
		
			try {
				
				for(RegiaoMunicipio regiaoMunicipio:listRegiaoMunicipio) {
					
					if(temPlanejamento(regiaoMunicipio)) {
						
						for(FisicoFinanceiroMensal fisicoFinanceiroMensal : regiaoMunicipio.getFisicoFinanceiroMensal()) {
							
							if(fisicoFinanceiroMensal.getValor()>0 || fisicoFinanceiroMensal.getQuantidade()>0) {
								saveFisicoFinanceiro(fisicoFinanceiroMensal);	
							}
							
							
						}
						
					}
			
				}
		
				Messages.addMessageInfo(SUCCESS_SAVE);
				
				return "fisicoFinanceiroMensalQuantitativoList";
				
			} catch (Exception e) {
				SispcaLogger.logError(e.getCause().getMessage());
		
				Messages.addMessageError(FAIL_SAVE);
			}
			
		return "";
	}
	
	private void saveFisicoFinanceiro(FisicoFinanceiroMensal fisicoFinanceiroMensal) throws JpaException {
		
		if(Utils.invalidId(fisicoFinanceiroMensal.getId())) {
			
			if(fisicoFinanceiroMensal.getValor()>0 || fisicoFinanceiroMensal.getQuantidade()>0) {
				fisicoFinanceiroMensalService.merge(fisicoFinanceiroMensal);	
			}
			
		}else {
			fisicoFinanceiroMensalService.merge(fisicoFinanceiroMensal);	
		}
		
	}
	
	/**
	 * Metodo usado para, se foi feito alguma planejamento para aquela região/Municipio
	 * 
	 * **/
	public boolean temPlanejamento(RegiaoMunicipio regiaoMunicipio){
		
		Double valorTotal = Double.valueOf(0.0);
		Double quantidadeTotal = Double.valueOf(0.0);
		
		for(FisicoFinanceiroMensal ff: regiaoMunicipio.getFisicoFinanceiroMensal()){
			
			valorTotal += ff.getValor();
			quantidadeTotal += ff.getQuantidade();
		
		
			if(valorTotal >0 || quantidadeTotal>0) {return true;}
		}
		
		return false;
		
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

	public List<Mes> getListaMes() {
		return listaMes;
	}

	public void setListaMes(List<Mes> listaMes) {
		this.listaMes = listaMes;
	}

	
	
}

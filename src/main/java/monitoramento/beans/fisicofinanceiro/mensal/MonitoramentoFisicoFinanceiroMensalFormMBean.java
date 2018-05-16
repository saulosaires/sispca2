package monitoramento.beans.fisicofinanceiro.mensal;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
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
import monitoramento.model.Execucao;
import monitoramento.service.ExecucaoService;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.service.AcaoService;
import qualitativo.service.MesService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.model.FisicoFinanceiroMensal;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
import quantitativo.model.TipoRegiao;
import quantitativo.service.FisicoFinanceiroService;
import quantitativo.service.RegiaoMunicipioService;
import quantitativo.service.TipoRegiaoService;

@Named
@ViewScoped
public class MonitoramentoFisicoFinanceiroMensalFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;
	public static final String FAIL_SAVE="Falha ao Salvar Monitoramento";
	public static final String SUCCESS_SAVE="Monitoramento Salvo com sucesso";
	 
	public static final String FAIL_DELETE_OBS="Falha ao Deletar Observação";
	public static final String SUCCESS_DELETE_OBS="Observação deletada com sucesso";

	
	private Long id;

	private Acao acao;
	private Long tipoRegiaoId;
	private Long regiaoId;
	private Long mesId;
	private Mes mes;
	private Execucao execucaoSelecionada = new Execucao();
 	
	private Exercicio exercicio;
	private List<TipoRegiao> listTipoRegioes;
	private List<Regiao> listRegioes;
    private List<RegiaoMunicipio> listRegiaoMunicipio;
	
    private List<Mes> listaMes;
    
	private AcaoService acaoService;
	private RegiaoMunicipioService regiaoMunicipioService;
	private MesService mesService;
	private ExecucaoService execucaoService;

	private FisicoFinanceiroService fisicoFinanceiroService;

	@Inject
	public MonitoramentoFisicoFinanceiroMensalFormMBean(AcaoService acaoService,
														RegiaoMunicipioService regiaoMunicipioService,
														FisicoFinanceiroService fisicoFinanceiroService,
														TipoRegiaoService tipoRegiaoService,
														MesService mesService,
														ExecucaoService execucaoService,
														ExercicioService exercicioService ) {

		this.acaoService = acaoService;
		this.mesService=mesService;
		this.regiaoMunicipioService=regiaoMunicipioService;
		this.fisicoFinanceiroService=fisicoFinanceiroService;
 		this.execucaoService=execucaoService;
 		
		mes = new Mes();
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

			mesId = (long) LocalDate.now().getMonthValue();
			
			mes = mesService.findById(mesId);
			
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
	
	public void onChangeMes() {
		
		mes = mesService.findById(mesId);
		
		onChangeRegiao();
		
	}
	
	
	private void initRegiaoMunicipio() {
 
		 
		for(RegiaoMunicipio regiaoMunicipio:listRegiaoMunicipio) {
			 
			regiaoMunicipio.setExecucoes(new ArrayList<>());
			
			for(Mes m: listaMes) {

				regiaoMunicipio.getExecucoes().add(buscaExecucao(acao,regiaoMunicipio,exercicio,m));
			}
			 
			
			Optional<FisicoFinanceiro> ff = fisicoFinanceiroService.findByRegiaoMunicipioAndExercicioAndAcao(regiaoMunicipio.getId(), exercicio.getId(), acao.getId());
			
			
				if(ff.isPresent()) {
					FisicoFinanceiro fisicoFinanceiro = ff.get();
					
					regiaoMunicipio.setQuantidadeVigente(fisicoFinanceiro.getQuantidade());
					regiaoMunicipio.setValorVigente(fisicoFinanceiro.getValor());
				}else {
					
					regiaoMunicipio.setQuantidadeVigente(0.0);
					regiaoMunicipio.setValorVigente(0.0);
				}
			 
			
				soma(regiaoMunicipio);	
		}
		
		
	}
	
	public String soma(RegiaoMunicipio rm){
		
		Double valorTotal = Double.valueOf(0.0);
		Double quantidadeTotal = Double.valueOf(0);
		Double valorAnterior = Double.valueOf(0.0);
		Double quantidadeAnterior = Double.valueOf(0);

		for(Execucao ex: rm.getExecucoes()){
			valorTotal += ex.getValor();
			quantidadeTotal +=ex.getQuantidade();
			
			if(ex.getMes().getNumeroMes() < mes.getNumeroMes()){
				valorAnterior += ex.getValor();
				quantidadeAnterior += Double.valueOf(ex.getQuantidade());
			}
			
			if(ex.getMes().equals(mes)){
				rm.setQuantidadeAtual(Double.valueOf(ex.getQuantidade()));
				rm.setValorAtual(ex.getValor());			
			}

			
		}
		
		rm.setValorAnterior(valorAnterior);
		rm.setQuantidadeAnterior(quantidadeAnterior);
		
		rm.setValorTotal(valorTotal);
		rm.setQuantidadeTotal(quantidadeTotal);
		
		return "";
	}
	
	private Execucao buscaExecucao(Acao acao,RegiaoMunicipio regiaoMunicipio,Exercicio exercicio,Mes mes) {
		
		 Optional<Execucao> execucaoOptinal = execucaoService.findByAcaoAndRegiaoAndExercicioAndMes(acao.getId(),regiaoMunicipio.getId(),exercicio.getId(),mes.getId());
		
		 
		if (execucaoOptinal.isPresent()) {

			return execucaoOptinal.get();
		} else {

			Execucao execucaoMensal = new Execucao();
			execucaoMensal.setMes(mes);
			execucaoMensal.setQuantidade(BigInteger.ZERO.intValue());
			execucaoMensal.setValor(0.0);

			execucaoMensal.setRegiaoMunicipio(regiaoMunicipio);
			execucaoMensal.setAcao(acao);
			execucaoMensal.setAtivo(true);
			execucaoMensal.setExercicio(exercicio);

			return execucaoMensal;
			
		}
		 
	}
	
	public String salvar() {
		
			try {
				
				for(RegiaoMunicipio regiaoMunicipio:listRegiaoMunicipio) {
						
					for(FisicoFinanceiroMensal fisicoFinanceiroMensal : regiaoMunicipio.getFisicoFinanceiroMensal()) {
						
						if(fisicoFinanceiroMensal.getValor()>0 || fisicoFinanceiroMensal.getQuantidade()>0) {
							salvarExecucoes(regiaoMunicipio.getExecucoes());	
						}
						
						
					}
 
				}
		
				Messages.addMessageInfo(SUCCESS_SAVE);
				
				return "fisicoFinanceiroMensalMonitoramentoList";
				
			} catch (Exception e) {
				SispcaLogger.logError(e);
		
				Messages.addMessageError(FAIL_SAVE);
			}
			
		return "";
	}
	
	private void salvarExecucoes(List<Execucao> execucoes) throws JpaException {
 			
		for(Execucao ex :execucoes) {	
			
			if(!Utils.invalidId(ex.getId()) || ex.getValor()>0 || ex.getQuantidade()>0) {
				execucaoService.merge(ex);
			}
			
		}
 
	}
	
	 
	public void adicionarObservacao(RegiaoMunicipio regiaoMunicipio) {
		
		Optional<Execucao> execucaoOptional = regiaoMunicipio.getExecucoes().stream().filter( e -> e.getMes().getId().equals(mes.getId())).findFirst();
		
		if(execucaoOptional.isPresent()) {
			execucaoSelecionada = execucaoOptional.get();
 
		}
		
		 
	}
 
	public String deletarObservacao() {
		
		try {
			
			
			execucaoSelecionada.setObservacao("");
			
			execucaoService.update(execucaoSelecionada);
			  
			Messages.addMessageInfo(SUCCESS_DELETE_OBS);
			
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_DELETE_OBS);
		}
		
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

	public List<Mes> getListaMes() {
		return listaMes;
	}

	public void setListaMes(List<Mes> listaMes) {
		this.listaMes = listaMes;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	
	
	public Long getMesId() {
		return mesId;
	}

	public void setMesId(Long mesId) {
		this.mesId = mesId;
	}

	public Execucao getExecucaoSelecionada() {
		return execucaoSelecionada;
	}

	public void setExecucaoSelecionada(Execucao execucaoSelecionada) {
		this.execucaoSelecionada = execucaoSelecionada;
	}
 
	 
	
}

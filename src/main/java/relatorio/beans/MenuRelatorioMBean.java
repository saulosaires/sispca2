package relatorio.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.service.PpaService;
import relatorio.model.Relatorio;

@Named
@ViewScoped
public class MenuRelatorioMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7356688391633626543L;
	private List<Relatorio> relatorios = new ArrayList<>();
	
	private Long ppaId;
	private List<Ppa> listPpa;
	
	private Long exercicioId=-1l;
	private List<Exercicio> listExercicio;
	
	private PpaService ppaService;
	
	@Inject
	public MenuRelatorioMBean(PpaService ppaService){
 
		this.ppaService = ppaService;
		
		listPpa = ppaService.findAll();
		
		
		initRelatorio();
		
	}

    private void initRelatorio() {
    	
    	relatorios.add(initRelatorioPlanejamento());
    	relatorios.add(initRelatorioMonitoramento());
    	relatorios.add(initRelatorioFisicoFinanceiro());
    	relatorios.add(initRelatorioGerencial());
    	relatorios.add(initRelatorioOutros());
    	
    }
    
    private Relatorio initRelatorioPlanejamento() {
    	
    	Relatorio relatorioPlanejamento = new Relatorio("Planejamento","#","relatorioPlanejamento");
    	
    	 initCategoriaPlanejamento(relatorioPlanejamento);
    	
    	return relatorioPlanejamento;
    }
    
    
    private void initCategoriaPlanejamento(Relatorio relatorioPlanejamento){
    	
    	relatorioPlanejamento.getSubRelatorio().add(initCategoriaPlanejamentoQualitativo());
    	
    	relatorioPlanejamento.getSubRelatorio().add(initCategoriaPlanejamentoQuantitativo());
    	
    }
	
    private Relatorio initCategoriaPlanejamentoQualitativo() {
    	
    	Relatorio relatorio = new Relatorio("Relatório Qualitativo","#","relatorioPlanejamentoQualitativo");
    	
    	
    	relatorio.getSubRelatorio().add(new Relatorio(" Finalidade e Descrição das Ações","relatorioPlanejamentoQualitativoFinalidadeAcoes",	  "relatorioPlanejamentoQualitativoFinalidadeAcoes"));
    	relatorio.getSubRelatorio().add(new Relatorio(" Objetivo Plano Interno",		  "relatorioPlanejamentoQualitativoObjetivoPlanoInterno", "relatorioPlanejamentoQualitativoObjetivoPlanoInterno"));
       	relatorio.getSubRelatorio().add(new Relatorio(" Plano de Trabalho",		  		  "relatorioPlanejamentoQualitativoObjetivoPlanoTrabalho","relatorioPlanejamentoQualitativoObjetivoPlanoTrabalho"));
       	relatorio.getSubRelatorio().add(new Relatorio(" Qualitativo de Programas e Ações","relatorioPlanejamentoQualitativoProgramasAcoes",		  "relatorioPlanejamentoQualitativoProgramasAcoes"));
            	
    	
    	return relatorio;

    	
    }
    
    private Relatorio initCategoriaPlanejamentoQuantitativo() {
    	
    	Relatorio relatorio = new Relatorio("Relatório Quantitativo","#","relatorioPlanejamentoQuantitativo");
    	
    	relatorio.getSubRelatorio().add(new Relatorio(" Planejado por Ano",		    "relatorioPlanejamentoQuantitativoPlanejadoPorAno",   "relatorioPlanejamentoQuantitativoPlanejadoPorAno"));
    	relatorio.getSubRelatorio().add(new Relatorio(" Planejado por Região",	    "relatorioPlanejamentoQuantitativoPlanejadoPorRegiao","relatorioPlanejamentoQuantitativoPlanejadoPorRegiao"));
    	relatorio.getSubRelatorio().add(new Relatorio(" Planejamento Mensal",	    "relatorioPlanejamentoQuantitativoFisicoFinanceiro",  "relatorioPlanejamentoQuantitativoFisicoFinanceiro"));
    	relatorio.getSubRelatorio().add(new Relatorio(" Quantitativo Anual",	    "relatorioPlanejamentoQuantitativoAnual",	  	      "relatorioPlanejamentoQuantitativoAnual"));
    	relatorio.getSubRelatorio().add(new Relatorio(" Quantitativo Anual por UO ","relatorioPlanejamentoQuantitativoAnualPor Uo",		  "relatorioPlanejamentoQuantitativoAnualPor"));
    	
   
    	
    	return relatorio;
    }
    
    private Relatorio initRelatorioMonitoramento() {
    	
    	Relatorio relatorioPlanejamento = new Relatorio("Monitoramento","#","relatorioMonitoramento");
    	
    	return relatorioPlanejamento;
    }
    
    private Relatorio initRelatorioFisicoFinanceiro() {
    	
    	Relatorio relatorioPlanejamento = new Relatorio("Físico Financeiro","#","relatorioFisicoFinanceiro");
    	
    	return relatorioPlanejamento;
    }
    
    private Relatorio initRelatorioGerencial() {
    	
    	Relatorio relatorioPlanejamento = new Relatorio("Gerencial","#","relatorioGerencial");
    	
    	return relatorioPlanejamento;
    }
   
    private Relatorio initRelatorioOutros() {
    	
    	Relatorio relatorioPlanejamento = new Relatorio("Outros","#","relatorioOutros");
    	
    	return relatorioPlanejamento;
    }
   
    public void onChangePpa() {
    	
    	listExercicio = ppaService.findById(ppaId).getExercicios();
    	
    }
    
	public List<Relatorio> getRelatorios() {
		return relatorios;
	}


	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
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

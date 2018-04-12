package administrativo.beans.exercicio;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.service.ExercicioService;
import administrativo.service.PpaService;
import arquitetura.utils.Messages;
import arquitetura.utils.PrimeFacesUtils;
import arquitetura.utils.SispcaLogger;
 

@Named
@ViewScoped
public class ExercicioListMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9140878250857643893L;
	private String mensagem;
	private Ppa buscaPpa;
	private Integer ano;
	
	private List<Ppa> listPpa;
	private List<Exercicio> listExercicio;
	
	private Exercicio exercicioSelecionado;
	
 
	private ExercicioService exercicioService;
	
	 @Inject
	public ExercicioListMBean( PpaService ppaService,ExercicioService exercicioService){
		 
		 this.exercicioService=exercicioService;
		 this.listPpa = ppaService.findAll();
		
	}

	public String beforeAtualizaVigencia(Exercicio exercicio) {
		
		this.exercicioSelecionado=exercicio;
		
		if (exercicio.getVigente()){
			this.mensagem = "Deseja tornar o exercício do ppa "+exercicio.getPpa().getDescricao()+" do ano "+exercicio.getAno()+" sem vigência?";
			
			PrimeFacesUtils.execute("PF('dlgMensag').show()");
			 
	  	}else 	if (exercicioService.quantidadeVigente()>0){
	  		Messages.addMessageWarn("Já existe pelo menos um exercício vigente, fazer esta operação constituirá em um erro!");
	  	}else {
	  		this.mensagem = "Deseja tornar o exercício do ppa "+exercicio.getPpa().getDescricao()+" do ano "+exercicio.getAno()+" com vigência?";
	  		PrimeFacesUtils.execute("PF('dlgMensag').show()");
	  	}
		
		return "";
	}
	 
	public void buscaExercicioPorPpaAno() {
		
		listExercicio= exercicioService.buscaExercicioPorPpaAno(buscaPpa, ano);
		
	}
	
	public String atualizaVigencia() {
		
		try {
			exercicioService.trocarVigencia(exercicioSelecionado);
		
			Messages.addMessageInfo("Processo de atualização de vigência realizado com sucesso!");
		}catch(Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
			
			Messages.addMessageError("Erro ao realizar o processo de ativação/desativação para o exercício selecionado!");
		}
		
		buscaExercicioPorPpaAno();
		
		return "";
	}
	
	public Ppa getBuscaPpa() {
		return buscaPpa;
	}

	public void setBuscaPpa(Ppa buscaPpa) {
		this.buscaPpa = buscaPpa;
	}

	public List<Ppa> getListPpa() {
		return listPpa;
	}

	public void setListPpa(List<Ppa> listPpa) {
		this.listPpa = listPpa;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Exercicio> getListExercicio() {
		return listExercicio;
	}

	public void setListExercicio(List<Exercicio> listExercicio) {
		this.listExercicio = listExercicio;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	
	
}

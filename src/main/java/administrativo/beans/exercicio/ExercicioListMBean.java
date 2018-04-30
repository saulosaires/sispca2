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
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
 

@Named
@ViewScoped
public class ExercicioListMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9140878250857643893L;
	
	
	public static final String FAIL_DELETE    = " Falha inesperada ao tentar Deletar Exercicio";
	public static final String SUCCESS_DELETE = " Exercicio deletada com Sucesso";
	public static final String FAIL_SEARCH 	  = " Falha ao pesquisar  Exercicio";
	public static final String NO_RECORDS	  = " A pesquisa não retorno nenhum Exercicio";
	
	private String mensagem;
	private Long ppaId;
 	
	private List<Ppa> listPpa;
	private List<Exercicio> listExercicio;
	
	private Exercicio exercicioSelecionado;
	private ExercicioService exercicioService;
 	
	private boolean mudarVigencia;
	private boolean deletar;
	private boolean salvar;
	
	@Inject
	public ExercicioListMBean( PpaService ppaService,ExercicioService exercicioService){
		 
 		 this.exercicioService=exercicioService;
		 this.listPpa = ppaService.findAll();
		 
		 mudarVigencia = SessionUtils.containsKey("administrativoExercicioMudarVigencia"); 
		 deletar       = SessionUtils.containsKey("administrativoExercicioDeletar");
		 salvar        = SessionUtils.containsKey("administrativoExercicioSalvar");

		
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
	 
	public void buscar() {
		
		try {
			listExercicio= exercicioService.buscar(ppaId);
		
			if(listExercicio.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
		
		
	}
	
	
	
	public String deletar(Exercicio exercicio) {

		try {

			Exercicio exer  = exercicioService.findById(exercicio.getId());
			
			exer.getPpa().getExercicios().remove(exer);
			
			exercicioService.delete(exer);
			 
			buscar();

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		return "";

	}

	
	
	public String atualizaVigencia() {
		
		try {
			exercicioService.trocarVigencia(exercicioSelecionado);
		
			Messages.addMessageInfo("Processo de atualização de vigência realizado com sucesso!");
		}catch(Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
			
			Messages.addMessageError("Erro ao realizar o processo de ativação/desativação para o exercício selecionado!");
		}
		
		buscar();
		
		return "";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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

	public List<Exercicio> getListExercicio() {
		return listExercicio;
	}

	public void setListExercicio(List<Exercicio> listExercicio) {
		this.listExercicio = listExercicio;
	}

	public Exercicio getExercicioSelecionado() {
		return exercicioSelecionado;
	}

	public void setExercicioSelecionado(Exercicio exercicioSelecionado) {
		this.exercicioSelecionado = exercicioSelecionado;
	}

	public boolean isMudarVigencia() {
		return mudarVigencia;
	}

	public void setMudarVigencia(boolean mudarVigencia) {
		this.mudarVigencia = mudarVigencia;
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
 
	
	
}

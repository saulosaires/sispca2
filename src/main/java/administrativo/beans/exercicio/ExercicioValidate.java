package administrativo.beans.exercicio;

import java.io.Serializable;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class ExercicioValidate implements Validate<Exercicio>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3251296339078735938L;
	public static final String YEAR_REQUIRED = "Ano é um campo obrigatório";
	public static final String PPA_REQUIRED = "Ppa é um campo obrigatório";
	public static final String FAIL = "Falha inesperada ao tentar Salvar Exercicio";
	 
	
	@Override
	public boolean validar(Exercicio exercicio) {
		
		if (exercicio == null) {
			Messages.addMessageError(FAIL);
			return false;

		}

		if (exercicio.getPpa()==null || Utils.invalidId(exercicio.getPpa().getId())) {
			Messages.addMessageError(PPA_REQUIRED);
			return false;

		}

		if (exercicio.getAno()==null  ||  exercicio.getAno()<=0) {
			Messages.addMessageError(YEAR_REQUIRED);
			return false;

		}

		return true;
	}

}

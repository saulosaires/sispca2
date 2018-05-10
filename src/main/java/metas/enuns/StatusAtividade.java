package metas.enuns;

/** @author Fabio
 * Define todos os possíveis status que uma atividade pode ter. */
public enum StatusAtividade {
	/** Identificador do status: a atividade foi concluida segundo o que foi previsto e realizado. */
	CUMPRIU(1L,"Cumpriu"),
	/** Identificador do status: a atividade encontrasse em andamento segundo o que foi previsto e que já foi realizado. */
	EM_ANDAMENTO(2L,"Em Andamento"),
	/** Identificador do status: a atividade não foi concluida segundo o que estava previsto. */
	NAO_CUMPRIU(3L,"Não Cumpriu");
	
	private Long id;
	
	private String label;
	 
	private StatusAtividade(Long id,String label){
		this.id = id;
		this.label = label;
	}
	
	 

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	 

}

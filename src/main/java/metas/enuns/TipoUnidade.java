package metas.enuns;

public enum TipoUnidade {

	QUILOMETRO("Quilômetro"),
	
	PERCENTUAL("Percentual"),
	
	MONETARIO("Monetário"),
	
	QUANTIDADE("Quantidade");
	
	private String descricao;
	
	TipoUnidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

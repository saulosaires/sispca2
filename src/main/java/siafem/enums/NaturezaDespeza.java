package siafem.enums;

public enum NaturezaDespeza {

	
	PESSOAL_E_ENCARGOS(31),
	JUROS_E_AMORTIZACAO(32),
	CUSTEIO(33),
	INVESTIMENTO(44),
	INVERSAO_FINANCEIRA(45),
	SEM_DESCRICAO(99);
	
	private final Integer id;
	
	private NaturezaDespeza(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
 
	public String getLabel() {
		
		switch(id) {
		
		case 31: return "PESSOAL E ENCARGOS";
		case 32: return "JUROS E AMORTIZAÇÃO";
		case 33: return "CUSTEIO";
		case 44: return "INVESTIMENTO";
		case 45: return "INVERSÃO FINANCEIRA";
		case 99: return "SEM DESCRIÇÃO";
		default: return ""; 
		
		
		}
		
	}
}

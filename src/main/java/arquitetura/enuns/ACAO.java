package arquitetura.enuns;

public enum ACAO {

	GESTAO_PROGRAMA("4450");
	
	private String codigo;

	ACAO(String codigo) {
        this.codigo = codigo;
    }

    public String codigo() {
        return codigo;
    }
}

package administrativo.enums;

public enum NivelAcesso {
	
	p("Pleno"),
	g("Gestor"),
	a("Administrador");

	private String label;

	NivelAcesso(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}

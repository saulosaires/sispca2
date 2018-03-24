package administrativo.enums;

public enum NivelAcesso {
	
	P("Pleno"),
	G("Gestor"),
	A("Administrador");

	private String label;

	NivelAcesso(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}

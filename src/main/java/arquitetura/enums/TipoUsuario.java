package arquitetura.enums;

public enum TipoUsuario {

	P("Pleno"),
	G("Gestor"),
	A("Admin");

	 

	private final String type;

	private TipoUsuario(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}

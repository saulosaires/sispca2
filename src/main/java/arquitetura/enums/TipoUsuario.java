package arquitetura.enums;

public enum TipoUsuario {

	P("PLENO"),
	G("GESTOR"),
	A("ADMIN");

	 

	private final String type;

	private TipoUsuario(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}

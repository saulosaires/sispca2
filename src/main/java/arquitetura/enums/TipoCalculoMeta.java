package arquitetura.enums;

public enum TipoCalculoMeta {

	ACUMULATIVA(1l),
	NAO_ACUMULATIVA(2l);
 
	private Long id;

	TipoCalculoMeta(Long id) {
        this.id = id;
    }

    public boolean isAcumulativa(Long id) {
        return id.equals(ACUMULATIVA.getId());
    }

    public boolean isNaoAcumulativa(Long id) {
        return id.equals(NAO_ACUMULATIVA.getId());
    }
    
    
    public Long getId() {
		return id;
	}
}

package br.edu.ifpe.lpoo.project.enums;

public enum TipoItem {
	
	LIVRO("Livro"),
	PERIODICO("Periodico");
	
	private final String TIPO_ITEM;
	
	private TipoItem(String tipoUsuario) {
		this.TIPO_ITEM = tipoUsuario;
	}
	
	public String getTipo() {
		return this.TIPO_ITEM;
	}
	
	public static TipoItem tipoUsuario(String tipoItem) {
		for(TipoItem tipo : TipoItem.values()) {
			if(tipo.TIPO_ITEM.equalsIgnoreCase(tipoItem)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo de item inválido");
	}
}

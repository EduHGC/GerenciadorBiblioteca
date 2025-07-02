package br.edu.ifpe.lpoo.project.enums;

public enum TipoUsuario {
	
	ALUNO("Pluno"),
	PROFESSOR("Professor"),
	PESQUISADOR("Pesquisador");
	
	private final String TIPO_USUARIO;
	
	private TipoUsuario(String tipoUsuario) {
		this.TIPO_USUARIO = tipoUsuario;
	}
	
	public String getTipo() {
		return this.TIPO_USUARIO;
	}
	
	public static TipoUsuario tipoUsuario(String tipoUsuario) {
		for(TipoUsuario tipo : TipoUsuario.values()) {
			if(tipo.TIPO_USUARIO.equalsIgnoreCase(tipoUsuario)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
	
}

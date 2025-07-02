package br.edu.ifpe.lpoo.project.enums;

public enum StatusUsuario {
	ATIVO("Ativo"),
    INATIVO("Inativo"),
    SUSPENSO("Suspenso"),
    BLOQUEADO("Bloqueado");
	
	private final String STATUS_USUARIO;
	
	private StatusUsuario(String statusUsuario) {
		this.STATUS_USUARIO = statusUsuario;
	}
	
	public String getStatus() {
		return STATUS_USUARIO;
	}
	
	public static StatusUsuario statusUsuario(String statusUsuario) {
		for(StatusUsuario status : StatusUsuario.values()) {
			if(status.STATUS_USUARIO.equalsIgnoreCase(statusUsuario)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
}

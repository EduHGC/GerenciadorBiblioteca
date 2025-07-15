package br.edu.ifpe.lpoo.project.enums;

public enum TipoFuncionario {
	
	BIBLIOTECARIO("Bibliotecário"),
	SUPERVISOR("Supervisor");
	
	private final String TIPO_FUNCIONARIO;
	
	private TipoFuncionario(String tipoFuncionario) {
		this.TIPO_FUNCIONARIO = tipoFuncionario;
	}
	
	public String getTipo() {
		return this.TIPO_FUNCIONARIO;
	}
	
	public static TipoFuncionario tipoUsuario(String tipoFuncionario) {
		for(TipoFuncionario tipo : TipoFuncionario.values()) {
			if(tipo.TIPO_FUNCIONARIO.equalsIgnoreCase(tipoFuncionario)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
	
}

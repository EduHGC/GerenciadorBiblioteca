package br.edu.ifpe.lpoo.project.enums;

public enum StatusFuncionario {
	
	EFETIVO("Efetivo"),
	DESLIGADO("Desligado");
	
	private final String STATUS_FUNCIONARIO;
	
	private StatusFuncionario(String statusFuncionario) {
		this.STATUS_FUNCIONARIO = statusFuncionario;
	}
	
	public String getStatus() {
		return this.STATUS_FUNCIONARIO;
	}
	
	public static StatusFuncionario statusFuncionario(String statusFuncionario) {
		for(StatusFuncionario tipo : StatusFuncionario.values()) {
			if(tipo.STATUS_FUNCIONARIO.equalsIgnoreCase(statusFuncionario)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
}

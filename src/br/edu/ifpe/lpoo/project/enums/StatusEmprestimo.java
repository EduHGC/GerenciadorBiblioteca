package br.edu.ifpe.lpoo.project.enums;

public enum StatusEmprestimo {
	
	ABERTO("Aberto"),
	FINALIZADO("Finalizado"),
	ATRASADO("Atrasado");
	
	private final String STATUS_EMPRESTIMO;
	
	private StatusEmprestimo(String statusEmprestimo) {
		this.STATUS_EMPRESTIMO = statusEmprestimo;
	}
	
	public String getStatus() {
		return this.STATUS_EMPRESTIMO;
	}
	
	public static StatusEmprestimo statusEmprestimo(String statusEmprestimo) {
		for(StatusEmprestimo status : StatusEmprestimo.values()) {
			if(status.STATUS_EMPRESTIMO.equalsIgnoreCase(statusEmprestimo)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
}

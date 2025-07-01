package br.edu.ifpe.lpoo.project.enums;

public enum StatusExemplar {

	DISPONIVEL("Disponível"), EMPRESTADO("Emprestado"), RESERVADO("Reservado"), DANIFICADO("Danificado"),
	EXTRAVIADO("Extraviado"), EM_MANUTENCAO("Em Manutenção");

	private final String STATUS;

	StatusExemplar(String status) {
		this.STATUS = status;
	}

	public String getDescricao() {
		return STATUS;
	}

	public static StatusExemplar fromDescricao(String status) {
		for (StatusExemplar statusExemplar : StatusExemplar.values()) {
			if (statusExemplar.STATUS.equalsIgnoreCase(status)) {
				return statusExemplar;
			}
		}
		throw new IllegalArgumentException("Status inválidos");
	}
}

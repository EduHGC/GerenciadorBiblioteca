package br.edu.ifpe.lpoo.project.enums;

public enum FormatoDigital {

	PDF("PDF"), EPUB("EPUB"), MOBI("MOBI");

	private final String FORMATO;

	private FormatoDigital(String FORMATO) {
		this.FORMATO = FORMATO;
	}

	public String getFormato() {
		return this.FORMATO;
	}

	public static FormatoDigital verificarFormato(String formato) {

		for (FormatoDigital formatoDigital : FormatoDigital.values()) {
			if (formatoDigital.FORMATO.equalsIgnoreCase(formato)) {
				return formatoDigital;
			}
		}

		throw new IllegalArgumentException("Formato digital inválido");
	}
}

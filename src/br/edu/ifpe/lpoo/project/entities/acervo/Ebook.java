package br.edu.ifpe.lpoo.project.entities.acervo;

import br.edu.ifpe.lpoo.project.enums.FormatoDigital;

public class Ebook extends ItemAcervo {

	private String isbn;
	private FormatoDigital formatoDigital;
	private String url;

	public Ebook(int idItem, String titulo, String autor, String editora, int anoPublicacao, String genero,
			String idioma, int numeroPaginas, String isbn, FormatoDigital formatoDigital, String url) {
		super(idItem, titulo, autor, editora, anoPublicacao, genero, idioma, numeroPaginas);
		this.isbn = isbn;
		this.formatoDigital = formatoDigital;
		this.url = url;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public FormatoDigital getFormatoDigital() {
		return formatoDigital;
	}

	public void setFormatoDigital(FormatoDigital formatoDigital) {
		this.formatoDigital = formatoDigital;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

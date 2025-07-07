package br.edu.ifpe.lpoo.project.entities.acervo;

public class Livro extends ItemAcervo {

	private String isbn;

	public Livro(int idItem, String titulo, String autor, String editora, int anoPublicacao, String genero,
			String idioma, int numeroPaginas, String isbn) {
		super(idItem, titulo, autor, editora, anoPublicacao, genero, idioma, numeroPaginas);
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}

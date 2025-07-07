package br.edu.ifpe.lpoo.project.entities.acervo;

public abstract class ItemAcervo {

	private int idItem;
	private String titulo;
	private String autor;
	private String editora;
	private int anoPublicacao;
	private String genero;
	private String idioma;
	private int numeroPaginas;

	public ItemAcervo(int idItem, String titulo, String autor, String editora, int anoPublicacao, String genero,
			String idioma, int numeroPaginas) {
		super();
		this.idItem = idItem;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
		this.genero = genero;
		this.idioma = idioma;
		this.numeroPaginas = numeroPaginas;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
}

package br.edu.ifpe.lpoo.project.entities.acervo;

public class Periodicos extends ItemAcervo {

	private String issn;
	private int numeroEdicao;
	private int volume;
	private String dataPublicacao;
	private String genero;

	public Periodicos(String titulo, String autor, int anoPublicacao, String editora, String idioma, String issn,
			int numeroEdicao, int volume, String dataPublicacao, String genero) {
		super(titulo, autor, anoPublicacao, editora, idioma);
		this.issn = issn;
		this.numeroEdicao = numeroEdicao;
		this.volume = volume;
		this.dataPublicacao = dataPublicacao;
		this.genero = genero;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public int getNumeroEdicao() {
		return numeroEdicao;
	}

	public void setNumeroEdicao(int numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}

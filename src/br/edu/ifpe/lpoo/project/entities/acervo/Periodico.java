package br.edu.ifpe.lpoo.project.entities.acervo;

public class Periodico extends ItemAcervo {

	private String issn;
	private int volume;
	private int edicao;

	public Periodico(int idItem, String titulo, String autor, String editora, int anoPublicacao, String genero,
			String idioma, int numeroPaginas, String issn, int volume, int edicao) {
		super(idItem, titulo, autor, editora, anoPublicacao, genero, idioma, numeroPaginas);
		this.issn = issn;
		this.volume = volume;
		this.edicao = edicao;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

}

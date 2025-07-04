package br.edu.ifpe.lpoo.project.entities.acervo;

import br.edu.ifpe.lpoo.project.enums.StatusExemplar;

public class Exemplar {

	private int idExemplar;
	private int idLivro;
	private String registro;
	private StatusExemplar status;

	public Exemplar(int idLivro, String registro, StatusExemplar status) {
		
		this.idLivro = idLivro;
		this.registro = registro;
		this.status = status;
	}

	public int getIdExemplar() {
		return idExemplar;
	}

	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}

	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public StatusExemplar getStatus() {
		return status;
	}

	public void setStatus(StatusExemplar status) {
		this.status = status;
	}

}

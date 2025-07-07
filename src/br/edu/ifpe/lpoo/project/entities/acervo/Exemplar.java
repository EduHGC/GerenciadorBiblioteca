package br.edu.ifpe.lpoo.project.entities.acervo;

import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
import br.edu.ifpe.lpoo.project.enums.TipoItem;

public class Exemplar {

	private int idExemplar;
	private int idItem;
	private String registro;
	private TipoItem tipoItem;
	private StatusExemplar status;

	public Exemplar(int idItem, String registro, TipoItem tipoItem, StatusExemplar status) {

		this.idItem = idItem;
		this.registro = registro;
		this.tipoItem = tipoItem;
		this.status = status;
	}

	public int getIdExemplar() {
		return idExemplar;
	}

	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public StatusExemplar getStatus() {
		return status;
	}

	public void setStatus(StatusExemplar status) {
		this.status = status;
	}

}

package br.edu.ifpe.lpoo.project.entities.gerenciamento;

import java.time.LocalDate;

import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;

public class Emprestimo {

	private int idEmprestimo;
	private int idExemplar;
	private int idUsuario;
	private int idBibliotecario;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private LocalDate dataRealDevolucao;
	private StatusEmprestimo statusEmprestimo;

	public Emprestimo(int idExemplar, int idUsuario, int idBibliotecario, LocalDate dataEmprestimo, LocalDate dataDevolucao,
			StatusEmprestimo statusEmprestimo) {
		super();
		this.idExemplar = idExemplar;
		this.idUsuario = idUsuario;
		this.idBibliotecario = idBibliotecario;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.statusEmprestimo = statusEmprestimo;
	}

	public int getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public int getIdExemplar() {
		return idExemplar;
	}

	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdBibliotecario() {
		return idBibliotecario;
	}

	public void setIdBibliotecario(int idBibliotecario) {
		this.idBibliotecario = idBibliotecario;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public LocalDate getDataRealDevolucao() {
		return dataRealDevolucao;
	}

	public void setDataRealDevolucao(LocalDate dataRealDevolucao) {
		this.dataRealDevolucao = dataRealDevolucao;
	}

	public StatusEmprestimo getStatusEmprestimo() {
		return statusEmprestimo;
	}

	public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
		this.statusEmprestimo = statusEmprestimo;
	}

}

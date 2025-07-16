package br.edu.ifpe.lpoo.project.entities.gerenciamento;

import java.time.LocalDate;

import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;

public class CardEmprestimo {

	private int idEmprestimo;
	private String nomeUsuario;
	private String cpfUsuario;
	private String nomeFuncionario;
	private String titulo;
	private LocalDate dataEmprestimo;
	private LocalDate dataParaDevolucao;
	private LocalDate dataRealDevolucao;
	private int idLivro;
	private int idExemplar;
	private StatusEmprestimo statusEmprestimo;

	public CardEmprestimo(int idEmprestimo, String nomeUsuario, String cpfUsuario, String nomeFuncionario,
			String titulo, LocalDate dataEmprestimo, LocalDate dataParaDevolucao, LocalDate dataRealDevolucao,
			int idLivro, int idExemplar, StatusEmprestimo statusEmprestimo) {
		this.idEmprestimo = idEmprestimo;
		this.nomeUsuario = nomeUsuario;
		this.cpfUsuario = cpfUsuario;
		this.nomeFuncionario = nomeFuncionario;
		this.titulo = titulo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataParaDevolucao = dataParaDevolucao;
		this.dataRealDevolucao = dataRealDevolucao;
		this.idLivro = idLivro;
		this.idExemplar = idExemplar;
		this.statusEmprestimo = statusEmprestimo;
	}

	public int getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataParaDevolucao() {
		return dataParaDevolucao;
	}

	public void setDataParaDevolucao(LocalDate dataParaDevolucao) {
		this.dataParaDevolucao = dataParaDevolucao;
	}

	public LocalDate getDataRealDevolucao() {
		return dataRealDevolucao;
	}

	public void setDataRealDevolucao(LocalDate dataRealDevolucao) {
		this.dataRealDevolucao = dataRealDevolucao;
	}

	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public int getIdExemplar() {
		return idExemplar;
	}

	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}

	public StatusEmprestimo getStatusExemplar() {
		return statusEmprestimo;
	}

	public void setStatusExemplar(StatusEmprestimo statusExemplar) {
		this.statusEmprestimo = statusExemplar;
	}

}

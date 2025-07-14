package br.edu.ifpe.lpoo.project.entities.funcionario;

import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.enums.TipoFuncionario;

public class Funcionario {

	private int idFuncionario;
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private String matricula;
	private TipoFuncionario tipoFuncionario;
	private boolean ativo;
	private StatusFuncionario statusFuncionario;

	public Funcionario(String cpf, String nome, String email, String senha, String matricula,
			TipoFuncionario tipoFuncionario, boolean ativo, StatusFuncionario statusFuncionario) {

		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.matricula = matricula;
		this.tipoFuncionario = tipoFuncionario;
		this.ativo = ativo;
		this.statusFuncionario = statusFuncionario;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public StatusFuncionario getStatusFuncionario() {
		return statusFuncionario;
	}

	public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}
	
	

}

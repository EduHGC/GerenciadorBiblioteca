package br.edu.ifpe.lpoo.project.entities.funcionario;

import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.enums.Cargo;

public class Funcionario {

	private int idFuncionario;
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private String matricula;
	private Cargo cargo;
	private boolean logado;
	private StatusFuncionario statusFuncionario;

	public Funcionario(String cpf, String nome, String email, String matricula,
			Cargo cargo, boolean logado, StatusFuncionario statusFuncionario) {

		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.matricula = matricula;
		this.cargo = cargo;
		this.logado = logado;
		this.statusFuncionario = statusFuncionario;
	}
	
	public Funcionario(String cpf, String nome, String email, String senha, String matricula,
			Cargo cargo, boolean ativo, StatusFuncionario statusFuncionario) {

		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.matricula = matricula;
		this.cargo = cargo;
		this.logado = ativo;
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public StatusFuncionario getStatusFuncionario() {
		return statusFuncionario;
	}

	public void setStatusFuncionario(StatusFuncionario statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}
	
	

}

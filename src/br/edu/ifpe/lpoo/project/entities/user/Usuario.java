package br.edu.ifpe.lpoo.project.entities.user;

import br.edu.ifpe.lpoo.project.enums.StatusUsuario;
import br.edu.ifpe.lpoo.project.enums.TipoUsuario;

public class Usuario {

	private int idUsuario;
	private String cpf;
	private String matricula;
	private String nome;
	private String email;
	private String telefone;
	private String departamentoCurso;
	private TipoUsuario tipoUsuario;
	private StatusUsuario statusUsuario;
	private String instituicao;
	private double debito;

	public Usuario(String cpf, String matricula, String nome, String email, String telefone, String departamentoCurso,
			TipoUsuario tipoUsuario, StatusUsuario statusUsuario, String instituicao, double debito) {
		super();
		this.cpf = cpf;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.departamentoCurso = departamentoCurso;
		this.tipoUsuario = tipoUsuario;
		this.statusUsuario = statusUsuario;
		this.instituicao = instituicao;
		this.debito = debito;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDepartamentoCurso() {
		return departamentoCurso;
	}

	public void setDepartamentoCurso(String departamentoCurso) {
		this.departamentoCurso = departamentoCurso;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public double getDebito() {
		return debito;
	}

	public void setDebito(double debito) {
		this.debito = debito;
	}
}

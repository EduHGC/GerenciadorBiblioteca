package br.edu.ifpe.lpoo.project.business.funcionario;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.FuncionarioRepository;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.enums.Cargo;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class FuncionarioController {

	private FuncionarioRepository funcionarioRepository;

	public FuncionarioController(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public FuncionarioController() {
		this(new FuncionarioRepository());
	}

	public void inserir(String cpf, String nome, String email, String senha, String matricula, String cargo) {

		if (cpf.isBlank() || !cpf.matches("\\d{11}")) {
			throw new BusinessException("CPF inválido ou o campo está vazio");
		}

		if (nome.isBlank()) {
			throw new BusinessException("O campo nome está vazio");
		}

		if (email.isBlank() || !email.matches("^[a-zA-Z0-9._-]+@biblioteca\\.edu\\.br$")) {
			throw new BusinessException(
					"Email inválido ou o campo está vazio. o email precisa seguir o padrão usuario@biblioteca.edu.br");
		}

		if (senha.isBlank() || senha.length() < 8) {
			throw new BusinessException("O campo senha está vazio ou tem menos que 8 dígitos");
		}

		if (!senha.matches(".*[A-Z].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 letra maiúscula");
		}

		if (!senha.matches(".*[a-z].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 letra minúscula");
		}

		if (!senha.matches(".*[0-9].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 número");
		}
		if (!senha.matches(".*[^a-zA-Z0-9].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 caractere especial");
		}

		if (matricula.isBlank()) {
			throw new BusinessException("O campo matricula está vazio");
		}

		if (cargo.equals("Selecione um cargo para o funcionário")) {
			throw new BusinessException("É necessário selecionar um cargo para o funcionário");
		}

		Funcionario funcionario = new Funcionario(cpf, nome, email, senha, matricula, Cargo.cargoFuncionario(cargo),
				false, StatusFuncionario.statusFuncionario("Efetivo"));

		try {

			if (!funcionarioRepository.existe(funcionario)) {
				funcionarioRepository.inserir(funcionario);
			} else {
				throw new BusinessException("Funcionário já existe no sistema");
			}
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}

	public Funcionario buscarPorId(String idFuncionario) {

		if (idFuncionario.isBlank()) {
			throw new BusinessException("Preencha o campo do id");
		}

		int parseIdFuncionario;
		try {
			parseIdFuncionario = Integer.parseInt(idFuncionario);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}

		if (parseIdFuncionario <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}

		Funcionario funcionario = null;

		try {
			funcionario = funcionarioRepository.buscarPorId(parseIdFuncionario);

			if (funcionario == null) {
				throw new BusinessException("O funcionário com id fonecido não está cadastrado no sistema.");
			}

		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}

		return funcionario;
	}

	public List<Funcionario> buscarTodos() {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
			funcionarios.addAll(funcionarioRepository.buscarTodos());

			if (funcionarios.isEmpty()) {
				throw new BusinessException("Não existe funcionários cadastrados");
			}
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
		return funcionarios;
	}

	public void atualizar(String idfuncionario, String cpf, String nome, String email, String matricula, String cargo,
			String statusFuncionario, boolean habilitarMudarSenha, boolean logado, String novaSenhaString) {

		if (idfuncionario.isBlank()) {
			throw new BusinessException("Preencha o campo do id");
		}

		int parseIdfuncionario;
		try {
			parseIdfuncionario = Integer.parseInt(idfuncionario);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}

		if (parseIdfuncionario <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}

		if (cpf.isBlank() || !cpf.matches("\\d{11}")) {
			throw new BusinessException("CPF inválido ou o campo está vazio");
		}

		if (nome.isBlank()) {
			throw new BusinessException("O campo nome está vazio");
		}

		if (email.isBlank() || !email.matches("^[a-zA-Z0-9._-]+@biblioteca\\.edu\\.br$")) {
			throw new BusinessException(
					"Email inválido ou o campo está vazio. O email precisa seguir o padrão usuario@biblioteca.edu.br");
		}

		if (habilitarMudarSenha) {
			verificarSenha(novaSenhaString);
		}

		if (matricula.isBlank()) {
			throw new BusinessException("O campo matricula está vazio");
		}

		if (cargo.equals("Selecione um cargo para o funcionário")) {
			throw new BusinessException("É necessário selecionar uma categoria de usuário");
		}

		if (statusFuncionario.equals("Selecione o status do funcionário")) {
			throw new BusinessException("É necessário selecionar uma status para o funcionário");
		}

		Funcionario funcionario;

		if (habilitarMudarSenha) {
			funcionario = new Funcionario(cpf, nome, email, novaSenhaString, matricula, Cargo.cargoFuncionario(cargo),
					logado, StatusFuncionario.statusFuncionario(statusFuncionario));
			funcionario.setIdFuncionario(parseIdfuncionario);

		} else {
			funcionario = new Funcionario(cpf, nome, email, matricula, Cargo.cargoFuncionario(cargo), logado,
					StatusFuncionario.statusFuncionario(statusFuncionario));
			funcionario.setIdFuncionario(parseIdfuncionario);
		}

		try {
			Funcionario funcionarioExiste = funcionarioRepository.buscarPorId(funcionario.getIdFuncionario());
			if (funcionarioExiste == null) {
				throw new BusinessException("O funcionário fonecido não está cadastrado no sistema.");
			}

			if (habilitarMudarSenha) {
				funcionarioRepository.atualizarSenha(funcionario);
			} else {
				funcionarioRepository.atualizar(funcionario);
			}

		} catch (Exception e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}

	private void verificarSenha(String senha) {

		if (senha.isBlank() || senha.length() < 8) {
			throw new BusinessException("O campo está vazio ou tem menos que 8 dígitos");
		}

		if (!senha.matches(".*[A-Z].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 letra maiúscula");
		}

		if (!senha.matches(".*[a-z].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 letra minúscula");
		}

		if (!senha.matches(".*[0-9].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 número");
		}
		if (!senha.matches(".*[^a-zA-Z0-9].*")) {
			throw new BusinessException("A senha precisa de pelo menos 1 caractere especial");
		}
	}
}

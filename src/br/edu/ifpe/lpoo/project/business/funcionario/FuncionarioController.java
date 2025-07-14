package br.edu.ifpe.lpoo.project.business.funcionario;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.FuncionarioRepository;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.enums.TipoFuncionario;
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

	public void inserir(String cpf, String nome, String email, String senha, String matricula, String tipoFuncionario) {

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

		if (matricula.isBlank()) {
			throw new BusinessException("O campo matricula está vazio");
		}

		if (tipoFuncionario.equals("Selecione uma categoria de funcionário")) {
			throw new BusinessException("É necessário selecionar uma categoria de usuário");
		}

		Funcionario funcionario = new Funcionario(cpf, nome, email, senha, matricula,
				TipoFuncionario.tipoUsuario(tipoFuncionario), false, StatusFuncionario.statusFuncionario("Ativo"));

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

	public void atualizar(String idfuncionario, String cpf, String nome, String email, String senha, String matricula,
			String tipoFuncionario, String statusFuncionario) {

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
					"Email inválido ou o campo está vazio. o email precisa seguir o padrão usuario@biblioteca.edu.br");
		}

		if (senha.isBlank() || senha.length() < 8) {
			throw new BusinessException("O campo senha está vazio ou tem menos que 8 dígitos");
		}

		if (matricula.isBlank()) {
			throw new BusinessException("O campo matricula está vazio");
		}

		if (tipoFuncionario.equals("Selecione uma categoria de funcionário")) {
			throw new BusinessException("É necessário selecionar uma categoria de usuário");
		}
		
		if (statusFuncionario.equals("Selecione o status do funcionário")) {
			throw new BusinessException("É necessário selecionar uma status para o funcionário");
		}
		
		Funcionario funcionario = new Funcionario(cpf, nome, email, senha, matricula,
				TipoFuncionario.tipoUsuario(tipoFuncionario), false, StatusFuncionario.statusFuncionario("Ativo"));
		funcionario.setIdFuncionario(parseIdfuncionario);
		
		try {
			Funcionario funcionarioExiste = funcionarioRepository.buscarPorId(funcionario.getIdFuncionario());
			if(funcionarioExiste == null) {
				throw new BusinessException("O funcionário fonecido não está cadastrado no sistema.");
			}
			
			funcionarioRepository.atualizar(funcionario);
			
		} catch (Exception e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}
}

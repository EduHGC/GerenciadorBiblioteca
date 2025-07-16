package br.edu.ifpe.lpoo.project.business.funcionario;

import br.edu.ifpe.lpoo.project.data.implement.FuncionarioRepository;
import br.edu.ifpe.lpoo.project.data.implement.LoginRepository;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class LoginController {
	
	private LoginRepository loginRepository;
	private FuncionarioRepository funcionarioRepository;
	
	public LoginController(LoginRepository loginRepository, FuncionarioRepository funcionarioRepository) {
		this.loginRepository = loginRepository;
		this.funcionarioRepository = funcionarioRepository;
	}

	public LoginController() {
		this(new LoginRepository(), new FuncionarioRepository());
	}
	
	public Funcionario buscarLogin(String email) {
		
		Funcionario funcionario  = null;
		
		if (email.isBlank() || !email.matches("^[a-zA-Z0-9._-]+@biblioteca\\.edu\\.br$")) {
			throw new BusinessException("Email inválido ou o campo está vazio. o email precisa seguir o padrão usuario@biblioteca.edu.br");
		}
		
		try {
			
			funcionario = loginRepository.buscarFuncionario(email);
			
			if(funcionario == null) {
				throw new BusinessException("Funcionário não encontrado");
			}
			
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
		
		return funcionario;
		
	}
	
	public void atualizarParaLogin(Funcionario funcionario) {
		
		if(funcionario == null) {
			throw new BusinessException("Funcionário não informado para fazer login");
		}
		
		try {
			funcionarioRepository.atualizar(funcionario);
			
		} catch (Exception e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}
}

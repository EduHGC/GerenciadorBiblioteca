package br.edu.ifpe.lpoo.project.business.user;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.UsuarioRepository;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.enums.StatusUsuario;
import br.edu.ifpe.lpoo.project.enums.TipoUsuario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class FuncionarioController {

	private UsuarioRepository usuarioRepository;

	public FuncionarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public FuncionarioController() {
		this(new UsuarioRepository());
	}

	public void inserir(String cpf, String matricula, String nome, String email, String telefone,
			String departamentoCurso, String tipoUsuario, String statusUsuario, String instituicao) {

		if (cpf.isBlank() || !cpf.matches("\\d{11}")) {
			throw new BusinessException("CPF inválido ou o campo está vazio");
		}

		if (matricula.isBlank()) {
			throw new BusinessException("Matrícula inválida ou o campo está vazio");
		}
		
		if(nome.isBlank()) {
			throw new BusinessException("O campo nome está vazio");
		}

		if (email.isBlank() || !email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$")) {
			throw new BusinessException("Email inválido ou o campo está vazio");
		}

		if (telefone.isBlank() || !telefone.matches("\\d{11}")) {
			throw new BusinessException("Telefone inválido ou o campo está vazio. DDD precisa ser incluso");
		}
		
		if(telefone.charAt(2) != '9') {
			throw new BusinessException("Falta o dígito 9 após o DDD");
		}

		if (departamentoCurso.isBlank() || departamentoCurso.length() < 3) {
			throw new BusinessException(
					"Departamento inválido ou o campo está vazio. É necessário no mínimo 3 caracteres");
		}

		if (tipoUsuario.equals("Selecione uma categoria de usuário")) {
			throw new BusinessException("É necessário selecionar uma categoria de usuário");
		}

		if (tipoUsuario.equals("Selecione um status para o usuário")) {
			throw new BusinessException("É necessário selecionar o status do usuário");
		}

		if (instituicao.isBlank() || instituicao.length() < 3) {
			throw new BusinessException(
					"Instituição inválido ou campo está vazio. É necessário no mínimo 3 caracteres");
		}

		Usuario usuario = new Usuario(cpf, matricula, nome, email, telefone, departamentoCurso,
				TipoUsuario.tipoUsuario(tipoUsuario), StatusUsuario.statusUsuario(statusUsuario), instituicao, 0.0);

		try {

			if (!usuarioRepository.existe(usuario)) {
				usuarioRepository.inserir(usuario);
			} else {
				throw new BusinessException("Usuário já existe no sistema");
			}
		} catch (ExceptionDb e1) {
			throw new BusinessException("Erro no banco de dados: " + e1.getMessage());
		}
	}

	public Usuario buscarPorId(String idUsuario) {

		if (idUsuario.isBlank()) {
			throw new BusinessException("Preencha o campo do id");
		}

		int parseIdUsuario;
		try {
			parseIdUsuario = Integer.parseInt(idUsuario);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}

		if (parseIdUsuario <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}

		Usuario usuario = null;
		try {

			usuario = usuarioRepository.buscarPorId(parseIdUsuario);

			if (usuario == null) {
				throw new BusinessException("O usuário com id fonecido não está cadastrado no sistema.");
			}
		} catch (ExceptionDb e1) {
			throw new BusinessException("Erro no banco de dados: " + e1.getMessage());
		}

		return usuario;
	}

	public List<Usuario> listarUsuarios() {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			usuarios.addAll(usuarioRepository.buscarTodos());
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}

		if (usuarios.isEmpty()) {
			throw new BusinessException("Não existe usuários cadastrados");
		}

		return usuarios;
	}

	public void deletar(String idUsuario) {

		if (idUsuario.isBlank()) {
			throw new BusinessException("Preencha o campo do id");
		}

		int parseIdUsuario;
		try {
			parseIdUsuario = Integer.parseInt(idUsuario);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}

		if (parseIdUsuario <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}

		try {

			Usuario usuario = usuarioRepository.buscarPorId(parseIdUsuario);

			if (usuario == null) {
				throw new BusinessException("O usuário com id fonecido não está cadastrado no sistema.");
			}

			usuarioRepository.deletar(usuario.getIdUsuario());

		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}
	
	public List<Usuario> buscarPorPalavaraChave(String palavraChave){
		
		if(palavraChave.isBlank()) {
			throw new BusinessException("O campo de busca está vazio");
		}
		
		if(palavraChave.length() < 3) {
			throw new BusinessException("O campo de busca precisa ao menos 4 caracteres");
		}
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			usuarios.addAll(usuarioRepository.buscarPorTermo(palavraChave));
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}

		if (usuarios.isEmpty()) {
			throw new BusinessException("Não foi encontrado usuários com a palavra chave informada");
		}
		
		return usuarios;
	}
	
	public void atualizar(String idUsuario, String cpf, String matricula, String nome, String email, String telefone,
			String departamentoCurso, String tipoUsuario, String statusUsuario, String instituicao, String debito) {
			
		if (idUsuario.isBlank()) {
			throw new BusinessException("Preencha o campo do id");
		}

		int parseIdUsuario;
		try {
			parseIdUsuario = Integer.parseInt(idUsuario);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}

		if (parseIdUsuario <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}
			
		if (cpf.isBlank() || !cpf.matches("\\d{11}")) {
			throw new BusinessException("CPF inválido ou o campo está vazio");
		}

		if (matricula.isBlank()) {
			throw new BusinessException("Matrícula inválida ou o campo está vazio");
		}

		if (email.isBlank() || !email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$")) {
			throw new BusinessException("Email inválido ou o campo está vazio");
		}

		if (telefone.isBlank() || !telefone.matches("\\d{11}")) {
			throw new BusinessException("Telefone inválido ou o campo está vazio. DDD precisa ser incluso");
		}
		
		if(telefone.charAt(2) != '9') {
			throw new BusinessException("Falta o dígito 9 após o DDD");
		}

		if (departamentoCurso.isBlank() || departamentoCurso.length() < 3) {
			throw new BusinessException(
					"Departamento inválido ou o campo está vazio. É necessário no mínimo 3 caracteres");
		}

		if (tipoUsuario.equals("Selecione uma categoria de usuário")) {
			throw new BusinessException("É necessário selecionar uma categoria de usuário");
		}

		if (tipoUsuario.equals("Selecione um status para o usuário")) {
			throw new BusinessException("É necessário selecionar o status do usuário");
		}

		if (instituicao.isBlank() || instituicao.length() < 3) {
			throw new BusinessException(
					"Instituição inválido ou campo está vazio. É necessário no mínimo 3 caracteres");
		}
		
		if(debito.isBlank()) {
			throw new BusinessException("É necessário selecionar o status do usuário");
		}
		
		double parseDebito;
		try {
			parseDebito = Double.parseDouble(debito);
		} catch (NumberFormatException e) {
			throw new BusinessException("O valor é necessário seguir o padrão XX,YY. Exemplo: 2,55 ou 35,40");
		}
		
		BigDecimal valorEmMoeda = new BigDecimal(parseDebito);
		valorEmMoeda = valorEmMoeda.setScale(2, RoundingMode.HALF_UP);
		
		double valorDebito = valorEmMoeda.doubleValue();
		
		Usuario usuario = new Usuario(cpf, matricula, nome, email, telefone, departamentoCurso,
				TipoUsuario.tipoUsuario(tipoUsuario), StatusUsuario.statusUsuario(statusUsuario), instituicao, valorDebito);
		usuario.setIdUsuario(parseIdUsuario);
		
		try {
			
			Usuario usuarioExiste = usuarioRepository.buscarPorId(usuario.getIdUsuario());
			
			if (usuarioExiste == null) {
				throw new BusinessException("O usuário fonecido não está cadastrado no sistema.");
			}

			usuarioRepository.atualizar(usuario);
			
		} catch (ExceptionDb e1) {
			throw new BusinessException("Erro no banco de dados: " + e1.getMessage());
		}
		
	}
}

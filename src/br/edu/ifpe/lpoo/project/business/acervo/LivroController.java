package br.edu.ifpe.lpoo.project.business.acervo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.ExemplarRepository;
import br.edu.ifpe.lpoo.project.data.implement.LivroRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
import br.edu.ifpe.lpoo.project.enums.TipoItem;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class LivroController {

	private LivroRepository livroRepository;
	private ExemplarRepository exemplarRepository;

	public LivroController(LivroRepository livroRepository, ExemplarRepository exemplarRepository) {
		this.livroRepository = livroRepository;
		this.exemplarRepository = exemplarRepository;
	}
	
	public LivroController() {
		this(new LivroRepository(), new ExemplarRepository());
	}

	public void cadastarLivro(String titulo, String autor, String editora, String anoPublicacao, String genero,
			String idioma, String numeroPaginas, String isbn, String quantidadeExemplares) throws BusinessException {

		validarCampo(titulo, "Título");

		validarCampo(autor, "Autor");

		validarCampo(editora, "Editora");

		validarCampo(anoPublicacao, "Ano de Publicação");

		int parseoAnoPublicacao;
		try {
			parseoAnoPublicacao = Integer.parseInt(anoPublicacao);
		} catch (NumberFormatException e) {
			throw new BusinessException("O ano da publicação precisar ser numérico.");
		}
		int anoAtual = LocalDate.now().getYear();
		if (parseoAnoPublicacao > anoAtual) {
			throw new BusinessException("O ano digitado é maior que o ano atual");
		}

		validarCampo(genero, "Gênero");

		validarCampo(idioma, "Idioma");

		validarCampo(numeroPaginas, "Número de Páginas");
		int parseoNumeroPaginas;
		try {
			parseoNumeroPaginas = Integer.parseInt(numeroPaginas);
		} catch (NumberFormatException e) {
			throw new BusinessException("O número de páginas precisa ser numérico.");
		}

		if (parseoNumeroPaginas < 1) {
			throw new BusinessException("O número de páginas precisa ser maior que 1");
		}

		if (isbn != null && !isbn.isBlank()) {
			if (isbn.length() != 10 && isbn.length() != 13) {
				throw new BusinessException("ISBN informado é inválido, não contem 10 ou 13 caracteres");
			}
		}

		validarCampo(quantidadeExemplares, "Quantidade de Exemplares");
		int parseoQuantidadeExemplares;
		try {
			parseoQuantidadeExemplares = Integer.parseInt(quantidadeExemplares);
		} catch (NumberFormatException e) {
			throw new BusinessException("A quantidade de exemplares precisa ser numérica.");
		}

		if (parseoQuantidadeExemplares < 1) {
			throw new BusinessException("A quantidade de exemplares deve ser pelo menos 1.");
		}

		Livro livro = new Livro(titulo, autor, editora, parseoAnoPublicacao, genero, idioma, parseoNumeroPaginas, isbn);

		try {

			int idLivro;
			if (!livroRepository.existe(livro)) {
				idLivro = livroRepository.inserir(livro);
			} else {
				throw new BusinessException("Livro já existe no sistema");
			}

			for (int i = 1; i <= parseoQuantidadeExemplares; i++) {
				String registro = idLivro + "EXPLIV" + i;
				Exemplar exemplar = new Exemplar(idLivro, registro, TipoItem.LIVRO, StatusExemplar.DISPONIVEL);
				exemplarRepository.inserir(exemplar);
			}
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}
	
	
	public Livro buscarLivroId(String idLivro) {
		
		int id = validarId(idLivro, "Id Livro");
		
		Livro livro = null;
		try {
			livro = livroRepository.buscarPorId(id);
			
			if(livro == null) {
				throw new BusinessException("O livro com id fonecido não está cadastrado no sistema.");
			}
		}catch(ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
		
		return livro;
	}
	
	public List<Livro> listarLivros(){
		
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			livros.addAll(livroRepository.buscarTodos());
		}catch(ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}
		
		if(livros.isEmpty()) {
			throw new BusinessException("Não existe livros cadastrados");
		}
		
		return livros;
	}
	
	public void deletarLivro(String idLivro) {
		
		try {
			
			int id = validarId(idLivro, "Id Livro");
			
			Livro livro = livroRepository.buscarPorId(id);
			
			if(livro == null) {
				throw new BusinessException("O livro com id fonecido não está cadastrado no sistema.");
			}
			
			livroRepository.deletar(livro.getIdItem());
			
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}
	
	public void atualizarLivro(String idLivro, String titulo, String autor, String editora, String anoPublicacao, String genero,
			String idioma, String numeroPaginas, String isbn){
		
		int id = validarId(idLivro, "Id Livro");
		
		validarCampo(titulo, "Título");

		validarCampo(autor, "Autor");

		validarCampo(editora, "Editora");

		validarCampo(anoPublicacao, "Ano de Publicação");

		int parseoAnoPublicacao;
		try {
			parseoAnoPublicacao = Integer.parseInt(anoPublicacao);
		} catch (NumberFormatException e) {
			throw new BusinessException("O ano da publicação precisar ser numérico.");
		}
		int anoAtual = LocalDate.now().getYear();
		if (parseoAnoPublicacao > anoAtual) {
			throw new BusinessException("O ano digitado é maior que o ano atual");
		}

		validarCampo(genero, "Gênero");

		validarCampo(idioma, "Idioma");

		validarCampo(numeroPaginas, "Número de Páginas");
		int parseoNumeroPaginas;
		try {
			parseoNumeroPaginas = Integer.parseInt(numeroPaginas);
		} catch (NumberFormatException e) {
			throw new BusinessException("O número de páginas precisa ser numérico.");
		}

		if (parseoNumeroPaginas < 1) {
			throw new BusinessException("O número de páginas precisa ser maior que 1");
		}

		if (isbn != null && !isbn.isBlank()) {
			if (isbn.length() != 10 && isbn.length() != 13) {
				throw new BusinessException("ISBN informado é inválido, não contem 10 ou 13 caracteres");
			}
		}
		
		Livro livro = new Livro(titulo, autor, editora, parseoAnoPublicacao, genero, idioma, parseoNumeroPaginas, isbn);
		livro.setIdItem(id);
		
		try {
			
			Livro livroExiste = livroRepository.buscarPorId(livro.getIdItem());
			
			if(livroExiste == null) {
				throw new BusinessException("O livro fonecido não está cadastrado no sistema.");
			}
			
			livroRepository.atualizar(livro);
			
			
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
	}
	
	private void validarCampo(String valor, String campo) throws BusinessException {
		if (valor == null || valor.isBlank()) {
			throw new BusinessException(campo + " não pode ser nulo ou vazio!");
		}
	}
	
	private int validarId(String idItem, String campo) {
		
		validarCampo(idItem, campo);
		int parseIdItem;
		try {
			parseIdItem = Integer.parseInt(idItem);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}
		
		if(parseIdItem <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}
		
		return parseIdItem;
	}
	
}

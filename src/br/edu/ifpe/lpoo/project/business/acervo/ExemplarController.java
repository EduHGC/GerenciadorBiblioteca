package br.edu.ifpe.lpoo.project.business.acervo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.ExemplarRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
import br.edu.ifpe.lpoo.project.enums.TipoItem;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class ExemplarController {

	private ExemplarRepository exemplarRepository;

	public ExemplarController(ExemplarRepository exemplarRepository) {
		this.exemplarRepository = exemplarRepository;
	}

	public ExemplarController() {
		this(new ExemplarRepository());
	}

	public void cadastarExemplar(String idLivro, String quantidade) {

		if (idLivro == null || idLivro.isBlank()) {
			throw new BusinessException("O Id não pode ser nulo ou vazio!");
		}

		int parseIdItem;
		try {
			parseIdItem = Integer.parseInt(idLivro);
		} catch (NumberFormatException e) {
			throw new BusinessException("O id precisa ser numérico para fazer a busca.");
		}

		if (parseIdItem <= 0) {
			throw new BusinessException("O id precisa ser maior que zero.");
		}

		if (quantidade == null || quantidade.isBlank()) {
			throw new BusinessException("A quantidade não pode ser nula ou vazia!");
		}

		int parseQtd;
		try {
			parseQtd = Integer.parseInt(quantidade);
		} catch (NumberFormatException e) {
			throw new BusinessException("A quantidade precisa ser numérica.");
		}

		if (parseQtd <= 0) {
			throw new BusinessException("A quantidade ser maior que zero.");
		}

		try {

			List<Exemplar> exemplares = exemplarRepository.buscarTodosPorIdItem(parseIdItem);

			if (exemplares.isEmpty()) {
				throw new BusinessException("Não existe exemplares cadastrados para este livro");
			}

			int totalAtual = exemplares.size();

			for (int i = totalAtual + 1; i <= totalAtual + parseQtd; i++) {
				String registro = idLivro + "EXPLIV" + i;
				Exemplar exemplar = new Exemplar(parseIdItem, registro, TipoItem.LIVRO, StatusExemplar.DISPONIVEL);
				exemplarRepository.inserir(exemplar);
			}

		} catch (ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}
	}

	public List<Exemplar> listarExemplares() {

		List<Exemplar> exemplares = new ArrayList<Exemplar>();

		try {
			exemplares.addAll(exemplarRepository.buscarTodos());
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}

		if (exemplares.isEmpty()) {
			throw new BusinessException("Não existe exemplares para este livro");
		}

		return exemplares;
	}

	public List<Exemplar> listarExemplaresIdItem(int idItem) {

		List<Exemplar> exemplares = new ArrayList<Exemplar>();

		try {
			exemplares.addAll(exemplarRepository.buscarTodosPorIdItem(idItem));
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}

		if (exemplares.isEmpty()) {
			throw new BusinessException("Não existe exemplares para este livro");
		}

		return exemplares;
	}

}

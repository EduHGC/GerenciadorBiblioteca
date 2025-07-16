package br.edu.ifpe.lpoo.project.business.gerenciamento;

import java.time.LocalDate;

import br.edu.ifpe.lpoo.project.data.implement.EmprestimoRepository;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class EmprestimoController {

	private EmprestimoRepository emprestimoRepository;

	public EmprestimoController(EmprestimoRepository emprestimoRepository) {
		this.emprestimoRepository = emprestimoRepository;
	}

	public EmprestimoController() {
		this(new EmprestimoRepository());
	}

	public void registrar(String idExemplar, String idUsuario, String idBibliotecario, LocalDate dataEmprestimo,
			LocalDate dataParaDevolucao, String statusEmprestimo) {

		int exemplar = parseId(idExemplar, "Campo id exemplar");
		int usuario = parseId(idUsuario, "Campo id usuário");
		int bibliotecario = parseId(idBibliotecario, "Campo id usuário");

		Emprestimo emprestimo = new Emprestimo(exemplar, usuario, bibliotecario, dataEmprestimo, dataParaDevolucao,
				StatusEmprestimo.statusEmprestimo(statusEmprestimo));
		
		try {
			
			emprestimoRepository.inserir(emprestimo);
			
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
		
	}

	private int parseId(String id, String campo) {

		int parse;
		try {
			parse = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new BusinessException(campo + " não tem id númerico");
		}

		return parse;
	}
}

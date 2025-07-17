package br.edu.ifpe.lpoo.project.business.gerenciamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.EmprestimoRepository;
import br.edu.ifpe.lpoo.project.data.implement.ExemplarRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.CardEmprestimo;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;
import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
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
	
	public List<Emprestimo> listarEmprestimos(){
		
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			emprestimos.addAll(emprestimoRepository.listarTodos());
		}catch(ExceptionDb e) {
			throw new BusinessException("Erro: " + e.getMessage());
		}
		
		if(emprestimos.isEmpty()) {
			throw new BusinessException("Não existe registro de empréstimos");
		}
		
		return emprestimos;
	}

	
	public CardEmprestimo buscarCardEmprestimo(int idEmpestimo) {
		CardEmprestimo cardEmprestimo = null;
		try {
			cardEmprestimo = emprestimoRepository.cardEmprestimo(idEmpestimo);
			
			if(cardEmprestimo == null) {
				throw new BusinessException("O empréstimo com id fonecido não está cadastrado no sistema.");
			}
		}catch ( ExceptionDb e) {
			throw new BusinessException("Erro no banco de dados: " + e.getMessage());
		}
		
		return cardEmprestimo;
	}
	
	public Emprestimo buscarEmprestimoPorId(int idEmprestimo) {
	    Emprestimo emprestimo = null;
	    try {
	        emprestimo = emprestimoRepository.buscarEmprestimoPorId(idEmprestimo);
	        if (emprestimo == null) {
	            throw new BusinessException("Empréstimo com o id fornecido não foi encontrado.");
	        }
	    } catch (ExceptionDb e) {
	        throw new BusinessException("Erro no banco de dados: " + e.getMessage());
	    }
	    return emprestimo;
	}

	
	public void atualizarEmprestimoFinalizar(Emprestimo emprestimo) {
		
		if (emprestimo == null) {
	        throw new BusinessException("Empréstimo para atualização não pode ser nulo.");
	    }

	    try {
	    	ExemplarRepository exemplarRepository = new ExemplarRepository();
	        emprestimoRepository.atualizar(emprestimo);
	        Exemplar exemplar = exemplarRepository.buscarPorId(emprestimo.getIdExemplar());
	        exemplar.setStatus(StatusExemplar.DISPONIVEL);
	        exemplarRepository.atualizar(exemplar);
	    } catch (ExceptionDb e) {
	        throw new BusinessException("Erro no banco de dados: " + e.getMessage());
	    }
	}
	
	public void atualizarStatusEmprestimos() {
	    try {
	        emprestimoRepository.atualizarStatusAtrasados();
	    } catch (ExceptionDb e) {
	        throw new BusinessException("Erro no banco de dados: " + e.getMessage());
	    }
	}
	
	public List<Emprestimo> listarEmprestimosAtrasados() {
	    List<Emprestimo> emprestimos = new ArrayList<>();

	    try {
	        emprestimos = emprestimoRepository.listarTodosAtrazados();
	    } catch (ExceptionDb e) {
	        throw new BusinessException("Erro ao buscar empréstimos atrasados: " + e.getMessage());
	    }

	    if (emprestimos.isEmpty()) {
	        throw new BusinessException("Nenhum empréstimo atrasado encontrado.");
	    }

	    return emprestimos;
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

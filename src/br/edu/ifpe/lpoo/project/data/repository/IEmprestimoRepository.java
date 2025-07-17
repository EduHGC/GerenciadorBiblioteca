package br.edu.ifpe.lpoo.project.data.repository;

import java.util.List;

import br.edu.ifpe.lpoo.project.entities.gerenciamento.CardEmprestimo;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;

public interface IEmprestimoRepository {
	
	void inserir(Emprestimo emprestimo);
	
	List<Emprestimo> listarTodos();
	
	void atualizar(Emprestimo emprestimo);
	
	Emprestimo buscarEmprestimoPorId(int id);
	
	CardEmprestimo cardEmprestimo(int idEmprestimo);
}

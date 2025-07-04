package br.edu.ifpe.lpoo.project.data.repository;

import java.sql.Connection;
import java.util.List;

import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;

public interface IExemplarRepository {

	void inserir(Exemplar exemplar, int idLivro);
	
	boolean existe(Exemplar exemplar);
	
	void deletarComLivro(int idLivro, Connection conn);
	
	void deletar(Exemplar exemplar);
	
	void atualizar(Exemplar exemplar);

	public Exemplar buscarPorId(int idItem);

	public List<Exemplar> buscarTodosPorIdLivro(int idItem);
}

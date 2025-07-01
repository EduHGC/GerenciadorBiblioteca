package br.edu.ifpe.lpoo.project.data.repository;

import java.util.List;

import br.edu.ifpe.lpoo.project.entities.acervo.Livro;

public interface ILivroReposiotry {

	int inserir(Livro livro);

	boolean existe(Livro livro);

	Livro buscarPorId(int idLivro);

	void deletar(int idLivro);

	List<Livro> buscarTodos();

	List<Livro> buscarPorTermo(String termo);

	void atualizar(Livro livro);
}

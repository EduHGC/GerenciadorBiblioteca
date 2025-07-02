package br.edu.ifpe.lpoo.project.data.repository;

import java.util.List;

import br.edu.ifpe.lpoo.project.entities.acervo.Periodico;

public interface IPeriodicoRepository {

	int inserir(Periodico periodico);

	boolean existe(Periodico periodico);

	Periodico buscarPorId(int idPeriodico);

	void deletar(int idPeriodico);

	List<Periodico> buscarTodos();

	List<Periodico> buscarPorTermo(String termo);

	void atualizar(Periodico periodico);
}

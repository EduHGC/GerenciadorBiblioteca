package br.edu.ifpe.lpoo.project.data.repository;

import java.sql.Connection;

public interface IExemplarRepository {

	void deletar(int idLivro, Connection conn);
}

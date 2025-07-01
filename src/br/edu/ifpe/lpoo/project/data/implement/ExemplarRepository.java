package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpe.lpoo.project.data.repository.IExemplarRepository;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class ExemplarRepository implements IExemplarRepository {

	@Override
	public void deletar(int idLivro, Connection conn) throws ExceptionDb {

		if (conn == null) {
			throw new ExceptionDb("Conexão com o banco é nula para deletar exemplares");
		}

		if (idLivro <= 0) {
			throw new ExceptionDb("Id inválido");
		}

		String sql = "DELETE FROM exemplar WHERE id_livro = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idLivro);

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao deletar exemplares");
		}
	}
}

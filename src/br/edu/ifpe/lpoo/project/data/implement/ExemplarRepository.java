package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IExemplarRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class ExemplarRepository implements IExemplarRepository {
	
	private Exemplar instanciarExemplar(ResultSet rst) throws SQLException {

		int idExemplar = rst.getInt("id_exemplar");
		int idLivro = rst.getInt("id_livro");
		String registro = rst.getString("registro");
		String status = rst.getString("status_exemplar").toUpperCase();
		StatusExemplar statusExemplar = StatusExemplar.valueOf(status);

		Exemplar exemplar = new Exemplar(idLivro, registro, statusExemplar);
		exemplar.setIdExemplar(idExemplar);

		return exemplar;
	}
	
	@Override
	public void deletarComLivro(int idLivro, Connection conn) throws ExceptionDb {

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

	@Override
	public void inserir(Exemplar exemplar, int idLivro) {

		if (exemplar == null) {
			throw new ExceptionDb("O objeto do tipo exemplar não pode ser null");
		}

		if (idLivro <= 0) {
			throw new ExceptionDb("Id inválido");
		}

		String sql = "INSERT INTO exemplar (id_livro, registro, status_exemplar) VALUES (?, ?, ?)";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, exemplar.getIdLivro());
			stmt.setString(2, exemplar.getRegistro());
			stmt.setString(3, exemplar.getStatus().name());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao cadastrar exemplar no banco de dados: " + e.getMessage());
		}
	}

	@Override
	public boolean existe(Exemplar exemplar) {

		if (exemplar == null) {
			throw new ExceptionDb("O objeto do tipo exemplar não pode ser null");
		}

		boolean existe = false;

		String sql = "SELECT * FROM exemplar WHERE id_livro = ? AND registro = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, exemplar.getIdLivro());
			stmt.setString(2, exemplar.getRegistro());

			try (ResultSet rst = stmt.executeQuery()) {

				if (rst.next()) {
					existe = true;
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar exemplar no banco de dados: " + e.getMessage());
		}

		return existe;
	}

	@Override
	public void deletar(Exemplar exemplar) {

		if (exemplar == null) {
			throw new ExceptionDb("O objeto do tipo exemplar não pode ser null");
		}

		String sql = "DELETE FROM exemplar WHERE id_exemplar = ? AND id_livro = ? AND registro = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, exemplar.getIdExemplar());
			stmt.setInt(2, exemplar.getIdLivro());
			stmt.setString(3, exemplar.getRegistro());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao deletar exemplar no banco de dados: " + e.getMessage());
		}

	}

	@Override
	public void atualizar(Exemplar exemplar) {

		if (exemplar == null) {
			throw new ExceptionDb("O objeto do tipo exemplar não pode ser null");
		}

		String sql = "UPDATE exemplar SET id_livro = ?, registro = ?, status_exemplar = ? WHERE exemplar_id = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, exemplar.getIdLivro());
			stmt.setString(2, exemplar.getRegistro());
			stmt.setString(3, exemplar.getStatus().name());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao atualizar exemplar no banco de dados: " + e.getMessage());
		}

	}

	@Override
	public Exemplar buscarPorId(int idItem) {

		if (idItem <= 0) {
			throw new ExceptionDb("Id inválido");
		}

		Exemplar exemplar = null;

		String sql = "SELECT * FROM exemplar WHERE id_exemplar = ?";
		
		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idItem);
			
			try(ResultSet rst = stmt.executeQuery()){
				
				if(rst.next()) {
					exemplar = instanciarExemplar(rst);
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao atualizar exemplar no banco de dados: " + e.getMessage());
		}
		
		return exemplar;
	}

	@Override
	public List<Exemplar> buscarTodosPorIdLivro(int idItem) {
		
		if (idItem <= 0) {
			throw new ExceptionDb("Id inválido");
		}
		
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		Exemplar exemplar = null;
		
		String sql = "SELECT * FROM exemplar WHERE id_livro = ?";
		
		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idItem);
			
			try(ResultSet rst = stmt.executeQuery()){
				
				if(rst.next()) {
					exemplar = instanciarExemplar(rst);
					exemplares.add(exemplar);
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao atualizar exemplar no banco de dados: " + e.getMessage());
		}
		
		return exemplares;
	}
}

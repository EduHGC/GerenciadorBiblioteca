package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IExemplarRepository;
import br.edu.ifpe.lpoo.project.data.repository.ILivroRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class LivroRepository implements ILivroRepository {

	private Livro instanciarLivro(ResultSet rst) throws SQLException {

		int idLivro = rst.getInt("id_livro");
		String titulo = rst.getString("titulo");
		String autor = rst.getString("autor");
		int anoPublicacao = rst.getInt("ano_publicacao");
		String editora = rst.getString("editora");
		String idioma = rst.getString("idioma");
		String isbn = rst.getString("isbn");
		int numeroPaginas = rst.getInt("numero_paginas");
		String genero = rst.getString("genero");

		Livro livro = new Livro(titulo, autor, anoPublicacao, editora, idioma, isbn, numeroPaginas, genero);
		livro.setId(idLivro);

		return livro;
	}

	@Override
	public int inserir(Livro livro) {

		if (livro == null) {
			throw new ExceptionDb("O objeto do tipo livro não pode ser null");
		}

		int idLivro = -1;

		String sql = "INSERT INTO livro (isbn, numero_paginas, genero, titulo, autor, ano_publicacao, editora, idioma) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionDb.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, livro.getIsbn());
			stmt.setInt(2, livro.getNumeroPaginas());
			stmt.setString(3, livro.getGenero());
			stmt.setString(4, livro.getTitulo());
			stmt.setString(5, livro.getAutor());
			stmt.setInt(6, livro.getAnoPublicacao());
			stmt.setString(7, livro.getEditora());
			stmt.setString(8, livro.getIdioma());

			stmt.executeUpdate();

			try (ResultSet rst = stmt.getGeneratedKeys()) {

				if (rst.next()) {
					idLivro = rst.getInt(1);
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao inserir livro no banco de dados: " + e.getMessage());
		}

		return idLivro;
	}

	@Override
	public boolean existe(Livro livro) {

		if (livro == null) {
			throw new ExceptionDb("O objeto do tipo livro não pode ser null");
		}

		boolean existe = false;

		String sql = "SELECT * FROM livro "
				+ "WHERE isbn = ? OR (titulo = ? AND editora = ? AND autor = ?  AND  ano_publicacao = ?  AND  idioma = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, livro.getIsbn());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getEditora());
			stmt.setString(4, livro.getAutor());
			stmt.setInt(5, livro.getAnoPublicacao());
			stmt.setString(6, livro.getIdioma());

			try (ResultSet rst = stmt.executeQuery()) {

				while (rst.next()) {
					existe = true;
				}

			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar livro no banco de dados: " + e.getMessage());
		}

		return existe;
	}

	@Override
	public Livro buscarPorId(int idLivro) {

		if (idLivro <= 0) {
			throw new ExceptionDb("O id inválido");
		}

		Livro livro = null;

		String sql = "SELECT * FROM livro WHERE id_livro = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idLivro);

			try (ResultSet rst = stmt.executeQuery()) {

				while (rst.next()) {
					livro = instanciarLivro(rst);
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao buscar livro por id: " + e.getMessage());
		}

		return livro;
	}

	@Override
	public void deletar(int idLivro) {

		if (idLivro <= 0) {
			throw new ExceptionDb("O id inválido");
		}

		Connection conn = null;

		String sql = "DELETE FROM livro WHERE id_livro = ?";

		try {

			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			IExemplarRepository exemplarRepository = new ExemplarRepository();
			exemplarRepository.deletar(idLivro, conn);

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, idLivro);

				stmt.executeUpdate();

			}

			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					throw new ExceptionDb("Erro na transação ao tentar deletar livro e exemplares: " + e1.getMessage());
				}
			}
			throw new ExceptionDb("Erro na transação ao tentar deletar livro e exemplares: " + e.getMessage());
		} finally {
			ConnectionDb.closeConnection(conn);
		}
	}

	@Override
	public List<Livro> buscarTodos() {

		List<Livro> livros = new ArrayList<>();

		String sql = "SELECT * FROM livro";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {
					livros.add(instanciarLivro(rst));
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar Livros" + e.getMessage());
		}

		return livros;
	}

	@Override
	public List<Livro> buscarPorTermo(String termo) {
		if (termo == null) {
			throw new ExceptionDb("O termo de pesquisa não pode ser null");
		}

		List<Livro> livros = new ArrayList<>();

		String termoBusca = "%" + termo.toLowerCase() + "%";

		String sql = "SELECT * FROM livro WHERE " + "LOWER(titulo) LIKE ? " + "OR LOWER(isbn) LIKE ? "
				+ "OR LOWER(autor) LIKE ? " + "ORDER BY titulo";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, termoBusca);
			stmt.setString(2, termoBusca);
			stmt.setString(3, termoBusca);

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {

					livros.add(instanciarLivro(rst));
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar Livros: " + e.getMessage());
		}
		return livros;
	}

	@Override
	public void atualizar(Livro livro) {

		if (livro == null) {
			throw new ExceptionDb("Objeto tipo Livro não pode ser null");
		}

		String sql = "UPDATE livro "
				+ "SET isbn = ?, numero_paginas = ?, genero = ?, titulo = ?, autor = ?, ano_publicacao = ?, editora = ?, idioma = ? "
				+ "WHERE id_livro = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, livro.getIsbn());
			stmt.setInt(2, livro.getNumeroPaginas());
			stmt.setString(3, livro.getGenero());
			stmt.setString(4, livro.getTitulo());
			stmt.setString(5, livro.getAutor());
			stmt.setInt(6, livro.getAnoPublicacao());
			stmt.setString(7, livro.getEditora());
			stmt.setString(8, livro.getIdioma());
			stmt.setInt(9, livro.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao atualizar livro: " + e.getMessage());
		}
	}
}

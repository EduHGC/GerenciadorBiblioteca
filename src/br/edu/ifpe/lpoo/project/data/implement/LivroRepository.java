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

		int idItem = rst.getInt("id_item");
		String titulo = rst.getString("titulo");
		String autor = rst.getString("autor");
		int anoPublicacao = rst.getInt("ano_publicacao");
		String editora = rst.getString("editora");
		String idioma = rst.getString("idioma");
		String isbn = rst.getString("isbn");
		int numeroPagina = rst.getInt("numero_pagina");
		String genero = rst.getString("genero");

		Livro livro = new Livro(titulo, autor, editora, anoPublicacao, genero, idioma, numeroPagina, isbn);
		livro.setIdItem(idItem);

		return livro;
	}

	@Override
	public int inserir(Livro livro) {

		if (livro == null) {
			throw new ExceptionDb("O objeto do tipo livro não pode ser null");
		}

		int idLivro = -1;

		String sqlAcervo = "INSERT INTO item_acervo (titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sqlLivro = "INSERT INTO livro (id_livro, isbn) VALUES (?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rst = null;

		try {
			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(sqlAcervo, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setString(3, livro.getEditora());
			stmt.setInt(4, livro.getAnoPublicacao());
			stmt.setString(5, livro.getGenero());
			stmt.setString(6, livro.getIdioma());
			stmt.setInt(7, livro.getNumeroPaginas());
			stmt.executeUpdate();

			rst = stmt.getGeneratedKeys();
			if (rst.next()) {
				idLivro = rst.getInt(1);
			} else {
				throw new ExceptionDb("Não foi gerado id ao inserir um novo livro");
			}

			stmt1 = conn.prepareStatement(sqlLivro);
			stmt1.setInt(1, idLivro);
			stmt1.setString(2, livro.getIsbn());
			stmt1.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb("Erro no rollback ao inserir livro no banco de dados: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro ao inserir livro no banco de dados: " + e.getMessage());
		} finally {
			ConnectionDb.closeResultSet(rst);
			ConnectionDb.closeStatement(stmt);
			ConnectionDb.closeStatement(stmt1);
			ConnectionDb.closeConnection(conn);
		}

		return idLivro;
	}

	@Override
	public boolean existe(Livro livro) {

		if (livro == null) {
			throw new ExceptionDb("O objeto do tipo livro não pode ser null");
		}

		boolean existe = false;

		String sql = "SELECT * FROM item_acervo " + "INNER JOIN livro ON item_acervo.id_item = livro.id_livro "
				+ "WHERE livro.isbn = ? OR (item_acervo.titulo = ? AND item_acervo.editora = ? AND item_acervo.autor = ?  "
				+ "AND  item_acervo.ano_publicacao = ?  AND  item_acervo.idioma = ?)";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, livro.getIsbn());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getEditora());
			stmt.setString(4, livro.getAutor());
			stmt.setInt(5, livro.getAnoPublicacao());
			stmt.setString(6, livro.getIdioma());

			try (ResultSet rst = stmt.executeQuery()) {

				if (rst.next()) {
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

		String sql = "SELECT id_item, titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, "
				+ "isbn FROM item_acervo INNER JOIN livro ON item_acervo.id_item = livro.id_livro "
				+ "WHERE item_acervo.id_item = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idLivro);

			try (ResultSet rst = stmt.executeQuery()) {

				if (rst.next()) {
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

		String sqlItemAcervo = "DELETE FROM item_acervo WHERE id_item = ?";
		String sqlLivro = "DELETE FROM livro WHERE id_livro = ?";

		try {

			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			IExemplarRepository exemplarRepository = new ExemplarRepository();
			exemplarRepository.deletarItem(idLivro, conn);

			try (PreparedStatement stmt = conn.prepareStatement(sqlLivro);
					PreparedStatement stmt1 = conn.prepareStatement(sqlItemAcervo)) {

				stmt.setInt(1, idLivro);
				stmt.executeUpdate();
				
				stmt1.setInt(1, idLivro);
				stmt1.executeUpdate();

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

		String sql = "SELECT id_item, titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, "
				+ "isbn FROM item_acervo INNER JOIN livro ON item_acervo.id_item = livro.id_livro ";

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

		String termoBusca = "%" + termo.toLowerCase().trim() + "%";

		String sql = "SELECT id_item, titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, "
				+ "isbn FROM item_acervo INNER JOIN livro ON item_acervo.id_item = livro.id_livro " + "WHERE "
				+ "LOWER(titulo) LIKE ? " + "OR LOWER(isbn) LIKE ? " + "OR LOWER(autor) LIKE ? " + "ORDER BY titulo";

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

		String sqlItemAcervo = "UPDATE item_acervo SET titulo = ?, autor = ?, editora = ?, ano_publicacao = ?, genero = ?, idioma = ?, numero_pagina = ? "
				+ "WHERE id_item = ?";
		String sqlLivro = "UPDATE livro SET isbn = ? WHERE id_livro = ?";

		Connection conn = null;

		try {

			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement stmt = conn.prepareStatement(sqlItemAcervo);
					PreparedStatement stmt1 = conn.prepareStatement(sqlLivro)) {

				stmt.setString(1, livro.getTitulo());
				stmt.setString(2, livro.getAutor());
				stmt.setString(3, livro.getEditora());
				stmt.setInt(4, livro.getAnoPublicacao());
				stmt.setString(5, livro.getGenero());
				stmt.setString(6, livro.getIdioma());
				stmt.setInt(7, livro.getNumeroPaginas());
				stmt.setInt(8, livro.getIdItem());
				stmt.executeUpdate();

				stmt1.setString(1, livro.getIsbn());
				stmt1.setInt(2, livro.getIdItem());
				stmt1.executeUpdate();

				conn.commit();
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb("Erro no rollback ao atualizar Livro: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro no banco ao atualizar livro: " + e.getMessage());
		} finally {
			ConnectionDb.closeConnection(conn);
		}
	}
}

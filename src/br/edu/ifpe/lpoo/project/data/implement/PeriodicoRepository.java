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
import br.edu.ifpe.lpoo.project.data.repository.IPeriodicoRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Periodico;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class PeriodicoRepository implements IPeriodicoRepository {

	private Periodico instanciarPeriodico(ResultSet rst) throws SQLException {

		int idPeriodico = rst.getInt("id_item");
		String titulo = rst.getString("titulo");
		String autor = rst.getString("autor");
		String editora = rst.getString("editora");
		int anoPublicacao = rst.getInt("ano_publicacao");
		String genero = rst.getString("genero");
		String idioma = rst.getString("idioma");
		int numeroPagina = rst.getInt("numero_pagina");
		String issn = rst.getString("issn");
		int volume = rst.getInt("volume");
		int edicao = rst.getInt("edicao");

		Periodico periodico = new Periodico(titulo, autor, editora, anoPublicacao, genero, idioma, numeroPagina, issn,
				volume, edicao);
		periodico.setIdItem(idPeriodico);

		return periodico;
	}

	@Override
	public int inserir(Periodico periodico) {

		if (periodico == null) {
			throw new ExceptionDb("O objeto do tipo Periodico não pode ser null");
		}

		int idperiodico = -1;

		String sqlAcervo = "INSERT INTO item_acervo (titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sqlPeriodico = "INSERT INTO periodico (id_periodico, issn, volume, edicao) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rst = null;

		try {

			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(sqlAcervo, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, periodico.getTitulo());
			stmt.setString(2, periodico.getAutor());
			stmt.setString(3, periodico.getEditora());
			stmt.setInt(4, periodico.getAnoPublicacao());
			stmt.setString(5, periodico.getGenero());
			stmt.setString(6, periodico.getIdioma());
			stmt.setInt(7, periodico.getNumeroPaginas());
			stmt.executeUpdate();

			rst = stmt.getGeneratedKeys();
			if (rst.next()) {
				idperiodico = rst.getInt(1);
			} else {
				throw new ExceptionDb("Não foi gerado id ao inserir um novo periódico");
			}

			stmt1 = conn.prepareStatement(sqlPeriodico);
			stmt1.setInt(1, idperiodico);
			stmt1.setString(2, periodico.getIssn());
			stmt1.setInt(3, periodico.getVolume());
			stmt1.setInt(4, periodico.getEdicao());

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb("Erro no rollback ao inserir periódico no banco de dados: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro ao inserir periodico no banco de dados: " + e.getMessage());
		}

		return idperiodico;
	}

	@Override
	public boolean existe(Periodico periodico) {

		if (periodico == null) {
			throw new ExceptionDb("O objeto do tipo Periodico não pode ser null");
		}

		boolean existe = false;

		String sql = "SELECT titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, issn, volume, edicao "
				+ "FROM item_acervo INNER JOIN periodico ON item_acervo.id_item = periodico.id_periodico "
				+ "WHERE periodico.issn = ? OR (item_acervo.titulo = ? AND periodico.edicao = ? AND periodico.volume = ?)";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, periodico.getIssn());
			stmt.setString(2, periodico.getTitulo());
			stmt.setInt(3, periodico.getEdicao());
			stmt.setInt(4, periodico.getVolume());

			try (ResultSet rst = stmt.executeQuery()) {

				if (rst.next()) {
					existe = true;
				}

			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar periodico no banco de dados: " + e.getMessage());
		}

		return existe;
	}

	@Override
	public Periodico buscarPorId(int idPeriodico) {

		if (idPeriodico <= 0) {
			throw new ExceptionDb("O id inválido");
		}

		Periodico periodico = null;

		String sql = "SELECT id_item, titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, "
				+ "issn, volume, edicao FROM item_acervo INNER JOIN periodico ON item_acervo.id_item = periodico.id_periodico "
				+ "WHERE item_acervo.id_item = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPeriodico);

			try (ResultSet rst = stmt.executeQuery()) {
				if (rst.next()) {
					periodico = instanciarPeriodico(rst);
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao buscar periodico por id: " + e.getMessage());
		}

		return periodico;
	}

	@Override
	public void deletar(int idPeriodico) {

		if (idPeriodico <= 0) {
			throw new ExceptionDb("O id inválido");
		}

		Connection conn = null;

		String sqlItemAcervo = "DELETE FROM item_acervo WHERE id_item = ?";
		String sqlPeriodico = "DELETE FROM periodico WHERE id_periodico = ?";

		try {

			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);
			
			IExemplarRepository exemplarRepository = new ExemplarRepository();
			exemplarRepository.deletarItem(idPeriodico, conn);
			
			try (PreparedStatement stmt1 = conn.prepareStatement(sqlPeriodico);
					PreparedStatement stmt = conn.prepareStatement(sqlItemAcervo)) {

				stmt1.setInt(1, idPeriodico);
				stmt1.executeUpdate();
				
				stmt.setInt(1, idPeriodico);
				stmt.executeUpdate();

			}
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb("Erro no rollback ao deletar periodico por id: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro no banco ao deletar periodico por id: " + e.getMessage());
		} finally {
			ConnectionDb.closeConnection(conn);
		}
	}

	@Override
	public List<Periodico> buscarTodos() {

		List<Periodico> periodicos = new ArrayList<Periodico>();

		String sql = "SELECT id_item, titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, "
				+ "issn, volume, edicao FROM item_acervo INNER JOIN periodico ON item_acervo.id_item = periodico.id_periodico";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			try (ResultSet rst = stmt.executeQuery()) {

				while (rst.next()) {
					periodicos.add(instanciarPeriodico(rst));
				}

			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao buscar periodicos: " + e.getMessage());
		}
		return periodicos;
	}

	@Override
	public List<Periodico> buscarPorTermo(String termo) {
		if (termo == null) {
			throw new ExceptionDb("O termo de pesquisa não pode ser null");
		}

		List<Periodico> periodicos = new ArrayList<>();

		String termoBusca = "%" + termo.toLowerCase().trim() + "%";

		String sql = "SELECT id_item, titulo, autor, editora, ano_publicacao, genero, idioma, numero_pagina, "
				+ "issn, volume, edicao FROM item_acervo INNER JOIN periodico ON item_acervo.id_item = periodico.id_periodico "
				+ "WHERE LOWER(issn) LIKE ? " + "OR LOWER(titulo) LIKE ? OR LOWER(autor) LIKE ? " + "ORDER BY titulo";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, termoBusca);
			stmt.setString(2, termoBusca);
			stmt.setString(3, termoBusca);

			try (ResultSet rst = stmt.executeQuery()) {

				while (rst.next()) {
					periodicos.add(instanciarPeriodico(rst));
				}

			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao buscar periodicos: " + e.getMessage());
		}

		return periodicos;
	}

	@Override
	public void atualizar(Periodico periodico) {

		if (periodico == null) {
			throw new ExceptionDb("Objeto tipo Livro não pode ser null");
		}

		String sqlItemAcervo = "UPDATE item_acervo SET titulo = ?, autor = ?, editora = ?, ano_publicacao = ?, genero = ?, idioma = ?, numero_pagina = ? "
				+ "WHERE id_item = ?";
		String sqlPeriodico = "UPDATE periodico SET issn = ?, volume = ?, edicao = ? WHERE id_periodico = ?";
		
		Connection conn = null; 
		
		try {
			
			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);
			
			try (PreparedStatement stmt = conn.prepareStatement(sqlItemAcervo); PreparedStatement stmt1 = conn.prepareStatement(sqlPeriodico)){
			
				stmt.setString(1, periodico.getTitulo());
				stmt.setString(2, periodico.getAutor());
				stmt.setString(3, periodico.getEditora());
				stmt.setInt(4, periodico.getAnoPublicacao());
				stmt.setString(5, periodico.getGenero());
				stmt.setString(6, periodico.getIdioma());
				stmt.setInt(7, periodico.getNumeroPaginas());
				stmt.setInt(8, periodico.getIdItem());
				stmt.executeUpdate();
				
				stmt1.setString(1, periodico.getIssn());
				stmt1.setInt(2, periodico.getVolume());
				stmt1.setInt(3, periodico.getEdicao());
				stmt1.setInt(4, periodico.getIdItem());
				stmt1.executeUpdate();
				
				conn.commit();
				
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb("Erro no roll back ao atualizar periodico: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro no banco ao atualizar periodico: " + e.getMessage());
		}finally {
			ConnectionDb.closeConnection(conn);
		}
	}
}

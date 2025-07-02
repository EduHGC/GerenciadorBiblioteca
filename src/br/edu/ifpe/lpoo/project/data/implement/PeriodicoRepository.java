package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IPeriodicoRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Periodico;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class PeriodicoRepository implements IPeriodicoRepository {

	private Periodico instanciarPeriodico(ResultSet rst) throws SQLException {

		int idPeriodico = rst.getInt("id_periodico");
		String titulo = rst.getString("titulo");
		String autor = rst.getString("autor");
		int anoPublicacao = rst.getInt("ano_publicacao");
		String editora = rst.getString("editora");
		String idioma = rst.getString("idioma");
		String issn = rst.getString("issn");
		int numeroEdicao = rst.getInt("numero_edicao");
		int volume = rst.getInt("volume");
		String genero = rst.getString("genero");

		Periodico periodico = new Periodico(titulo, autor, anoPublicacao, editora, idioma, issn, numeroEdicao, volume,
				genero);
		periodico.setId(idPeriodico);

		return periodico;
	}

	@Override
	public int inserir(Periodico periodico) {

		if (periodico == null) {
			throw new ExceptionDb("O objeto do tipo Periodico não pode ser null");
		}

		int idperiodico = -1;

		String sql = "INSERT INTO periodico (issn, titulo, autor, numero_edicao, volume, editora, idioma, ano_publicacao, genero) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionDb.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, periodico.getIssn());
			stmt.setString(2, periodico.getTitulo());
			stmt.setString(3, periodico.getAutor());
			stmt.setInt(4, periodico.getNumeroEdicao());
			stmt.setInt(5, periodico.getVolume());
			stmt.setString(6, periodico.getEditora());
			stmt.setString(7, periodico.getIdioma());
			stmt.setInt(8, periodico.getAnoPublicacao());
			stmt.setString(9, periodico.getGenero());

			stmt.executeUpdate();

			try (ResultSet rst = stmt.getGeneratedKeys()) {

				if (rst.next()) {
					idperiodico = rst.getInt(1);
				}
			}

		} catch (SQLException e) {
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

		String sql = "SELECT * FROM periodico WHERE issn = ? OR (titulo = ? AND numero_edicao = ? AND volume = ?)";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, periodico.getIssn());
			stmt.setString(2, periodico.getTitulo());
			stmt.setInt(3, periodico.getNumeroEdicao());
			stmt.setInt(4, periodico.getVolume());

			try (ResultSet rst = stmt.executeQuery()) {

				while (rst.next()) {
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

		String sql = "SELECT * FROM periodico WHERE id_periodico = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPeriodico);

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {
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

		String sql = "DELETE FROM periodico WHERE id_periodico = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPeriodico);

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao buscar periodico por id: " + e.getMessage());
		}
	}

	@Override
	public List<Periodico> buscarTodos() {

		List<Periodico> periodicos = new ArrayList<Periodico>();

		String sql = "SELECT * FROM periodico";

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
		
		String termoBusca = "%" + termo.toLowerCase() + "%";
		
		String sql = "SELECT * FROM periodico WHERE " + "LOWER(issn) LIKE ? " + "OR LOWER(titulo) LIKE ? "
				+ "OR LOWER(autor) LIKE ? " + "ORDER BY titulo";

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

		String sql = "UPDATE periodico "
				+ "SET issn = ?, titulo = ?, autor = ?, numero_edicao = ?, volume = ?, editora = ?, idioma = ?, ano_publicacao = ?, genero = ? "
				+ "WHERE id_periodico = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, periodico.getIssn());
			stmt.setString(2,  periodico.getTitulo());
			stmt.setString(3, periodico.getAutor());
			stmt.setInt(4, periodico.getNumeroEdicao());
			stmt.setInt(5, periodico.getVolume());
			stmt.setString(6, periodico.getEditora());
			stmt.setString(7, periodico.getIdioma());
			stmt.setInt(8, periodico.getAnoPublicacao());
			stmt.setString(9, periodico.getGenero());
			stmt.setInt(10, periodico.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ExceptionDb("Erro no banco ao atualizar periodico: " + e.getMessage());
		}
	}
}

package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IEmprestimoRepository;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.CardEmprestimo;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class EmprestimoRepository implements IEmprestimoRepository {

	@Override
	public void inserir(Emprestimo emprestimo) {

		if (emprestimo == null) {
			throw new ExceptionDb("O objeto do tipo Emprestimo não pode ser nulo para inserir.");
		}

		String sql = "INSERT INTO emprestimo (id_exemplar, id_usuario, id_bibliotecario, data_emprestimo, data_devolucao, status_emprestimo) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, emprestimo.getIdExemplar());
			stmt.setInt(2, emprestimo.getIdUsuario());
			stmt.setInt(3, emprestimo.getIdBibliotecario());
			stmt.setDate(4, Date.valueOf(emprestimo.getDataEmprestimo()));
			stmt.setDate(5, Date.valueOf(emprestimo.getDataDevolucao()));
			stmt.setString(6, emprestimo.getStatusEmprestimo().name());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb(
					"Erro ao resgistrar novo empréstimo no banco de dados: Causado por: " + e.getMessage());
		}
	}

	@Override
	public List<Emprestimo> listarTodos() {

		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

		String sql = "SELECT * FROM emprestimo";

		Emprestimo emprestimo;

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {

					int idEmprestimo = rst.getInt("id_emprestimo");
					int idExemplar = rst.getInt("id_exemplar");
					int idUsuario = rst.getInt("id_usuario");
					int idBibliotecario = rst.getInt("id_bibliotecario");
					LocalDate dataEmprestimo = rst.getDate("data_emprestimo").toLocalDate();
					LocalDate dataDevolucao = rst.getDate("data_devolucao").toLocalDate();
					Date dataRealDevolucaoSql = rst.getDate("data_devolucao");
					LocalDate dataRealDevolucao = dataRealDevolucaoSql != null ? dataRealDevolucaoSql.toLocalDate()
							: null;
					String status = rst.getString("status_emprestimo").toUpperCase();
					StatusEmprestimo statusEmprestimo = StatusEmprestimo.valueOf(status);

					emprestimo = new Emprestimo(idEmprestimo, idExemplar, idUsuario, idBibliotecario, dataEmprestimo,
							dataDevolucao, dataRealDevolucao, statusEmprestimo);

					emprestimos.add(emprestimo);

				}
			}

//			if(emprestimos.isEmpty()) {
//				throw new ExceptionDb("Não existe empréstimos registrados");
//			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar empréstimos no banco de dados: Causado por: " + e.getMessage());
		}

		return emprestimos;
	}

	public CardEmprestimo cardEmprestimo(int idEmprestimo) {

		String sql = "SELECT emprestimo.id_emprestimo, emprestimo.data_emprestimo, emprestimo.data_devolucao, emprestimo.data_real_devolucao, "
				+ "emprestimo.status_emprestimo, usuario.nome AS usuario_nome, usuario.cpf, funcionario.nome AS funcionario_nome, item_acervo.titulo, "
				+ "exemplar.id_exemplar, exemplar.id_item "
				+ "FROM emprestimo " + "INNER JOIN usuario ON usuario.id_usuario = emprestimo.id_usuario "
				+ "INNER JOIN funcionario ON funcionario.id_funcionario = emprestimo.id_bibliotecario "
				+ "INNER JOIN exemplar ON exemplar.id_exemplar = emprestimo.id_exemplar "
				+ "INNER JOIN item_acervo ON item_acervo.id_item = exemplar.id_item " + "WHERE emprestimo.id_emprestimo = ?";
		
		CardEmprestimo cardEmprestimo = null;
		
		try(Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, idEmprestimo);
			
			try(ResultSet rst = stmt.executeQuery()){
				if(rst.next()) {
			
			            String nomeUsuario = rst.getString("usuario_nome");
			            String cpfUsuario = rst.getString("cpf");
			            String nomeFuncionario = rst.getString("funcionario_nome"); 
			            String titulo = rst.getString("titulo");
			            LocalDate dataEmprestimo = rst.getDate("data_emprestimo") != null ? rst.getDate("data_emprestimo").toLocalDate() : null;
			            LocalDate dataParaDevolucao = rst.getDate("data_devolucao") != null ? rst.getDate("data_devolucao").toLocalDate() : null;
			            LocalDate dataRealDevolucao = rst.getDate("data_real_devolucao") != null ? rst.getDate("data_real_devolucao").toLocalDate() : null;
			            int idLivro = rst.getInt("id_item");
			            int idExemplar = rst.getInt("id_exemplar");
			            String status = rst.getString("status_emprestimo").toUpperCase();
			            StatusEmprestimo statusEmprestimo = StatusEmprestimo.valueOf(status);

			            cardEmprestimo = new CardEmprestimo(idEmprestimo, nomeUsuario, cpfUsuario, nomeFuncionario, titulo, dataEmprestimo, dataParaDevolucao, dataRealDevolucao, idLivro, idExemplar, statusEmprestimo);
			           
			        }
				
			}
			
		}catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar informações relarionadas à empréstimos no banco de dados: Causado por: " + e.getMessage());
		}
		
		return cardEmprestimo;
	}

}

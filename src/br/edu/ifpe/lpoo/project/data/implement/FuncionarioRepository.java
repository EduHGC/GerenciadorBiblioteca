package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IFuncionarioRepository;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.enums.Cargo;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class FuncionarioRepository implements IFuncionarioRepository {

	private Funcionario instanciarFuncionario(ResultSet rst) throws SQLException {

		int idFuncionario = rst.getInt("id_funcionario");
		String cpf = rst.getString("cpf");
		String nome = rst.getString("nome");
		String email = rst.getString("email");
		String senha = rst.getString("senha");
		String matricula = rst.getString("matricula");
		String tipo = rst.getString("tipo_funcionario").toUpperCase();
		Cargo cargo = Cargo.valueOf(tipo);
		boolean ativo = rst.getBoolean("ativo");
		String status = rst.getString("status_funcionario");
		StatusFuncionario statusFuncionario = StatusFuncionario.valueOf(status);

		Funcionario funcionario = new Funcionario(cpf, nome, email, senha, matricula, cargo, ativo,
				statusFuncionario);
		funcionario.setIdFuncionario(idFuncionario);

		return funcionario;
	}

	@Override
	public int inserir(Funcionario funcionario) {

		if (funcionario == null) {
			throw new ExceptionDb("O objeto do tipo Funcionário não pode ser null");
		}

		int idFuncionario = -1;

		String sql = "INSERT INTO funcionario (cpf, nome, email, senha, matricula, tipo_funcionario, ativo, status_funcionario) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, funcionario.getCpf());
			stmt.setString(2, funcionario.getNome());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getSenha());
			stmt.setString(5, funcionario.getMatricula());
			stmt.setString(6, funcionario.getCargo().name());
			stmt.setBoolean(7, funcionario.isLogado());
			stmt.setString(8, funcionario.getStatusFuncionario().name());
			stmt.executeUpdate();

			try (ResultSet rst = stmt.getGeneratedKeys()) {

				if (rst.next()) {
					idFuncionario = rst.getInt(1);
				} else {
					throw new ExceptionDb("Não foi gerado id ao inserir um novo funcionario");
				}
			}

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb(
						"Erro no rollback ao inserir o funcionário no banco de dados: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro ao inserir novo funcionário no banco de dados: Causado por: " + e.getMessage());
		} finally {
			ConnectionDb.closeStatement(stmt);
			ConnectionDb.closeConnection(conn);
		}

		return idFuncionario;
	}

	@Override
	public boolean existe(Funcionario funcionario) {

		if (funcionario == null) {
			throw new ExceptionDb("O objeto do tipo Funcionario não pode ser null");
		}

		String sql = "SELECT cpf FROM funcionario WHERE cpf = ? LIMIT 1";

		boolean existe = false;

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, funcionario.getCpf());

			try (ResultSet rst = stmt.executeQuery()) {

				if (rst.next()) {
					existe = true;
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao verificar existência do funcionário. Causado por: " + e.getMessage());
		}
		return existe;
	}

	@Override
	public void deletar(int idFuncionario) {

		if (idFuncionario <= 0) {
			throw new ExceptionDb("O id do funcionário não pode ser menor que 1.");
		}

		String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idFuncionario);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw new ExceptionDb("Erro ao deletar funcionário. Causado por: " + e.getMessage());
		}

	}

	@Override
	public void atualizar(Funcionario funcionario) {

		if (funcionario == null) {
			throw new ExceptionDb("O objeto do tipo livro não pode ser null");
		}

		String sql = "UPDATE funcionario SET cpf = ?, nome = ?, email = ?, matricula = ?, tipo_funcionario = ?, ativo = ?, status_funcionario = ? "
				+ "WHERE id_funcionario = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, funcionario.getCpf());
			stmt.setString(2, funcionario.getNome());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getMatricula());
			stmt.setString(5, funcionario.getCargo().name());
			stmt.setBoolean(6, funcionario.isLogado());
			stmt.setString(7, funcionario.getStatusFuncionario().name());
			stmt.setInt(8, funcionario.getIdFuncionario());
			stmt.executeUpdate();

		} catch (Exception e) {
			throw new ExceptionDb("Erro ao atualizar funcionário. Causado por: " + e.getMessage());
		}
	}

	@Override
	public Funcionario buscarPorId(int idFuncionario) {

		if (idFuncionario <= 0) {
			throw new ExceptionDb("O id do funcionário não pode ser menor que 1 para fazer a busca.");
		}

		String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";

		Funcionario funcionario = null;

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idFuncionario);

			try (ResultSet rst = stmt.executeQuery()) {
				if (rst.next()) {
					funcionario = instanciarFuncionario(rst);
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar funcionário por id. Causado por: " + e.getMessage());
		}

		return funcionario;
	}
	
	@Override
	public void atualizarSenha(Funcionario funcionario) {
		if (funcionario == null) {
			throw new ExceptionDb("O objeto do tipo livro não pode ser null");
		}

		String sql = "UPDATE funcionario SET senha = ? WHERE id_funcionario = ?";
		
		try(Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, funcionario.getSenha());
			stmt.setInt(2, funcionario.getIdFuncionario());
			stmt.executeUpdate();
		}catch (SQLException e){
			throw new ExceptionDb("Erro ao atualizar senha. Causado por: " + e.getMessage());
		}
	}
	
	@Override
	public List<Funcionario> buscarTodos() {

		String sql = "SELECT * FROM funcionario";

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {
					funcionarios.add(instanciarFuncionario(rst));
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar todos funcionários. Causado por: " + e.getMessage());
		}

		return funcionarios;
	}

}

package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IUsuarioRepository;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.enums.StatusUsuario;
import br.edu.ifpe.lpoo.project.enums.TipoUsuario;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class UsuarioRepository implements IUsuarioRepository {

	private Usuario instanciarUsuario(ResultSet rst) throws SQLException {

		int idUsuario = rst.getInt("id_usuario");
		String cpf = rst.getString("cpf");
		String matricula = rst.getString("matricula");
		String nome = rst.getString("nome");
		String email = rst.getString("email");
		String telefone = rst.getString("telefone");
		String departamento = rst.getString("departamento");
		String tipo = rst.getString("tipo_usuario").toUpperCase();
		TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
		String status = rst.getString("status_usuario");
		StatusUsuario statusUsuario = StatusUsuario.valueOf(status);
		String instituicao = rst.getString("instituicao");
		double debito = rst.getDouble("debito");

		Usuario usuario = new Usuario(cpf, matricula, nome, email, telefone, departamento, tipoUsuario, statusUsuario,
				instituicao, debito);
		usuario.setIdUsuario(idUsuario);

		return usuario;
	}

	public int inserir(Usuario usuario) {

		if (usuario == null) {
			throw new ExceptionDb("O objeto do tipo Usuario não pode ser null");
		}

		int idUsuario = -1;

		String sql = "INSERT INTO usuario (cpf, matricula, nome, email, telefone, departamento, tipo_usuario, status_usuario, instituicao, debito ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionDb.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getMatricula());
			stmt.setString(3, usuario.getNome());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getTelefone());
			stmt.setString(6, usuario.getDepartamentoCurso());
			stmt.setString(7, usuario.getTipoUsuario().name());
			stmt.setString(8, usuario.getStatusUsuario().name());
			stmt.setString(9, usuario.getInstituicao());
			stmt.setDouble(10, usuario.getDebito());
			stmt.executeUpdate();

			try (ResultSet rst = stmt.getGeneratedKeys()) {

				if (rst.next()) {
					idUsuario = rst.getInt(1);
				} else {
					throw new ExceptionDb("Erro ao criar id para o usuário");
				}
			}

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionDb("Erro no rollback ao cadastrar o novo usuário. Causado por: " + e1.getMessage());
			}
			throw new ExceptionDb("Erro ao cadastrar novo usuário. Causado por: " + e.getMessage());
			
		} finally {
			ConnectionDb.closeStatement(stmt);
			ConnectionDb.closeConnection(conn);
		}

		return idUsuario;
	}

	@Override
	public boolean existe(Usuario usuario) {

		if (usuario == null) {
			throw new ExceptionDb("O objeto do tipo Usuario não pode ser null");
		}

		boolean existe = false;

		String sql = "SELECT cpf FROM usuario WHERE cpf = ? LIMIT 1";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, usuario.getCpf());

			try (ResultSet rst = stmt.executeQuery()) {
				if (rst.next()) {
					existe = true;
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar existência do usuário. Causado por: " + e.getMessage());
		}
		return existe;
	}

	@Override
	public Usuario buscarPorId(int idUsuario) {

		if (idUsuario <= 0) {
			throw new ExceptionDb("Id precisa ser maior do que 0 para a busca");
		}

		String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

		Usuario usuario = null;

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idUsuario);

			try (ResultSet rst = stmt.executeQuery()) {
				if (rst.next()) {
					usuario = instanciarUsuario(rst);
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar usuário por id. Causado por: " + e.getMessage());
		}

		return usuario;
	}

	@Override
	public void deletar(int idUsuario) {

		if (idUsuario <= 0) {
			throw new ExceptionDb("Id precisa ser maior do que 0 para deletar");
		}

		String sql = "DELETE FROM usuario WHERE id_usuario = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idUsuario);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ExceptionDb("Erro deletar usuário. Causado por: " + e.getMessage());
		}

	}

	@Override
	public List<Usuario> buscarTodos() {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM usuario";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {
					usuarios.add(instanciarUsuario(rst));
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar todos usuários. Causado por: " + e.getMessage());
		}
		return usuarios;
	}

	@Override
	public List<Usuario> buscarPorTermo(String termo) {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		String termoBusca = "%" + termo.toLowerCase().trim() + "%";
		
		String sql = "SELECT id_usuario, cpf, matricula, nome, email, telefone, departamento, tipo_usuario, status_usuario, instituicao, debito FROM usuario "
				+ "WHERE LOWER(nome) LIKE ? OR LOWER(departamento) LIKE ? OR cpf LIKE ? OR LOWER(instituicao) LIKE ? ORDER BY nome";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, termoBusca);
			stmt.setString(2, termoBusca);
			stmt.setString(3, termoBusca);
			stmt.setString(4, termoBusca);

			try (ResultSet rst = stmt.executeQuery()) {
				while (rst.next()) {
					usuarios.add(instanciarUsuario(rst));
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar todos por termo usuários. Causado por: " + e.getMessage());
		}
		return usuarios;
	}

	@Override
	public void atualizar(Usuario usuario) {
		
		if(usuario == null) {
			throw new ExceptionDb("O objeto do tipo Usuario não pode ser null");
		}
		
		String sql = "UPDATE usuario SET cpf = ?, matricula = ?, nome = ?, email = ?, telefone = ?, departamento = ?, tipo_usuario = ?, status_usuario = ?, "
				+ "instituicao = ?, debito = ? WHERE id_usuario = ?";
		
		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getMatricula());
			stmt.setString(3, usuario.getNome());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getTelefone());
			stmt.setString(6, usuario.getDepartamentoCurso());
			stmt.setString(7, usuario.getTipoUsuario().name());
			stmt.setString(8, usuario.getStatusUsuario().name());
			stmt.setString(9, usuario.getInstituicao());
			stmt.setDouble(10, usuario.getDebito());
			stmt.setInt(11, usuario.getIdUsuario());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao atualizar usuário. Causado por: " + e.getMessage());
		}
	}
}

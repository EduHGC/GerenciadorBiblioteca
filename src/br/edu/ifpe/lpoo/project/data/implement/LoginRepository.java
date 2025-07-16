package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.ILoginRepository;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.enums.Cargo;
import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class LoginRepository implements ILoginRepository{

	@Override
	public Funcionario buscarFuncionario(String funcionarioNome) {
		
		if(funcionarioNome == null) {
			throw new ExceptionDb("O nome de Usuário não pode ser nulo");
		}
		
		String sql = "SELECT * FROM funcionario WHERE email = ? LIMIT 1";
		
		Funcionario funcionario = null;
		
		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, funcionarioNome);
			
			try(ResultSet rst = stmt.executeQuery()){
				if(rst.next()) {
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

					funcionario = new Funcionario(cpf, nome, email, senha, matricula, cargo, ativo, statusFuncionario);
					funcionario.setIdFuncionario(idFuncionario);
				}
			}
			
		}catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar funcionário no banco de dados. Causado por: " + e.getMessage());
		}
		return funcionario;
	}

}

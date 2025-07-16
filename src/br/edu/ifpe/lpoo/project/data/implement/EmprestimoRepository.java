package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.data.repository.IEmprestimoRepository;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class EmprestimoRepository implements IEmprestimoRepository{

	@Override
	public void inserir(Emprestimo emprestimo) {
		
		if(emprestimo == null) {
			throw new ExceptionDb("O objeto do tipo Emprestimo não pode ser nulo para inserir.");
		}
		
		String sql = "INSERT INTO emprestimo (id_exemplar, id_usuario, id_bibliotecario, data_emprestimo, data_devolucao, status_emprestimo)";
		
		try(Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, emprestimo.getIdExemplar());
			stmt.setInt(2, emprestimo.getIdUsuario());
			stmt.setInt(3, emprestimo.getIdBibliotecario());
			stmt.setDate(4, Date.valueOf(emprestimo.getDataEmprestimo()));
			stmt.setDate(5, Date.valueOf(emprestimo.getDataDevolucao()));
			stmt.setString(6,  emprestimo.getStatusEmprestimo().name());
			
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new ExceptionDb("Erro ao resgistrar novo empréstimo no banco de dados: Causado por: " + e.getMessage());
		}
	}

	
}

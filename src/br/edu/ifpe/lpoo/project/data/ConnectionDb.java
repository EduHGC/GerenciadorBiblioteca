package br.edu.ifpe.lpoo.project.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class ConnectionDb {

	public static Connection getConnection() throws ExceptionDb {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/testelivro", "root", "root");
		} catch (SQLException e) {
			throw new ExceptionDb("Falha na conexão com o banco de dados. Causado por: " + e.getMessage());
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ExceptionDb("Erro no fechamento da conexão. Causado por: " + e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement stmt) {
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ExceptionDb("Erro ao fechar o Statement. Causado por: " + e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rst) {
			
		if(rst != null) {
			try {
				rst.close();
			} catch (SQLException e) {
				throw new ExceptionDb("Erro ao fechar o ResultSet. Causado por: " + e.getMessage());
			}
		}
	}

}

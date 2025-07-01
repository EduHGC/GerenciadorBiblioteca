package br.edu.ifpe.lpoo.project.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public class ConnectionDb {

	public static Connection getConnection() throws ExceptionDb {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/testelivro", "root", "root");
		} catch (SQLException e) {
			throw new ExceptionDb("Falha na conexão com o banco de dados: " + e.getMessage());
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ExceptionDb("Erro no fechamento da conexão: " + e.getMessage());
			}
		}
	}

}

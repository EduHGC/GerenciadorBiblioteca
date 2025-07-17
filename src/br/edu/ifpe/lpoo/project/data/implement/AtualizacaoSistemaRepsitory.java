package br.edu.ifpe.lpoo.project.data.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.edu.ifpe.lpoo.project.data.ConnectionDb;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

import java.sql.Connection;
import java.sql.Date;

public class AtualizacaoSistemaRepsitory {

	public LocalDate buscarUltimaAtualizacao() {

		String sql = "SELECT data_ultima_atualizacao FROM atualizar_sistema WHERE id = ?";

		try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, 1);
			
			try (ResultSet rst = stmt.executeQuery()) {
				if (rst.next()) {
					return rst.getDate("data_ultima_atualizacao").toLocalDate();
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDb("Erro ao buscar última atualização de multas. Causado por: " + e.getMessage());
		}

		return LocalDate.of(2025, 1, 1);
	}
	
	public void atualizarDataUltimaAtualizacao(LocalDate dataAtual) {
	    String sql = "UPDATE atualizar_sistema SET data_ultima_atualizacao = ? WHERE id = 1";

	    try (Connection conn = ConnectionDb.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setDate(1, Date.valueOf(dataAtual));
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        throw new ExceptionDb("Erro ao atualizar data de atualização de multas: " + e.getMessage());
	    }
	}

}

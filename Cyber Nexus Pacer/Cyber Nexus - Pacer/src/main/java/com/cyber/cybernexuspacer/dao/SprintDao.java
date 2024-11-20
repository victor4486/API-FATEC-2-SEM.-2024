package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Sprint;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SprintDao {

    // Salva um novo sprint no banco de dados
    public int salvarSprint(Sprint sprint) {
        String sql = "INSERT INTO SPRINTS (NUM_SPRINT, DATA_INICIAL, DATA_FINAL) VALUES (?, ?, ?)";

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (sprint.getDataInicio() == null || sprint.getDataFim() == null) {
                throw new IllegalArgumentException("Datas não podem ser vazias");
            }

            stmt.setInt(1, sprint.getNumSprint());
            stmt.setDate(2, sprint.getDataInicio());
            stmt.setDate(3, sprint.getDataFim());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna o ID gerado
                } else {
                    throw new SQLException("Falha ao obter o ID gerado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lista todos os sprints no banco de dados
    public List<Sprint> listarSprints() throws SQLException {
        List<Sprint> sprints = new ArrayList<>();
        String sql = "SELECT * FROM SPRINTS";

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int numSprint = rs.getInt("num_sprint");
                Date dataInicial = rs.getDate("data_inicial");
                Date dataFinal = rs.getDate("data_final");

                Sprint sprint = new Sprint(id, numSprint, dataInicial, dataFinal);
                sprints.add(sprint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sprints;
    }

    // Deleta um sprint específico pelo ID
    public void deletarSprint(int id) throws SQLException {
        String sql = "DELETE FROM SPRINTS WHERE id = ?";

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar o sprint", e);
        }
    }

    // Obtém o sprint atual com base na data
    public static Sprint obterSprintAtual() throws SQLException {
        String sql = "SELECT * FROM SPRINTS WHERE CURDATE() BETWEEN DATA_INICIAL AND DATA_FINAL LIMIT 1";

        Sprint sprintAtual = null;

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                sprintAtual = new Sprint();
                sprintAtual.setId(rs.getInt("id"));
                sprintAtual.setNumSprint(rs.getInt("num_sprint"));
                sprintAtual.setDataInicio(rs.getDate("data_inicial"));
                sprintAtual.setDataFim(rs.getDate("data_final"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar o sprint atual", e);
        }

        return sprintAtual;
    }

    // Conta o total de sprints no banco de dados
    public int contarSprints() {
        String sql = "SELECT COUNT(*) FROM SPRINTS";
        int totalSprints = 0;

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totalSprints = rs.getInt(1); // Obtém o número total de sprints
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalSprints;
    }
}

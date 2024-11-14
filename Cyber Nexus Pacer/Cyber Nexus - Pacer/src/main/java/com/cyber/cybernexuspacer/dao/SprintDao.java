package com.cyber.cybernexuspacer.dao;


import com.cyber.cybernexuspacer.entity.Sprint;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SprintDao {
    public int salvarSprint(Sprint sprint) {
        // Lógica para conectar ao banco de dados e inserir a sprint
        String sql = "INSERT INTO SPRINTS (NUM_SPRINT, DATA_INICIAL, DATA_FINAL) VALUES (?, ?, ?)";

        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            if (sprint.getDataInicio() == null  ||  sprint.getDataFim() == null) {
                throw new IllegalArgumentException("Datas não podem ser vazias");
            }

            connection = ConexaoDao.getConnection();
            assert connection != null;
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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

    public List<Sprint> listarSprints() throws SQLException {
        List<Sprint> sprints = new ArrayList<>();
        String sql = "SELECT * FROM SPRINTS";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            assert connection != null;
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Itera sobre o ResultSet para preencher a lista de critérios
            while (rs.next()) {
                int id = rs.getInt("id");
                int numSprint = rs.getInt("num_sprint");
                Date dataInicial = rs.getDate("data_inicial");
                Date dataFinal = rs.getDate("data_final");

                // Cria um novo objeto Criterio e adiciona à lista
                Sprint sprint = new Sprint(id,numSprint, dataInicial, dataFinal);
                sprints.add(sprint);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return sprints;  // Retorna a lista de critérios
    }

    public void deletarSprint(int id) throws SQLException {
        String sql = "DELETE FROM SPRINTS WHERE id = ?";

        Connection connection = null;

        try {
            connection = ConexaoDao.getConnection();
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Tratar exceções, se necessário
            throw e;
        }
    }

    public static Sprint obterSprintAtual() throws SQLException {
        String sql = "SELECT * FROM SPRINTS WHERE CURDATE() BETWEEN DATA_INICIAL AND DATA_FINAL LIMIT 1";

        Sprint sprintAtual = null;

        // Estabelece a conexão com o banco
        try {
            Connection conn = ConexaoDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Verifica se encontrou algum sprint
            if (rs.next()) {
                // Cria o objeto Sprint com os dados retornados
                sprintAtual = new Sprint();
                sprintAtual.setId(rs.getInt("id"));
                sprintAtual.setNumSprint(rs.getInt("num_sprint"));
                sprintAtual.setDataInicio(rs.getDate("data_inicial"));
                sprintAtual.setDataFim(rs.getDate("data_final"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao buscar o sprint atual", e);
        }

        return sprintAtual;
    }

    public int contarSprints() {
        String sql = "SELECT COUNT(*) FROM sprints";
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


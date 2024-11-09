package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Sprint;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SprintDao {
    public int salvarSprint(Sprint sprint) {
        // Lógica para conectar ao banco de dados e inserir a sprint
        String sql = "INSERT INTO sprints (id, num_sprint, data_inicial, data_final) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = null;
        Connection connection = null;

        try  {

            if (sprint.getDataInicio() == null  ||  sprint.getDataFim() == null) {
                throw new IllegalArgumentException("Datas não podem ser vazias");
            }

            connection = ConexaoDao.getConnection();
            assert connection != null;
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, sprint.getId());
            stmt.setString(2, sprint.getNumSprint());
            stmt.setDate(3, sprint.getDataInicio());
            stmt.setDate(4, sprint.getDataFim());
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
                String numSprint = rs.getString("num_sprint");
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
        String sql = "DELETE FROM sprints WHERE id = ?";

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
}


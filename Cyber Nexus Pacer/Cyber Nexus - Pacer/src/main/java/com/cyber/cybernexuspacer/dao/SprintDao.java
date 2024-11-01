package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Criterio;
import com.cyber.cybernexuspacer.entity.PontuacaoGrupo;
import com.cyber.cybernexuspacer.entity.Sprint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SprintDao {
    public void salvarSprint(Sprint sprint) {
        // Lógica para conectar ao banco de dados e inserir a sprint
        String sql = "INSERT INTO sprints (num_sprint, data_inicial, data_final) VALUES (?, ?, ?)";

        PreparedStatement stmt = null;
        Connection connection = null;

        try  {

            if (sprint.getDataInicio() == null  ||  sprint.getDataFim() == null) {
                throw new IllegalArgumentException("Datas não podem ser vazias");
            }

            connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, sprint.getNumSprint());
            stmt.setDate(2, sprint.getDataInicio());
            stmt.setDate(3, sprint.getDataFim());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                String numSprint = rs.getString("num_sprint");
                Date dataInicial = rs.getDate("data_inicial");
                Date dataFinal = rs.getDate("data_final");

                // Cria um novo objeto Criterio e adiciona à lista
                Sprint sprint = new Sprint(numSprint, dataInicial, dataFinal);
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
}


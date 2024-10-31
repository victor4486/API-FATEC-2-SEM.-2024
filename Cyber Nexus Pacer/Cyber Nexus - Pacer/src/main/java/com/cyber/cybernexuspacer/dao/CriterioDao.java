package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Criterio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class    CriterioDao {

    public void inserirCriterio(String nome, String descricao) {
        String sql = "INSERT INTO CRITERIO (TITULO, DESCRICAO) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConexaoDao.getConnection(); // Obtém a conexão
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.executeUpdate(); // Executa a inserção

            System.out.println("Critério inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace(); // Captura corretamente a SQLException
        }finally {
            try {
                if (stmt != null) {
                    stmt.close(); // Fechando o PreparedStatement
                }
                // Remova ou comente a linha abaixo para manter a conexão aberta
                 //if (connection != null) {
                 //    connection.close(); // Fechando a conexão
                 //}
            } catch (SQLException e) {
                e.printStackTrace(); // Caso algum erro ocorra ao fechar recursos
            }
        }
    }

    public List<Criterio> listarCriterios() throws SQLException {
        List<Criterio> criterios = new ArrayList<>();
        String sql = "SELECT * FROM CRITERIO";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            assert connection != null;
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Itera sobre o ResultSet para preencher a lista de critérios
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                // Cria um novo objeto Criterio e adiciona à lista
                Criterio criterio = new Criterio(titulo, descricao);
                criterios.add(criterio);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return criterios;  // Retorna a lista de critérios
    }

}



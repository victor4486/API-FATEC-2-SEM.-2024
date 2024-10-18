package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Criterio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriterioDao {

    public void inserirCriterio(String nome, String descricao) {
        String sql = "INSERT INTO criterio (nome, descricao) VALUES (?, ?)";
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
                    // if (connection != null) {
                    //     connection.close(); // Fechando a conexão
                    // }
                } catch (SQLException e) {
                    e.printStackTrace(); // Caso algum erro ocorra ao fechar recursos
                }
            }
    }

}

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

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.executeUpdate();

            System.out.println("Critério inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
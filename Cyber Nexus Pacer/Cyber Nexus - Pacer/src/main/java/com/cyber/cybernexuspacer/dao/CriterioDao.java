package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Criterio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriterioDao {

    public void inserirCriterio(Criterio criterio) {
        String sql = "INSERT INTO CRITERIO (TITULO, DESCRICAO) VALUES (?, ?)";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, criterio.getTitulo());
            stmt.setString(2, criterio.getDescricao());
            stmt.executeUpdate();

            System.out.println("Critério inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirCriterio(int id) throws SQLException {
        String sql = "DELETE FROM CRITERIO WHERE ID = ?";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método listarCriterios para obter todos os critérios do banco de dados
    public List<Criterio> listarCriterios() throws SQLException {
        List<Criterio> criterios = new ArrayList<>();
        String sql = "SELECT * FROM CRITERIO";

        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String titulo = rs.getString("TITULO");
                String descricao = rs.getString("DESCRICAO");

                Criterio criterio = new Criterio(id, titulo, descricao);
                criterios.add(criterio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criterios;
    }
}

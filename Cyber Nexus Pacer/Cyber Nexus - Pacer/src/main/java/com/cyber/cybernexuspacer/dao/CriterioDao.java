package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Criterio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriterioDao {
    //CRUD Create
    public List<Criterio> getAllCriterios() {
        List<Criterio> criterios = new ArrayList<>();
        String sql = "SELECT * FROM Criterio";
        try (Connection conn = ConexaoDao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Criterio criterio = new Criterio(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));
                criterios.add(criterio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criterios;
    }

    // Outras operações de CRUD (insert, update, delete)
}
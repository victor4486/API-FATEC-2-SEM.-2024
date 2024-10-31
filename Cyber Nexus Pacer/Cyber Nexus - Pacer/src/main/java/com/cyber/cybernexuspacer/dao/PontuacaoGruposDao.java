package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.PontuacaoGrupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PontuacaoGruposDao {

    public static List<PontuacaoGrupo> pesquisarGrupos() throws SQLException {
        String sql = "SELECT  G.id, G.grupo AS grupo,  G.nota,  COUNT(A.id) AS integrantes\n" +
                "FROM   GRUPOS AS G\n" +
                "LEFT JOIN  ALUNOS AS A ON G.id = A.id_grupo\n" +
                "GROUP BY  G.id, G.grupo, G.nota;  ";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PontuacaoGrupo> pontGrupos = new ArrayList<>();

        try {

            stmt = ConexaoDao.getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();

            // Itera sobre o ResultSet para preencher a lista de critérios
            while (rs.next()) {
                int id = rs.getInt("id");
                String grupo = rs.getString("grupo");
                int integrantes = rs.getInt("integrantes");
                double nota = rs.getDouble("nota");

                // Cria um novo objeto Criterio e adiciona à lista
                PontuacaoGrupo pontuacaoGrupo = new PontuacaoGrupo(id,grupo, integrantes, nota);
                pontGrupos.add(pontuacaoGrupo);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return pontGrupos;

    }

    public static void salvarNota(Integer idGrupo, String novaNota) throws SQLException {
        String sql = "UPDATE GRUPOS SET NOTA = ? WHERE ID = ?";

        PreparedStatement stmt = null;

        try {

            stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt.setString(1, novaNota);
            stmt.setInt(2, idGrupo);
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (stmt != null) stmt.close();
        }

    }
}

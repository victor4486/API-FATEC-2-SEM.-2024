package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.PontuacaoGrupo;
import com.cyber.cybernexuspacer.entity.Sprint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PontuacaoGruposDao {

    public static List<PontuacaoGrupo> pesquisarGrupos() throws SQLException {
        String sql = "SELECT G.ID, G.GRUPO, NG.NOTA_GRUPO, COUNT(A.id) AS INTEGRANTES\n" +
                "FROM GRUPOS AS G\n" +
                "LEFT JOIN NOTAS_GRUPOS AS NG ON G.ID = NG.ID\n" +
                "LEFT JOIN ALUNOS AS A ON G.GRUPO = A.GRUPO\n" +
                "GROUP BY  G.ID, G.GRUPO;";

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
                int nota = rs.getInt("nota_grupo");

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

    public static void salvarNotaGrupo(String nomeGrupo, int num_sprint, int novaNota) throws SQLException {
        String sql = "UPDATE NOTAS_GRUPOS SET NOTA_GRUPO = ? WHERE GRUPO = ? AND NUM_SPRINT = ?";  // Usando o nome do grupo e o sprintId

        PreparedStatement stmt = null;

        try {

            stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt.setInt(1, novaNota);      // Nome do grupo
            stmt.setString(2, nomeGrupo);          // ID do sprint
            stmt.setInt(3, num_sprint);
            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (stmt != null) stmt.close();
        }

    }



}

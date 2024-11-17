package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AcompanharSprints;
import com.cyber.cybernexuspacer.entity.AreaDoAluno;
import com.cyber.cybernexuspacer.entity.PontuacaoGrupo;
import com.cyber.cybernexuspacer.session.AlunoSession;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcompanharSprintsDao {
    public List<PontuacaoGrupo> listarGrupos() throws SQLException {
        List<PontuacaoGrupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM grupos";  // Tabela de grupos
        try  {
            Connection connection = ConexaoDao.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PontuacaoGrupo grupo = new PontuacaoGrupo();
                grupo.setGrupo(rs.getString("grupo"));
                grupos.add(grupo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return grupos;
    }

    // Método para listar os alunos do mesmo grupo
    public List<AreaDoAluno> listarAlunosPorGrupo(String grupo) throws SQLException {
        List<AreaDoAluno> alunos = new ArrayList<>();

        String sqlAlunosDoGrupo = "SELECT ID, NOME, EMAIL, GRUPO FROM ALUNOS WHERE GRUPO = ?";


        PreparedStatement stmtAlunosDoGrupo = null;
        ResultSet rsAlunosDoGrupo = null;

        try {
            // Conexão com o banco de dados
            Connection connection = ConexaoDao.getConnection();
            assert connection != null;


            // Consulta para buscar os alunos do mesmo grupo
            stmtAlunosDoGrupo = connection.prepareStatement(sqlAlunosDoGrupo);
            stmtAlunosDoGrupo.setString(1, grupo);
            rsAlunosDoGrupo = stmtAlunosDoGrupo.executeQuery();


            // Itera sobre o ResultSet e preenche a lista de alunos
            while (rsAlunosDoGrupo.next()) {
                int idAluno = rsAlunosDoGrupo.getInt("id");
                String nome = rsAlunosDoGrupo.getString("nome");
                String email = rsAlunosDoGrupo.getString("email");

                // Cria e adiciona o aluno à lista
                AreaDoAluno aluno = new AreaDoAluno(idAluno,nome, email, grupo, "fatec2024", "Aluno");


                alunos.add(aluno);
            }



        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lança a exceção para ser tratada em um nível superior
        } finally {
            // Fechamento de recursos
            if (rsAlunosDoGrupo != null) rsAlunosDoGrupo.close();
            if (stmtAlunosDoGrupo != null) stmtAlunosDoGrupo.close();
        }

        return alunos;  // Retorna a lista de alunos
    }

    //Listar todos os alunos
    public List<AreaDoAluno> listarAlunos() throws SQLException {
        List<AreaDoAluno> alunos = new ArrayList<>();

        String  sql = "SELECT ID, NOME, EMAIL, GRUPO FROM ALUNOS";


        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Conexão com o banco de dados
            Connection connection = ConexaoDao.getConnection();
            assert connection != null;


            // Consulta para buscar os alunos do mesmo grupo
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();


            // Itera sobre o ResultSet e preenche a lista de alunos
            while (rs.next()) {
                int idAluno = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String grupo = rs.getString("grupo");

                // Cria e adiciona o aluno à lista
                AreaDoAluno aluno = new AreaDoAluno(idAluno,nome, email, grupo, "fatec2024", "Aluno");

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lança a exceção para ser tratada em um nível superior
        } finally {
            // Fechamento de recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return alunos;  // Retorna a lista de alunos
    }


}

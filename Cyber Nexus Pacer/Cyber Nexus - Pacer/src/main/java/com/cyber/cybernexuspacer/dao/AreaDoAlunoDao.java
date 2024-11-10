package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;
import com.cyber.cybernexuspacer.entity.Sprint;
import com.cyber.cybernexuspacer.session.AlunoSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDoAlunoDao {

    // Método para listar os alunos do mesmo grupo que o aluno logado
    public List<AreaDoAluno> listarAlunos() throws SQLException {
        List<AreaDoAluno> alunos = new ArrayList<>();

        // Verifica se o aluno está logado
        AreaDoAluno alunoLogado = AlunoSession.getAlunoLogado();
        if (alunoLogado == null) {
            throw new SQLException("Nenhum aluno logado.");
        }

        // Obtém o e-mail do aluno logado
        String emailAlunoLogado = alunoLogado.getEmail();

        // Consulta SQL para obter o id_grupo do aluno
        String sqlGrupo = "SELECT GRUPO FROM ALUNOS WHERE email = ?";
        String sqlAlunosDoGrupo = "SELECT ID, NOME, EMAIL, GRUPO FROM ALUNOS WHERE GRUPO = ?";


        PreparedStatement stmtGrupo = null;
        PreparedStatement stmtAlunosDoGrupo = null;
        ResultSet rsGrupo = null;
        ResultSet rsAlunosDoGrupo = null;

        try {
            // Conexão com o banco de dados
            Connection connection = ConexaoDao.getConnection();
            assert connection != null;

            // Obtém o id_grupo do aluno logado
            stmtGrupo = connection.prepareStatement(sqlGrupo);
            stmtGrupo.setString(1, emailAlunoLogado);
            rsGrupo = stmtGrupo.executeQuery();

            if (rsGrupo.next()) {
                String grupo = rsGrupo.getString("grupo");

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
                    AreaDoAluno aluno = new AreaDoAluno(nome, email, grupo, "fatec2024", "Aluno");
                    aluno.setIdAlunoReceptor(idAluno);
                    alunos.add(aluno);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lança a exceção para ser tratada em um nível superior
        } finally {
            // Fechamento de recursos
            if (rsGrupo != null) rsGrupo.close();
            if (rsAlunosDoGrupo != null) rsAlunosDoGrupo.close();
            if (stmtGrupo != null) stmtGrupo.close();
            if (stmtAlunosDoGrupo != null) stmtAlunosDoGrupo.close();
        }

        return alunos;  // Retorna a lista de alunos
    }

    public double buscaNota() throws SQLException {
        // Pegando sprint atual
        Sprint sprintAtual = SprintDao.obterSprintAtual();

        if (sprintAtual == null) {
            throw new SQLException("Nenhuma sprint ativa encontrada.");
        }

        String sql = "SELECT NOTA_GRUPO FROM NOTAS_GRUPOS WHERE GRUPO = ? AND NUM_SPRINT = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, AlunoSession.getAlunoLogado().getGrupo());
            stmt.setInt(2, sprintAtual.getNumSprint());
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("nota_grupo"); // Retorna a nota do grupo
            } else {
                throw new SQLException("Nota não encontrada para o grupo do aluno.");
            }

        } catch (SQLException e) {
            // Trate a exceção de forma adequada (log, rethrow, etc)
            throw new RuntimeException("Erro ao buscar a nota: " + e.getMessage(), e);
        } finally {
            if (rs != null) rs.close(); // Fechar o ResultSet
            if (stmt != null) stmt.close(); // Fechar o PreparedStatement
        }
    }
}


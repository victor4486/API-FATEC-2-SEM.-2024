package com.cyber.cybernexuspacer.session;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;

public class AlunoSession {
    // A variável estática que armazenará o aluno logado
    private static AreaDoAluno alunoLogado;

    // Método para obter o aluno logado
    public static AreaDoAluno getAlunoLogado() {
        return alunoLogado;
    }

    // Método para definir o aluno logado
    public static void setAlunoLogado(AreaDoAluno aluno) {
        alunoLogado = aluno;
    }

    // Método para limpar a sessão (quando o aluno sair)
    public static void limparAlunoLogado() {
        alunoLogado = null;
    }
}

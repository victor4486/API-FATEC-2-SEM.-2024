package com.cyber.cybernexuspacer.entity;

public class Aluno {

    private final String aluno;
    private String senha;
    private String email;
    private String tipo_usuario;
    private final String grupo;

    public Aluno(String aluno, String email, String grupo, String senha, String tipo_usuario) {
        this.aluno = aluno;
        this.email = email;
        this.grupo = grupo;
        this.senha = senha;
        this.tipo_usuario = tipo_usuario;
    }

    public String getAluno() { return aluno; }
    public String getEmail() { return email; }
    public String getGrupo() { return grupo; }
    public String getSenha() { return senha; }
    public String getTipo_usuario() { return tipo_usuario; }


}

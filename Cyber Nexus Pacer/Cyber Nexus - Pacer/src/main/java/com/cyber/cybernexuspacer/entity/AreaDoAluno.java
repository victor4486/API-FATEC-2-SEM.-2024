package com.cyber.cybernexuspacer.entity;

public class AreaDoAluno {

    private int idAlunoReceptor;
    private int idAlunoAvaliador;
    private String nome;
    private String senha;
    private String email;
    private String tipo_usuario;
    private String grupo;

    public AreaDoAluno(String nome, String email, String grupo, String senha, String tipo_usuario) {
        this.nome = nome;
        this.email = email;
        this.grupo = grupo;
        this.senha = senha;
        this.tipo_usuario = tipo_usuario;
    }

    public int getIdAlunoReceptor() {
        return idAlunoReceptor;
    }
    public int getIdAlunoAvaliador() {
        return idAlunoAvaliador;
    }
    public String getNomeAluno() { return nome; }
    public String getEmail() { return email; }
    public String getGrupo() { return grupo; }
    public String getSenha() { return senha; }
    public String getTipo_usuario() { return tipo_usuario; }

    public void setIdAlunoReceptor(int idAlunoReceptor) {
        this.idAlunoReceptor = idAlunoReceptor;
    }
    public void setIdAlunoAvaliador(int idAlunoAvaliador) {
        this.idAlunoAvaliador = idAlunoAvaliador;
    }
    public void setNomeAluno(String nome) {
        this.nome = nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }


}

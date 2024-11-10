package com.cyber.cybernexuspacer.entity;

public class PontuacaoGrupo {
    int id;
    String grupo;
    int integrantes;
    int nota;

    public PontuacaoGrupo(int id, String grupo, int integrantes, int nota) {
        this.id = id;
        this.grupo = grupo;
        this.integrantes = integrantes;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }
    public String getGrupo() {
        return grupo;
    }

    public int getIntegrantes() {
        return integrantes;
    }
    public int getNota() {
        return nota;
    }


}

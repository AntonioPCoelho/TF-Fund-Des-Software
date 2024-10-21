package com.coelho.sistcontrol.dominio.entidades;

public class ClienteModel {
    private long Id;
    private String nome;
    private String email;

    public ClienteModel(long Id, String nome, String email) {
        this.Id = Id;
        this.nome = nome;
        this.email = email;
    }

    public long getId() {
        return this.Id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "ClienteModel [Id=" + Id + ", nome=" + nome + ", email=" + email + "]";
    }

}

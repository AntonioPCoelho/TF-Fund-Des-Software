package com.coelho.sistcontrol.dominio.entidades;

public class ClienteModel {
    private long codigo;
    private String nome;
    private String email;

    public ClienteModel(long codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "ClienteModel [codigo=" + codigo + ", nome=" + nome + ", email=" + email + "]";
    }

}

package com.coelho.sistcontrol.aplicacao.dtos;

public class AplicativoDTO {
    private Long codigo;
    private String nome;
    private double custo;

    public AplicativoDTO(Long codigo, String nome, double custo) {
        this.codigo = codigo;
        this.nome = nome;
        this.custo = custo;
    }

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}

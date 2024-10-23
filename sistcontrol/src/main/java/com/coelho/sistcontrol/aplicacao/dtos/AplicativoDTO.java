package com.coelho.sistcontrol.aplicacao.dtos;

import java.math.BigDecimal;

public class AplicativoDTO {
    private Long codigo;
    private String nome;
    private BigDecimal custo;

    public AplicativoDTO(Long codigo, String nome, BigDecimal custo) {
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

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
}

package com.coelho.sistcontrol.aplicacao.dtos;

import java.math.BigDecimal;

public class PagamentoRequestDTO {
    private int dia;
    private int mes;
    private int ano;
    private Long codass;
    private BigDecimal valorPago;

    public PagamentoRequestDTO(int dia, int mes, int ano, Long codass, BigDecimal valorPago) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.codass = codass;
        this.valorPago = valorPago;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Long getCodass() {
        return codass;
    }

    public void setCodass(Long codass) {
        this.codass = codass;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

}

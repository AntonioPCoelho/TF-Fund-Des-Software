package com.coelho.sistcontrol.dominio.entidades;

import java.util.Date;

public class PagamentoModel {
    private long codigo;
    private double valorPago;
    private Date dataPagamento;
    private String promocao;
    private AssinaturaModel ass;

    public PagamentoModel(long codigo, double valorPago, Date dataPagamento, String promocao, AssinaturaModel ass) {
        this.codigo = codigo;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
        this.ass = ass;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public double valorPago() {
        return this.valorPago;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public String getPromocao() {
        return this.promocao;
    }

    public AssinaturaModel getAss() {
        return ass;
    }


    @Override
    public String toString() {
        return "PagamentoModel [codigo=" + codigo + ", valorPago=" + valorPago + ", dataPagamento=" + dataPagamento
                + ", promocao=" + promocao + "]";
    }

}

package com.coelho.sistcontrol.dominio.entidades;

import java.util.Date;

public class PagamentoModel {
    private long Id;
    private double valorPago;
    private Date dataPagamento;
    private String promocao;
    private AssinaturaModel ass;

    public PagamentoModel(long Id, double valorPago, Date dataPagamento, String promocao, AssinaturaModel ass) {
        this.Id = Id;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
        this.ass = ass;
    }

    public long getId() {
        return this.Id;
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
        return "PagamentoModel [Id=" + Id + ", valorPago=" + valorPago + ", dataPagamento=" + dataPagamento
                + ", promocao=" + promocao + "]";
    }

}

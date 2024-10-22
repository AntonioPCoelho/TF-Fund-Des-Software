package com.coelho.sistcontrol.dominio.entidades;

import java.util.Date;

public class PagamentoModel {
    private long Id;
    private double valorPago;
    private Date dataPagamento;
    private String promocao;
    private AssinaturaModel assinatura;

    public PagamentoModel(long Id, AssinaturaModel assinatura, double valorPago, Date dataPagamento, String promocao) {
        this.Id = Id;
        this.assinatura = assinatura;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
    }

    public long getId() {
        return this.Id;
    }

    public double getValorPago() {
        return this.valorPago;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public String getPromocao() {
        return this.promocao;
    }

    public AssinaturaModel getAssinatura() {
        return assinatura;
    }


    @Override
    public String toString() {
        return "PagamentoModel [Id=" + Id + ", valorPago=" + valorPago + ", dataPagamento=" + dataPagamento
                + ", promocao=" + promocao + "]";
    }

}

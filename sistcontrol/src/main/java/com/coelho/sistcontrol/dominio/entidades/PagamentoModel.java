package com.coelho.sistcontrol.dominio.entidades;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentoModel {
    private long Id;
    private BigDecimal valorPago;
    private Date dataPagamento;
    private String promocao;
    private AssinaturaModel assinatura;

    public PagamentoModel(long Id, BigDecimal valorPago, Date dataPagamento, String promocao, AssinaturaModel assinatura) {
        this.Id = Id;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
        this.assinatura = assinatura;
    }

    public long getId() {
        return this.Id;
    }

    public BigDecimal getValorPago() {
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

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public void setAssinatura(AssinaturaModel assinatura) {
        this.assinatura = assinatura;
    }

    @Override
    public String toString() {
        return "PagamentoModel [Id=" + Id + ", valorPago=" + valorPago + ", dataPagamento=" + dataPagamento
                + ", promocao=" + promocao + "]";
    }

}

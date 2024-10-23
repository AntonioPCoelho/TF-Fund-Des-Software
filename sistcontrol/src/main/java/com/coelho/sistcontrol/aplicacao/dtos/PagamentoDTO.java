package com.coelho.sistcontrol.aplicacao.dtos;

import java.math.BigDecimal;
import java.util.Date;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;

public class PagamentoDTO {

    private long id;
    private BigDecimal valorPago;
    private Date dataPagamento;
    private String promocao;
    private AssinaturaModel ass;

    public PagamentoDTO(long id, BigDecimal valorPago, Date dataPagamento, String promocao, AssinaturaModel ass) {
        this.id = id;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
        this.ass = ass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public AssinaturaModel getass() {
        return ass;
    }

    public void setass(AssinaturaModel ass) {
        this.ass = ass;
    }
}

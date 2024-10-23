package com.coelho.sistcontrol.aplicacao.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PagamentoResponseDTO {
    private String status;
    private Date novaDataValidade;
    private BigDecimal valorEstornado;
    
    public PagamentoResponseDTO(String status, Date novaDataValidade, BigDecimal valorEstornado) {
        this.status = status;
        this.novaDataValidade = novaDataValidade;
        this.valorEstornado = valorEstornado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNovaDataValidade() {
        return novaDataValidade;
    }

    public void setNovaDataValidade(Date novaDataValidade) {
        this.novaDataValidade = novaDataValidade;
    }

    public BigDecimal getValorEstornado() {
        return valorEstornado;
    }

    public void setValorEstornado(BigDecimal valorEstornado) {
        this.valorEstornado = valorEstornado;
    }

    
}

package com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades;

import java.math.BigDecimal;
import java.util.Date;
import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "assinatura_Id")
    private Assinatura assinatura;

    private BigDecimal valorPago;

    private Date dataPagamento;

    private String promocao;

    public Pagamento(Long Id, Assinatura assinatura, BigDecimal valorPago, Date dataPagamento, String promocao) {
        this.Id = Id;
        this.assinatura = assinatura;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
    }
    protected Pagamento(){}


    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
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

    public static Pagamento fromModel(PagamentoModel model) {
        return new Pagamento(model.getId(), Assinatura.fromModel(model.getAssinatura()), model.getValorPago(), model.getDataPagamento(), model.getPromocao());
    }

    public PagamentoModel toModel() {
        return new PagamentoModel(this.Id, this.valorPago, this.dataPagamento, this.promocao, this.assinatura.toModel());
    }

}
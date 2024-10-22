package com.coelho.sistcontrol.aplicacao.dtos;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import java.util.Date;

public class AssinaturaDTO {
    private Long Id;
    private Date inicioVigencia;
    private Date fimVigencia;
    private AplicativoModel app;
    private ClienteModel cliente;
    private String status;


    public AssinaturaDTO(Long Id, Date inicioVigencia, Date fimVigencia, AplicativoModel app, ClienteModel cliente, String status){
        this.Id = Id;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.app = app;
        this.cliente = cliente;
        this.status = status;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public AplicativoModel getApp() {
        return app;
    }

    public void setApp(AplicativoModel app) {
        this.app = app;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
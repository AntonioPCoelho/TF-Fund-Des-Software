package com.coelho.sistcontrol.aplicacao.dtos;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import java.util.Date;

public class AssinaturasDTO {
    private long Id;
    private Date inicioVigencia;
    private Date fimVigencia;
    private AplicativoModel app;
    private ClienteModel cliente;
    private String status;


    public AssinaturasDTO(AssinaturaModel assinaturaModel){
        this.Id = assinaturaModel.getId();
        this.inicioVigencia = assinaturaModel.getInicioVigencia();
        this.fimVigencia = assinaturaModel.getFimVigencia();
        this.app = assinaturaModel.getApp();
        this.cliente = assinaturaModel.getCliente();
        this.status = assinaturaModel.getstatus();
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



    public static AssinaturasDTO fromModel(AssinaturaModel assinaturamodel){
        return new AssinaturasDTO(assinaturamodel);
    }
}

package com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades;

import java.util.ArrayList;
import java.util.List;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assinatura> assinaturas = new ArrayList<>();

    public Cliente(Long codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public void setAssinaturas(List<Assinatura> assinaturas) {
        this.assinaturas = assinaturas;
    }

    public void atualizarDados(ClienteModel clienteModel) {
        this.nome = clienteModel.getNome();
        this.email = clienteModel.getEmail();
    }

    public static Cliente fromModel(ClienteModel model) {
        return new Cliente(model.getCodigo(), model.getNome(), model.getEmail());
    }

    public ClienteModel toModel() {
        return new ClienteModel(this.codigo, this.nome, this.email);
        
    }
    
}
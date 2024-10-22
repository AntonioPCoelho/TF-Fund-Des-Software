package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AssinaturaRepositoryJPA;

@Component
public class AssinaturaRepository implements IAssinaturaRepository {
    private AssinaturaRepositoryJPA assinaturas;

    public AssinaturaRepository(AssinaturaRepositoryJPA assinaturas) {
        this.assinaturas = assinaturas;
    }

    @Override
    public AssinaturaModel save(AssinaturaModel assinaturaModel) {
        Assinatura entity = Assinatura.fromModel(assinaturaModel); // Convert model to entity
        Assinatura savedEntity = assinaturas.save(entity); // Save entity in DB
        return savedEntity.toModel(); // Convert entity back to model and return
    }

    @Override
    public Optional<AssinaturaModel> findByClienteIdAndAplicativoId(Long clienteId, Long aplicativoId) {
        return assinaturas.findByClienteIdAndAplicativoId(clienteId, aplicativoId)
                .map(Assinatura::toModel); // Convert entity to model
    }

    @Override
    public Optional<AssinaturaModel> findById(Long id) {
        return assinaturas.findById(id)
                .map(Assinatura::toModel); // Convert entity to model
    }

    @Override
    public List<AssinaturaModel> findAll() {
        return assinaturas.findAll().stream()
                .map(Assinatura::toModel) // Convert entities to models
                .toList();
    }

    @Override
    public List<AssinaturaModel> findByClienteId(Long clienteId) {
        return assinaturas.findByClienteId(clienteId).stream()
                .map(Assinatura::toModel) // Convert entities to models
                .toList();
    }

    @Override
    public List<AssinaturaModel> findByStatus(String status) {
        return assinaturas.findByStatus(status).stream()
                .map(Assinatura::toModel) // Convert entities to models
                .toList();
    }

    @Override
    public List<ClienteModel> findClientesByAplicativo(Long aplicativoId) {

        return assinaturas.findClientesByAplicativoId(aplicativoId).stream().map(Cliente::toModel).toList();
    }
    
    public Optional<Assinatura> findByid(Long id) {
        return assinaturas.findById(id);
    }

}
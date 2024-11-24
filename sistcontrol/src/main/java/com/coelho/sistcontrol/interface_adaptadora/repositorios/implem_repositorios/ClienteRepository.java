package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.ClienteRepositoryJPA;

@Component
public class ClienteRepository implements IClienteRepository{
    private ClienteRepositoryJPA clientes;


    public ClienteRepository(ClienteRepositoryJPA clientes){
        this.clientes = clientes;
    }


    @Override
    public ClienteModel save(ClienteModel cliente) {
        return clientes.save(Cliente.fromModel(cliente)).toModel();        
    }


    @Override
    public List<ClienteModel> findAll() {
        return clientes.findAll().stream().map(Cliente::toModel).toList();
    }


    @Override
    public Optional<ClienteModel> findById(Long id) {
        return clientes.findById(id).map(Cliente::toModel);
    }

    @Override
    public void deleteAll(){
        clientes.deleteAll();
    }
}

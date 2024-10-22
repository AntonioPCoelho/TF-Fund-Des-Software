package com.coelho.sistcontrol.dominio.interfRepositorios;

import java.util.List;
import java.util.Optional;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;

public interface IClienteRepository {
    <Cliente> Cliente save();
    List<Cliente> findAll();
    Optional<Cliente> findById();
}

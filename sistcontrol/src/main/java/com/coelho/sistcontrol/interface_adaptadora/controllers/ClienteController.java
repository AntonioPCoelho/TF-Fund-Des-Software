package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.dominio.servicos.ClienteService;


import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/servcad")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar todos os clientes cadastrados
    @GetMapping("/clientes")
    public List<ClienteModel> listarClientes() {
        return clienteService.listarTodos().stream().map(x -> x.toModel()).toList();
    }

    // Listar assinaturas do cliente informado
    @GetMapping("/asscli/{id}")
    public ResponseEntity<?> listarAssinaturasPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarAssinaturasPorCliente(id));
    }
}


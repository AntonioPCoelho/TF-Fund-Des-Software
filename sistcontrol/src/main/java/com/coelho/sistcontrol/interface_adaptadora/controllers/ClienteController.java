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
@RequestMapping("/servcad/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar todos os clientes
    @GetMapping
    public List<ClienteModel> listarClientes() {
        return clienteService.listarTodos().stream().map(x -> x.toModel()).toList();
    }

    // Cadastrar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteModel> cadastrarCliente(@RequestBody ClienteModel clienteModel) {
        ClienteModel novoCliente = clienteService.cadastrar(clienteModel).toModel();
        return ResponseEntity.ok(novoCliente);
    }

    // Editar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> editarCliente(@PathVariable Long id, @RequestBody ClienteModel clienteModel) {
        ClienteModel atualizado = clienteService.editarCliente(id, clienteModel);
        return ResponseEntity.ok(atualizado);
    }

    // Listar assinaturas de um cliente
    @GetMapping("/{id}/assinaturas")
    public ResponseEntity<?> listarAssinaturasPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarAssinaturasPorCliente(id));
    }
}


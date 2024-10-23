package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import com.coelho.sistcontrol.aplicacao.dtos.ClienteDTO;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;


@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Endpoint para listar todos os clientes
    @GetMapping("/servcad/clientes")
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteModel> clientes = clienteService.listarTodos();
        
        // Convertendo os ClienteModel para ClienteDTO para evitar exposição de dados sensíveis
        List<ClienteDTO> clientesDTO = clientes.stream()
            .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail()))
            .toList();
        
        return ResponseEntity.ok(clientesDTO);
    }
}

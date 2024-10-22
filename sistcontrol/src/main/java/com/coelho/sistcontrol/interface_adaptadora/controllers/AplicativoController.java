package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.dominio.servicos.AplicativoService;


import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/servcad")
public class AplicativoController {

    private final AplicativoService aplicativoService;

    public AplicativoController(AplicativoService aplicativoService) {
        this.aplicativoService = aplicativoService;
    }

    // Listar todos os aplicativos cadastrados
    @GetMapping("/aplicativos")
    public List<AplicativoModel> listarAplicativos() {
        return aplicativoService.listarAplicativos();
    }

    // Atualizar o valor do custo mensal de um aplicativo
    @PostMapping("/aplicativos/atualizacusto/{id}")
    public ResponseEntity<Void> atualizarCustoMensal(@PathVariable Long id, @RequestParam double novoCusto) {
        aplicativoService.atualizarCustoMensal(id, novoCusto);
        return ResponseEntity.ok().build();
    }

}


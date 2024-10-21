package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;


import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    // Cadastrar uma nova assinatura
    @PostMapping
    public ResponseEntity<AssinaturaModel> cadastrarAssinatura(@RequestBody AssinaturaModel assinaturaModel) {
        AssinaturaModel novaAssinatura = assinaturaService.salvarAssinatura(assinaturaModel);
        return ResponseEntity.ok(novaAssinatura);
    }

    // Verificar se uma assinatura é válida
    @GetMapping("/validade")
    public ResponseEntity<Boolean> isAssinaturaValida(@RequestParam Long clienteId, @RequestParam Long aplicativoId) {
        boolean isValida = assinaturaService.isAssinaturaValida(clienteId, aplicativoId);
        return ResponseEntity.ok(isValida);
    }

    // Listar assinaturas de um aplicativo
    @GetMapping("/aplicativos/{aplicativoId}/assinantes")
    public ResponseEntity<List<AssinaturaModel>> listarAssinantesPorAplicativo(@PathVariable Long aplicativoId) {
        List<AssinaturaModel> assinantes = assinaturaService.listarAssinantesPorAplicativo(aplicativoId);
        return ResponseEntity.ok(assinantes);
    }
}


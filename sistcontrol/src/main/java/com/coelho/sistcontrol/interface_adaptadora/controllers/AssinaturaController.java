package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servcad")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    // Cria uma assinatura
    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaModel> cadastrarAssinatura(@PathVariable Long clienteId, @PathVariable Long aplicativoId, @RequestBody AssinaturaModel assinaturaModel) {
        AssinaturaModel novaAssinatura = assinaturaService.salvarAssinatura(clienteId, aplicativoId, assinaturaModel);
        return ResponseEntity.ok(novaAssinatura);
    }

    // Lista de assinaturas conforme o status (TODAS, ATIVAS ou CANCELADAS)
    @GetMapping("/assinaturas/{tipo}")
    public ResponseEntity<List<AssinaturaModel>> listarAssinaturaPorstatus(@PathVariable String status) {
        List<AssinaturaModel> assinaturas = assinaturaService.listarAssinaturasPorstatus(status);
        return ResponseEntity.ok(assinaturas);
    }

    // Listar assinaturas de um aplicativo
    @GetMapping("/assapp/{aplicativoId}")
    public ResponseEntity<List<Cliente>> listarAssinantesPorAplicativo(@PathVariable Long aplicativoId) {
        List<Cliente> assinantes = assinaturaService.listarAssinantesPorAplicativo(aplicativoId);
        return ResponseEntity.ok(assinantes);
    }

     // Retorna se a assinatura questionada permanece ativa
     @GetMapping("/assinvalida/{id}")
     public ResponseEntity<Boolean> isAssinaturaValida(@RequestParam Long codass) {
         boolean isValida = assinaturaService.isAssinaturaValida(codass);
         return ResponseEntity.ok(isValida);
     }

}

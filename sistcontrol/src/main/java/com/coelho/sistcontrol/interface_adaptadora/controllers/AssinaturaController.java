package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/servcad/assinaturas")
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
    public ResponseEntity<List<Cliente>> listarAssinantesPorAplicativo(@PathVariable Long aplicativoId) {
        List<Cliente> assinantes = assinaturaService.listarAssinantesPorAplicativo(aplicativoId);
        return ResponseEntity.ok(assinantes);
    }

    @PatchMapping("/{id}/atualizarValidade")
    public ResponseEntity<Void> atualizarValidadeAssinatura(@PathVariable Long id, @RequestParam String novaDataValidade) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date novaData = format.parse(novaDataValidade);
            assinaturaService.atualizarValidadeAssinatura(id, novaData);
            return ResponseEntity.ok().build();
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build(); // Retorna erro se a data não for válida
        }
    }

    // Lista de assinaturas conforme o status (TODAS, ATIVAS ou CANCELADAS)
    @GetMapping("/{status}")
    public ResponseEntity<List<AssinaturaModel>> listarAssinaturaPorstatus(@PathVariable String status) {
        List<AssinaturaModel> assinaturas = assinaturaService.listarAssinaturasPorstatus(status);
        return ResponseEntity.ok(assinaturas);
    }

}

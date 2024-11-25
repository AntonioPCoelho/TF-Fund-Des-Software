package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.aplicacao.casosdeuso.CriarAssinaturaUseCase;
import com.coelho.sistcontrol.aplicacao.dtos.AssinaturaDTO;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;

@RestController
public class AssinaturaController {

    private final AssinaturaService assinaturaService;
    private final ClienteService clienteService;
    private final CriarAssinaturaUseCase criarAssinaturaUseCase;

    public AssinaturaController(AssinaturaService assinaturaService, ClienteService clienteService, CriarAssinaturaUseCase criarAssinaturaUseCase) {
        this.assinaturaService = assinaturaService;
        this.clienteService = clienteService;
        this.criarAssinaturaUseCase = criarAssinaturaUseCase;
    }

    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaDTO> criarAssinatura(@RequestParam Long clienteId, @RequestParam Long aplicativoId) {
        AssinaturaDTO resposta = criarAssinaturaUseCase.execute(clienteId, aplicativoId);
        return ResponseEntity.ok(resposta);
    }

    // Verificar se uma assinatura é válida
    @GetMapping("/assinvalida/{id}")
    public ResponseEntity<Boolean> isAssinaturaValida(@PathVariable Long id) {
        boolean isValida = assinaturaService.isAssinaturaValida(id);
        return ResponseEntity.ok(isValida);
    }

    // Listar assinaturas de um aplicativo
    @GetMapping("/assapp/{aplicativoId}")
    public ResponseEntity<List<ClienteModel>> listarAssinantesPorAplicativo(@PathVariable Long aplicativoId) {
        List<ClienteModel> assinantes = assinaturaService.listarAssinantesPorAplicativo(aplicativoId);
        return ResponseEntity.ok(assinantes);
    }

    // Lista de assinaturas conforme o status (TODAS, ATIVAS ou CANCELADAS)
    @GetMapping("/servcad/assinaturas/{tipo}") // GOOD
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorTipo(@PathVariable String tipo) {
        List<AssinaturaDTO> assinaturas = assinaturaService.listarAssinaturasPorstatus(tipo).stream()
            .map(assinatura -> new AssinaturaDTO(assinatura.getId(), assinatura.getInicioVigencia(), assinatura.getFimVigencia(),
                assinatura.getApp(),
                assinatura.getCliente(),
                assinatura.getstatus()))
            .toList();
        return ResponseEntity.ok(assinaturas);
    }

    @GetMapping("/servcad/asscli/{codcli}")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorCliente(@PathVariable Long codcli) {
        List<AssinaturaDTO> assinaturas = clienteService.listarAssinaturasPorCliente(codcli).stream()
            .map(assinatura -> new AssinaturaDTO(assinatura.getId(), assinatura.getInicioVigencia(), assinatura.getFimVigencia(),
                assinatura.getApp(), assinatura.getCliente(),
                assinatura.getstatus()))
            .toList();
        return ResponseEntity.ok(assinaturas);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

}

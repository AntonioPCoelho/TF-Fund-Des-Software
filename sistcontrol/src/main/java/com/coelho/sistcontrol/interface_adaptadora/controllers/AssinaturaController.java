package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import com.coelho.sistcontrol.aplicacao.dtos.AssinaturaDTO;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servcad")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;
    private final ClienteService clienteService;

    public AssinaturaController(AssinaturaService assinaturaService, ClienteService clienteService) {
        this.assinaturaService = assinaturaService;
        this.clienteService = clienteService;
    }

    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaDTO> criarAssinatura(@RequestBody AssinaturaDTO assinaturaDTO) {
        AssinaturaModel novaAssinatura = new AssinaturaModel(assinaturaDTO.getId(), assinaturaDTO.getInicioVigencia(), assinaturaDTO.getFimVigencia(), assinaturaDTO.getApp(), assinaturaDTO.getCliente(), assinaturaDTO.getStatus());
        AssinaturaModel salva = assinaturaService.salvarAssinatura(novaAssinatura);
        AssinaturaDTO resposta = new AssinaturaDTO(salva.getId(), salva.getInicioVigencia(), salva.getFimVigencia(), salva.getApp(), salva.getCliente(), salva.getstatus());
        return ResponseEntity.ok(resposta);
    }

    // Verificar se uma assinatura é válida
    @GetMapping("/assinvalida/{id}")
    public ResponseEntity<Boolean> isAssinaturaValida(@RequestParam Long id) {
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
    @GetMapping("/assinaturas/{tipo}") // GOOD
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorTipo(@PathVariable String tipo) {
        List<AssinaturaDTO> assinaturas = assinaturaService.listarAssinaturasPorstatus(tipo).stream()
            .map(assinatura -> new AssinaturaDTO(assinatura.getId(), assinatura.getInicioVigencia(), assinatura.getFimVigencia(),
                assinatura.getApp(),
                assinatura.getCliente(),
                assinatura.getstatus()))
            .toList();
        return ResponseEntity.ok(assinaturas);
    }

    @GetMapping("/asscli/{codcli}")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorCliente(@PathVariable Long codcli) {
        List<AssinaturaDTO> assinaturas = clienteService.listarAssinaturasPorCliente(codcli).stream()
            .map(assinatura -> new AssinaturaDTO(assinatura.getId(), assinatura.getInicioVigencia(), assinatura.getFimVigencia(),
                assinatura.getApp(), assinatura.getCliente(),
                assinatura.getstatus()))
            .toList();
        return ResponseEntity.ok(assinaturas);
    }

}

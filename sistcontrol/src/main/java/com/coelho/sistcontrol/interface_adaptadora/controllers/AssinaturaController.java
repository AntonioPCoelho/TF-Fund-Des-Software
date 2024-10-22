package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;
import com.coelho.sistcontrol.aplicacao.dtos.AssinaturaDTO;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/servcad")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaDTO> criarAssinatura(@RequestBody AssinaturaDTO assinaturaDTO) {
        AssinaturaModel novaAssinatura = new AssinaturaModel(assinaturaDTO.getId(), assinaturaDTO.getInicioVigencia(), assinaturaDTO.getFimVigencia(), assinaturaDTO.getApp(), assinaturaDTO.getCliente(), assinaturaDTO.getStatus());
        AssinaturaModel salva = assinaturaService.salvarAssinatura(novaAssinatura);
        AssinaturaDTO resposta = new AssinaturaDTO(salva);
        return ResponseEntity.ok(resposta);
    }

    // Verificar se uma assinatura é válida
    @GetMapping("/validade")
    public ResponseEntity<Boolean> isAssinaturaValida(@RequestParam Long clienteId, @RequestParam Long aplicativoId) {
        boolean isValida = assinaturaService.isAssinaturaValida(clienteId, aplicativoId);
        return ResponseEntity.ok(isValida);
    }

    /*
    // Listar assinaturas de um aplicativo
    @GetMapping("/aplicativos/{aplicativoId}/assinantes")
    public ResponseEntity<List<Cliente>> listarAssinantesPorAplicativo(@PathVariable Long aplicativoId) {
        List<Cliente> assinantes = assinaturaService.listarAssinantesPorAplicativo(aplicativoId);
        return ResponseEntity.ok(assinantes);
    }
    */

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
    @GetMapping("/assinaturas/{tipo}")
    public ResponseEntity<List<AssinaturaDTO>> listarAssinaturasPorStatus(@PathVariable String tipo) {
        List<AssinaturaDTO> assinaturas = assinaturaService.listarAssinaturasPorstatus(tipo).stream()
                .map(assinatura -> new AssinaturaDTO(assinatura))
                .toList();
        return ResponseEntity.ok(assinaturas);
    }

}

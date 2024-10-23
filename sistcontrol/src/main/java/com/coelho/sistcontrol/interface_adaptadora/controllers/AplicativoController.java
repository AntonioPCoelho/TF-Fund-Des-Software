package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.dominio.servicos.AplicativoService;
import com.coelho.sistcontrol.aplicacao.dtos.AplicativoDTO;
import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
@RestController
@RequestMapping("/servcad")
public class AplicativoController {

    private final AplicativoService aplicativoService;

    public AplicativoController(AplicativoService aplicativoService) {
        this.aplicativoService = aplicativoService;
    }

    // Endpoint para listar todos os aplicativos
    @GetMapping("/aplicativos")
    public ResponseEntity<List<AplicativoDTO>> listarTodos() {
        List<AplicativoModel> aplicativos = aplicativoService.listarAplicativos();
        
        // Convertendo os AplicativoModel para AplicativoDTO
        List<AplicativoDTO> aplicativosDTO = aplicativos.stream()
            .map(aplicativo -> new AplicativoDTO(aplicativo.getId(), aplicativo.getNome(), aplicativo.getCustoMensal()))
            .toList();
        
        return ResponseEntity.ok(aplicativosDTO);
    }

    @PostMapping("/aplicativos/atualizacusto/{idAplicativo}")
    public ResponseEntity<AplicativoDTO> atualizarCusto(@PathVariable Long idAplicativo, @RequestBody BigDecimal custo) {
        // Atualiza o custo mensal e obtém o aplicativo atualizado
        AplicativoModel aplicativoAtualizado = aplicativoService.atualizarCustoMensal(idAplicativo, custo);
        
        // Converte para DTO para resposta
        AplicativoDTO aplicativoDTO = new AplicativoDTO(aplicativoAtualizado.getId(), aplicativoAtualizado.getNome(), aplicativoAtualizado.getCustoMensal());
        
        return ResponseEntity.ok(aplicativoDTO);
    }
}
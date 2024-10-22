package com.coelho.sistcontrol.interface_adaptadora.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.dominio.servicos.PagamentoService;

@RestController
@RequestMapping("/registrarpagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    /*
    // Solicitar o registro de um pagamento
    @PostMapping()
    public ResponseEntity<PagamentoDTO> registrarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO resultado = pagamentoService.registrarPagamento(pagamentoDTO);
        return ResponseEntity.ok(resultado);
    }
    */
}

package com.coelho.sistcontrol.interface_adaptadora.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.aplicacao.casosdeuso.RegistrarPagamentoUseCase;
import com.coelho.sistcontrol.aplicacao.casosdeuso.RegistrarPagamentoPromocaoUseCase;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoResponseDTO;

@RestController
@RequestMapping("/registrarpagamento")
public class PagamentoController {

    private final RegistrarPagamentoUseCase registrarPagamentoUseCase;
    private final RegistrarPagamentoPromocaoUseCase registrarPagamentoPagamentoUseCase;

    public PagamentoController(RegistrarPagamentoUseCase registrarPagamentoUseCase,
            RegistrarPagamentoPromocaoUseCase registrarPagamentoPagamentoUseCase) {
        this.registrarPagamentoUseCase = registrarPagamentoUseCase;
        this.registrarPagamentoPagamentoUseCase = registrarPagamentoPagamentoUseCase;
    }

    @PostMapping()
    public ResponseEntity<PagamentoResponseDTO> registrarPagamento(@RequestBody PagamentoRequestDTO request) {
        PagamentoResponseDTO response = registrarPagamentoUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{promocao}")
    public ResponseEntity<PagamentoResponseDTO> registrarPagamentoComPromocao(
            @RequestBody PagamentoRequestDTO request,
            @PathVariable String promocao) {
        PagamentoResponseDTO response = registrarPagamentoPagamentoUseCase.execute(request, promocao);
        return ResponseEntity.ok(response);
    }
}

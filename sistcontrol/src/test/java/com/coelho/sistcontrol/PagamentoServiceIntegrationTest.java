package com.coelho.sistcontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IPagamentoRepository;
import com.coelho.sistcontrol.dominio.servicos.PagamentoService;

@SpringBootTest
class PagamentoServiceIntegrationTest {
    @Autowired
    private PagamentoService pagamentoService;
    
    @Autowired
    private IAssinaturaRepository assinaturaRepository;
    
    @Autowired
    private IPagamentoRepository pagamentoRepository;
    
    @Test
    void testRegistrarPagamentoIntegracao() {
        // Arrange
        // AssinaturaModel assinatura = criarAssinaturaTeste();
        // assinaturaRepository.save(assinatura);
        
        // PagamentoRequestDTO request = new PagamentoRequestDTO();
        // request.setCodass(assinatura.getId());
        // request.setValorPago(assinatura.getApp().getCustoMensal());
        // request.setAno(2024);
        // request.setMes(1);
        // request.setDia(1);
        
        // // Act
        // pagamentoService.registrarPagamento(request, assinatura);
        
        // // Assert
        // Optional<PagamentoModel> pagamentoSalvo = pagamentoRepository.findByAssinatura(assinatura);
        // assertTrue(pagamentoSalvo.isPresent());
        // assertEquals(request.getValorPago(), pagamentoSalvo.get().getValorPago());
    }
}

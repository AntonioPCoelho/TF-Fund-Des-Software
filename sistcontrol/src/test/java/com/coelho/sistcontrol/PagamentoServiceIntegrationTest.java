package com.coelho.sistcontrol;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IPagamentoRepository;
import com.coelho.sistcontrol.dominio.servicos.PagamentoService;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoDTO;

@SpringBootTest
@Transactional
@Rollback
public class PagamentoServiceIntegrationTest {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private IAssinaturaRepository assinaturaRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IAplicativoRepository aplicativoRepository;

    private AssinaturaModel assinatura;
    private AplicativoModel aplicativo;
    private ClienteModel cliente;

    @BeforeEach
    void setUp() {
        // Limpar dados anteriores
        pagamentoRepository.deleteAll();
        assinaturaRepository.deleteAll();
        clienteRepository.deleteAll();
        aplicativoRepository.deleteAll();

        // Criar dados base para testes
        cliente = clienteRepository.save(new ClienteModel(0L, "Cliente Teste", "cliente@teste.com"));
        
        aplicativo = aplicativoRepository.save(new AplicativoModel(0L, "App Teste", new BigDecimal("50.00")));
        
        Calendar cal = Calendar.getInstance();
        Date inicioVigencia = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date fimVigencia = cal.getTime();
        
        assinatura = assinaturaRepository.save(new AssinaturaModel(
            0L,
            inicioVigencia,
            fimVigencia,
            aplicativo,
            cliente,
            "ATIVA"
        ));
    }

    @AfterEach
    void tearDown() {
        pagamentoRepository.deleteAll();
        assinaturaRepository.deleteAll();
        clienteRepository.deleteAll();
        aplicativoRepository.deleteAll();
    }

    @Test
    void deveRegistrarPagamentoComSucesso() {
        // Arrange
        PagamentoRequestDTO request = new PagamentoRequestDTO(
            1, // dia
            1, // mês
            2024, // ano
            assinatura.getId(),
            new BigDecimal("50.00") // valor igual ao custo mensal
        );

        // Act
        pagamentoService.registrarPagamento(request, assinatura);

        // Assert
        Optional<PagamentoModel> pagamentoSalvo = pagamentoRepository.findByAssinaturaId(assinatura.getId());
        assertTrue(pagamentoSalvo.isPresent());
        assertEquals(new BigDecimal("50.00"), pagamentoSalvo.get().getValorPago());
    }

    @Test
    void deveRegistrarPagamentoComDTO() {
        // Arrange
        PagamentoDTO dto = new PagamentoDTO(
            assinatura.getId(),
            new BigDecimal("50.00"),
            new Date(),
            null,
            assinatura
        );

        // Act
        PagamentoDTO resultado = pagamentoService.registrarPagamento(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(new BigDecimal("50.00"), resultado.getValorPago());
    }

    @Test
    void deveLancarExcecaoQuandoValorPagoIncorreto() {
        // Arrange
        PagamentoDTO dto = new PagamentoDTO(
            assinatura.getId(),
            new BigDecimal("45.00"), // valor diferente do custo mensal
            new Date(),
            null,
            assinatura
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            pagamentoService.registrarPagamento(dto);
        });
    }

    @Test
    void deveLancarExcecaoQuandoAssinaturaNaoEncontrada() {
        // Arrange
        PagamentoDTO dto = new PagamentoDTO(
            999L, // ID inexistente
            new BigDecimal("50.00"),
            new Date(),
            null,
            null
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            pagamentoService.registrarPagamento(dto);
        });
    }
}
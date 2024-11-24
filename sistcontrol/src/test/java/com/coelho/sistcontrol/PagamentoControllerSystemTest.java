package com.coelho.sistcontrol;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import com.coelho.sistcontrol.dominio.servicos.AplicativoService;

@SpringBootTest
@AutoConfigureMockMvc
class PagamentoControllerSystemTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private AplicativoService aplicativoService;
    
    @Autowired
    private AssinaturaService assinaturaService;
    
    private Long codAssIniciado;
    
    @BeforeEach
    void setUp() throws Exception {
        limparDados();
        // Criar cliente para teste
        ClienteModel cliente = new ClienteModel(49234L, "Cliente Teste", "teste@email.com");
        cliente = clienteService.cadastrar(cliente);
        
        // Criar aplicativo para teste
        AplicativoModel aplicativo = new AplicativoModel(997L, "App Teste", new BigDecimal("50.00"));
        aplicativo = aplicativoService.salvarAplicativo(aplicativo);
        
        // Criar assinatura
        mockMvc.perform(post("/assinaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .param("clienteId", Long.toString(cliente.getId()))
            .param("aplicativoId", Long.toString(aplicativo.getId())))
            .andExpect(status().isOk())
            .andDo(result -> {
                String content = result.getResponse().getContentAsString();
                this.codAssIniciado = objectMapper.readTree(content).get("id").asLong();
            });
    }
    @AfterEach
    void tearDown() {
        limparDados();
    }

    private void limparDados(){
        clienteService.deleteAll();
    }

    @Test
    void testRegistrarPagamento() throws Exception {
        // Arrange

        PagamentoRequestDTO request = new PagamentoRequestDTO(2, 2, 2004, 10L, BigDecimal.TEN);
        request.setCodass(codAssIniciado); // Usar o código da assinatura criada
        request.setValorPago(new BigDecimal("50.00"));
        request.setAno(2024);
        request.setMes(1);
        request.setDia(1);

        // Act & Assert
        mockMvc.perform(post("/registrarpagamento")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("PAGAMENTO_OK"))
            .andExpect(jsonPath("$.valorEstornado").value(0));
    }

    @Test
    void testRegistrarPagamentoComPromocao() throws Exception {
        // Arrange
        PagamentoRequestDTO request = new PagamentoRequestDTO(2, 2, 2004, 10L, BigDecimal.TEN);
        request.setCodass(codAssIniciado); // Usar o código da assinatura criada
        request.setValorPago(new BigDecimal("47.50")); // 5% de desconto em 50.00
        request.setAno(2024);
        request.setMes(1);
        request.setDia(1);

        // Act & Assert
        mockMvc.perform(post("/registrarpagamento/DESCONTO_5")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("PAGAMENTO_OK"))
            .andExpect(jsonPath("$.valorEstornado").value(0));
    }
}
package com.coelho.sistcontrol;

import java.math.BigDecimal;

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
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PagamentoControllerSystemTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testRegistrarPagamento() throws Exception {
        // Arrange
        PagamentoRequestDTO request = new PagamentoRequestDTO(2, 2, 2004, 10L, BigDecimal.TEN);
        request.setCodass(1L);
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
        request.setCodass(1L);
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
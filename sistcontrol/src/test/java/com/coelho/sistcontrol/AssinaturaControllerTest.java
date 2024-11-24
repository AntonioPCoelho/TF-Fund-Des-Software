package com.coelho.sistcontrol;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.coelho.sistcontrol.aplicacao.casosdeuso.CriarAssinaturaUseCase;
import com.coelho.sistcontrol.aplicacao.dtos.AssinaturaDTO;
import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import com.coelho.sistcontrol.interface_adaptadora.controllers.AssinaturaController;

@WebMvcTest(controllers = AssinaturaController.class)
class AssinaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriarAssinaturaUseCase criarAssinaturaUseCase;

    @MockBean
    private AssinaturaService assinaturaService;

    @MockBean
    private ClienteService clienteService;

    @Test
    void deveCriarAssinatura() throws Exception {
        // Dados de entrada
        Long clienteId = 1L;
        Long aplicativoId = 1L;

        // Mock do caso de uso
        AssinaturaDTO respostaMock = new AssinaturaDTO(
                1L,
                new Date(),
                new Date(),
                new AplicativoModel(1L, "App Exemplo", new BigDecimal("50.0")),
                new ClienteModel(1L, "João", "joao@email.com"),
                "ATIVA"
        );

        Mockito.when(criarAssinaturaUseCase.execute(clienteId, aplicativoId)).thenReturn(respostaMock);

        // Chamada e validação
        mockMvc.perform(post("/assinaturas")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clienteId", clienteId.toString())
                .param("aplicativoId", aplicativoId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ATIVA"))
                .andExpect(jsonPath("$.cliente.nome").value("João"))
                .andExpect(jsonPath("$.app.nome").value("App Exemplo"));
    }
}

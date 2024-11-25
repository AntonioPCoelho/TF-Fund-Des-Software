package com.coelho.sistcontrol;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.servicos.AplicativoService;
import com.coelho.sistcontrol.interface_adaptadora.controllers.AplicativoController;

@WebMvcTest(AplicativoController.class)
class AplicativoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AplicativoService aplicativoService;

    @Test
    void deveListarTodosOsAplicativos() throws Exception {
        List<AplicativoModel> aplicativos = List.of(
            new AplicativoModel(1L, "YouTube", new BigDecimal("11.90")),
            new AplicativoModel(2L, "Prime Video", new BigDecimal("19.90"))
        );

        when(aplicativoService.listarAplicativos()).thenReturn(aplicativos);

        mockMvc.perform(get("/servcad/aplicativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nome").value("YouTube"));
    }
    @Test
    void deveAtualizarOCustoViaEndpoint() throws Exception {
        AplicativoModel atualizado = new AplicativoModel(1L, "HBO Max", new BigDecimal("29.99"));

        when(aplicativoService.atualizarCustoMensal(eq(1L), eq(new BigDecimal("29.99")))).thenReturn(atualizado);

        mockMvc.perform(post("/servcad/aplicativos/atualizacusto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("29.99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("HBO Max"))
                .andExpect(jsonPath("$.custo").value(29.99));
    }
    @Test
    void deveRetornarErro404AoAtualizarCustoDeAplicativoInexistente() throws Exception {
        when(aplicativoService.atualizarCustoMensal(eq(99L), any(BigDecimal.class)))
            .thenThrow(new RuntimeException("Aplicativo não encontrado"));

        mockMvc.perform(post("/servcad/aplicativos/atualizacusto/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("15.00"))
                .andExpect(status().isNotFound());
    }
    @Test
    void deveRetornarListaVaziaQuandoNaoExistemAplicativos() throws Exception {
        when(aplicativoService.listarAplicativos()).thenReturn(List.of());

        mockMvc.perform(get("/servcad/aplicativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }
}
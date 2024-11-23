package com.coelho.sistcontrol;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import com.coelho.sistcontrol.interface_adaptadora.controllers.ClienteController;
import java.util.List;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService; // MockBean ao invés de Mock

    @Test
    void testListarTodos() throws Exception {
        // Definindo o mock do ClienteService para retornar uma lista de clientes
        ClienteModel clienteModel = new ClienteModel(1L, "João", "joao@example.com");
        when(clienteService.listarTodos()).thenReturn(List.of(clienteModel));

        // Realizando a requisição e validando a resposta
        mockMvc.perform(get("/servcad/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"));
    }
}

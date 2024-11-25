package com.coelho.sistcontrol;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import com.coelho.sistcontrol.interface_adaptadora.controllers.ClienteController;

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
    @Test
    void testListarTodosVazio() throws Exception {
        // Definindo o mock do ClienteService para retornar uma lista vazia
        when(clienteService.listarTodos()).thenReturn(List.of());

        // Realizando a requisição e validando a resposta
        mockMvc.perform(get("/servcad/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testListarTodosMultiplosClientes() throws Exception {
        // Definindo o mock do ClienteService para retornar uma lista de múltiplos clientes
        ClienteModel cliente1 = new ClienteModel(1L, "João", "joao@example.com");
        ClienteModel cliente2 = new ClienteModel(2L, "Maria", "maria@example.com");
        when(clienteService.listarTodos()).thenReturn(List.of(cliente1, cliente2));

        // Realizando a requisição e validando a resposta
        mockMvc.perform(get("/servcad/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }
    
}

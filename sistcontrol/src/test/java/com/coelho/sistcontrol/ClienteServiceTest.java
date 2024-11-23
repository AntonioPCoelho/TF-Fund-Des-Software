package com.coelho.sistcontrol;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;

import java.util.Optional;
import java.util.List;

public class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void testCadastrar() {
        ClienteModel cliente = new ClienteModel(1L, "João", "joao@example.com");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteModel result = clienteService.cadastrar(cliente);

        assertEquals("João", result.getNome());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testListarTodos() {
        ClienteModel cliente1 = new ClienteModel(1L, "João", "joao@example.com");
        ClienteModel cliente2 = new ClienteModel(2L, "Maria", "maria@example.com");
        when(clienteRepository.findAll()).thenReturn(List.of(cliente1, cliente2));

        List<ClienteModel> result = clienteService.listarTodos();

        assertEquals(2, result.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void testBuscarPorId_clienteExistente() {
        ClienteModel cliente = new ClienteModel(1L, "João", "joao@example.com");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<ClienteModel> result = clienteService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("João", result.get().getNome());
    }

    @Test
    void testBuscarPorId_clienteNaoExistente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ClienteModel> result = clienteService.buscarPorId(1L);

        assertFalse(result.isPresent());
    }



    @Test
    void testEditarCliente() {
        ClienteModel cliente = new ClienteModel(1L, "João", "joao@example.com");
        ClienteModel clienteAtualizado = new ClienteModel(1L, "João Silva", "joao.silva@example.com");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(clienteAtualizado);

        ClienteModel result = clienteService.editarCliente(1L, clienteAtualizado);

        assertEquals("João Silva", result.getNome());
        verify(clienteRepository).save(cliente);
    }
}

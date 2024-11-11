import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.dominio.servicos.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrar_shouldSaveAndReturnCliente() {
        ClienteModel cliente = new ClienteModel();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteModel result = clienteService.cadastrar(cliente);

        assertNotNull(result);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void buscarPorId_shouldReturnClienteWhenFound() {
        ClienteModel cliente = new ClienteModel();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<ClienteModel> found = clienteService.buscarPorId(1L);
        
        assertTrue(found.isPresent());
    }

    // Additional tests for other methods in ClienteService
}

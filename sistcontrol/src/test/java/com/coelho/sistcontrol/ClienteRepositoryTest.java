import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios.ClienteRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.ClienteRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class ClienteRepositoryTest {

    @Mock
    private ClienteRepositoryJPA clienteRepositoryJPA;

    @InjectMocks
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldSaveAndReturnClienteModel() {
        ClienteModel model = new ClienteModel();
        Cliente entity = Cliente.fromModel(model);
        when(clienteRepositoryJPA.save(entity)).thenReturn(entity);

        ClienteModel result = clienteRepository.save(model);

        assertNotNull(result);
        verify(clienteRepositoryJPA).save(entity);
    }

    @Test
    void findAll_shouldReturnListOfClienteModels() {
        when(clienteRepositoryJPA.findAll()).thenReturn(List.of(new Cliente()));

        List<ClienteModel> result = clienteRepository.findAll();

        assertNotNull(result);
        verify(clienteRepositoryJPA).findAll();
    }

    @Test
    void findById_shouldReturnClienteModelIfExists() {
        Cliente entity = new Cliente();
        when(clienteRepositoryJPA.findById(1L)).thenReturn(Optional.of(entity));

        Optional<ClienteModel> result = clienteRepository.findById(1L);

        assertTrue(result.isPresent());
        verify(clienteRepositoryJPA).findById

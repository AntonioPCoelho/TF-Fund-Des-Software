import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Pagamento;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios.PagamentoRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.PagamentoRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PagamentoRepositoryTest {

    @Mock
    private PagamentoRepositoryJPA pagamentoRepositoryJPA;

    @InjectMocks
    private PagamentoRepository pagamentoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldSaveAndReturnPagamentoModel() {
        PagamentoModel model = new PagamentoModel();
        Pagamento entity = Pagamento.fromModel(model);
        when(pagamentoRepositoryJPA.save(entity)).thenReturn(entity);

        PagamentoModel result = pagamentoRepository.save(model);

        assertNotNull(result);
        verify(pagamentoRepositoryJPA).save(entity);
    }
}

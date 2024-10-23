package com.coelho.sistcontrol.dominio.servicos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;

@Service
public class AssinaturaService {

    private final IAssinaturaRepository assinaturaRepository;
    private final IAplicativoRepository aplicativoRepository;

    public AssinaturaService(IAssinaturaRepository assinaturaRepository,
            IAplicativoRepository aplicativoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    public AssinaturaModel criarNovaAssinatura(ClienteModel cliente, AplicativoModel aplicativo) {
        // Lógica para criar uma nova assinatura
        Date dataInicio = new Date(); // Data de início da assinatura (hoje)
        Date dataFim = addDays(dataInicio, 7); // 7 dias grátis inicialmente

        AssinaturaModel novaAssinatura = new AssinaturaModel(0, dataInicio, dataFim, aplicativo, cliente, "ATIVA");

        // Salvar a nova assinatura
        return assinaturaRepository.save(novaAssinatura);
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public AssinaturaModel salvarAssinatura(AssinaturaModel assinaturaModel) {
        AssinaturaModel salvo = assinaturaRepository.save(assinaturaModel);
        return salvo;
    }

    // Verificar se uma assinatura é válida (com java.util.Date)
    public boolean isAssinaturaValida(Long id) {
        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findByid(id);
        if (assinaturaOpt.isPresent()) {
            Assinatura assinatura = assinaturaOpt.get();
            return assinatura.getFimVigencia().after(new Date()); // Verifica se a vigência é posterior à data atual
        }
        return false;
    }

    public List<AssinaturaModel> listarAssinaturasPorCliente(Long clienteId) {
        return assinaturaRepository.findByClienteId(clienteId).stream()
                .collect(Collectors.toList());
    }

    public List<ClienteModel> listarAssinantesPorAplicativo(Long aplicativoId) {
        AplicativoModel aplicativo = aplicativoRepository.findById(aplicativoId)
                .orElseThrow(() -> new IllegalArgumentException("Aplicativo não encontrado"));
        return assinaturaRepository.findClientesByAplicativo(aplicativo.getId());
    }

    public void atualizarValidadeAssinatura(Long id, Date novaDataFim) {
        AssinaturaModel assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));
        assinatura.setFimVigencia(novaDataFim);
        assinaturaRepository.save(assinatura);
    }

    public List<AssinaturaModel> listarAssinaturasPorstatus(String status) {
        List<AssinaturaModel> assinaturas = switch (status.toUpperCase()) {
            case "ATIVAS" -> assinaturaRepository.findByStatus("ATIVA");
            case "CANCELADAS" -> assinaturaRepository.findByStatus("CANCELADA");
            default -> assinaturaRepository.findAll();
        };

        return assinaturas;
    }
}

package com.coelho.sistcontrol.dominio.servicos;

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

@Service
public class AssinaturaService {

    private final IAssinaturaRepository assinaturaRepository;
    private final IAplicativoRepository aplicativoRepository;

    public AssinaturaService(IAssinaturaRepository assinaturaRepository,
            IAplicativoRepository aplicativoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    public AssinaturaModel salvarAssinatura(AssinaturaModel assinaturaModel) {
        AssinaturaModel salvo = assinaturaRepository.save(assinaturaModel);
        return salvo;
    }

    public boolean isAssinaturaValida(Long clienteId, Long aplicativoId) {
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findByClienteIdAndAplicativoId(clienteId,
                aplicativoId);
        return assinaturaOpt.map(assinatura -> assinatura.getFimVigencia().after(new Date())).orElse(false);
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

    public void atualizarValidadeAssinatura(Long assinaturaId, Date novaDataFim) {
        AssinaturaModel assinatura = assinaturaRepository.findById(assinaturaId)
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

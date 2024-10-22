package com.coelho.sistcontrol.dominio.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AplicativoRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AssinaturaRepository;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final AplicativoRepository aplicativoRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository,
            AplicativoRepository aplicativoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    public AssinaturaModel salvarAssinatura(Long clienteId, Long aplicativoId, AssinaturaModel assinaturaModel) {
        Assinatura entity = Assinatura.fromModel(assinaturaModel);
        entity.setId(clienteId);
        entity.setId(aplicativoId);
        Assinatura salvo = assinaturaRepository.save(entity);
        return salvo.toModel();
    }

    // Verificar se uma assinatura é válida (com java.util.Date)
    public boolean isAssinaturaValida(Long codass) {
        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findByAssinaturaId(codass);
        if (assinaturaOpt.isPresent()) {
            Assinatura assinatura = assinaturaOpt.get();
            return assinatura.getFimVigencia().after(new Date()); // Verifica se a vigência é posterior à data atual
        }
        return false;
    }

    public List<AssinaturaModel> listarAssinaturasPorCliente(Long clienteId) {
        return assinaturaRepository.findByClienteId(clienteId).stream()
                .map(Assinatura::toModel)
                .collect(Collectors.toList());
    }

    // Listar assinantes de um aplicativo
    public List<Cliente> listarAssinantesPorAplicativo(Long aplicativoId) {
        Aplicativo aplicativo = aplicativoRepository.findById(aplicativoId)
                .orElseThrow(() -> new IllegalArgumentException("Aplicativo não encontrado"));
        return assinaturaRepository.findClientesByAplicativo(aplicativo);
    }

    // Receber notificação de pagamento e atualizar validade da assinatura
    public void atualizarValidadeAssinatura(Long assinaturaId, Date novaDataFim) {
        Assinatura assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));
        assinatura.setFimVigencia(novaDataFim);
        assinaturaRepository.save(assinatura);
    }

    public List<AssinaturaModel> listarAssinaturasPorstatus(String status) {
        List<Assinatura> assinaturas;
        // Trocar a logica para os use cases
        switch (status.toUpperCase()) {
            case "ATIVAS":
                assinaturas = assinaturaRepository.findByStatus("ATIVA");
                break;
            case "CANCELADAS":
                assinaturas = assinaturaRepository.findByStatus("CANCELADA");
                break;
            case "TODAS":
            default:
                assinaturas = assinaturaRepository.findAll();
                break;
        }
        
        return assinaturas.stream()
                .map(Assinatura::toModel)
                .collect(Collectors.toList());
    }
    
}

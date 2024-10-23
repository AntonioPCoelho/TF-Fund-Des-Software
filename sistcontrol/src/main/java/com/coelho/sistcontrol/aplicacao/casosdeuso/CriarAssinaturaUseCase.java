package com.coelho.sistcontrol.aplicacao.casosdeuso;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.aplicacao.dtos.AssinaturaDTO;
import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;

@Service
public class CriarAssinaturaUseCase {

    private final AssinaturaService assinaturaService;
    private final IClienteRepository clienteRepository;
    private final IAplicativoRepository aplicativoRepository;

    public CriarAssinaturaUseCase(AssinaturaService assinaturaService, IClienteRepository clienteRepository, IAplicativoRepository aplicativoRepository) {
        this.assinaturaService = assinaturaService;
        this.clienteRepository = clienteRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    public AssinaturaDTO execute(Long clienteId, Long aplicativoId) {
        // Verificar se o cliente e o aplicativo existem
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        AplicativoModel aplicativo = aplicativoRepository.findById(aplicativoId)
                .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));

        // Criar uma nova assinatura
        AssinaturaModel novaAssinatura = assinaturaService.criarNovaAssinatura(cliente, aplicativo);

        // Retornar DTO de resposta
        return new AssinaturaDTO(novaAssinatura.getId(), novaAssinatura.getInicioVigencia(),
                novaAssinatura.getFimVigencia(), novaAssinatura.getApp(), novaAssinatura.getCliente(), novaAssinatura.getstatus());
    }
}


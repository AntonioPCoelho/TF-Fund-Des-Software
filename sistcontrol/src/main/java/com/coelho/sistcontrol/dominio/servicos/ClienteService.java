package com.coelho.sistcontrol.dominio.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteModel cadastrar(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public List<ClienteModel> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Retorna a lista de assinaturas do cliente informado
    public List<AssinaturaModel> listarAssinaturasPorCliente(Long clienteId) {
        ClienteModel cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        return Cliente.fromModel(cliente).getAssinaturas().stream()
                .map(assinatura -> assinatura.toModel())
                .toList();
    }

    public ClienteModel editarCliente(Long id, ClienteModel clienteModel) {
        ClienteModel entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Cliente.fromModel(entity).atualizarDados(clienteModel);  // Atualiza os dados do cliente
        ClienteModel atualizado = clienteRepository.save(entity);
        return atualizado;
    }

    public void deleteAll(){
        
    }
}

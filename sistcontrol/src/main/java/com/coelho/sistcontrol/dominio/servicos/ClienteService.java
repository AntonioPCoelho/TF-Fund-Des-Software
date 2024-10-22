package com.coelho.sistcontrol.dominio.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(ClienteModel cliente) {
        return clienteRepository.save(Cliente.fromModel(cliente));
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Retorna a lista de assinaturas do cliente informado
    public List<AssinaturaModel> listarAssinaturasPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        return cliente.getAssinaturas().stream()
                .map(assinatura -> assinatura.toModel())
                .toList();
    }
    
    public ClienteModel editarCliente(Long id, ClienteModel clienteModel) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        entity.atualizarDados(clienteModel);  // Atualiza os dados do cliente
        Cliente atualizado = clienteRepository.save(entity);
        return atualizado.toModel();
    }
}

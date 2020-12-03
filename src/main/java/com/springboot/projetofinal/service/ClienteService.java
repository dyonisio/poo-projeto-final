package com.springboot.projetofinal.service;

import java.util.List;
import java.util.Optional;

import com.springboot.projetofinal.model.Reserva;
import com.springboot.projetofinal.dto.ClienteDTO;
import com.springboot.projetofinal.model.Cliente;
import com.springboot.projetofinal.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente fromDTO(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        return cliente;
    }

    public List<Cliente> getAllClientes(){
        return repository.getAllClientes();
    }

    public Cliente getClienteByCod(int cod){
        Optional<Cliente> op = repository.getClienteByCod(cod);

        return op.orElseThrow( () ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
        );
    }

    public void removeByCod(int cod){
        repository.removeCliente(getClienteByCod(cod));
    }

    public Cliente saveCliente(Cliente cliente){
        return repository.saveCliente(cliente);
    }

    public Cliente updateCliente(Cliente cliente){
        getClienteByCod(cliente.getCod());
        return repository.updateCliente(cliente);
    }

    public List<Reserva> getAllReservasFromCliente(int cod){
        Cliente cliente = getClienteByCod(cod);

        return cliente.getReservas();
    }
}

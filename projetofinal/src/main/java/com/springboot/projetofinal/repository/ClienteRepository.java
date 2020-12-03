package com.springboot.projetofinal.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.springboot.projetofinal.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private int nextCode;

    @PostConstruct
    public void init() {
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        Cliente c3 = new Cliente();

        c1.setNome("Mateus");
        c1.setEndereco("Rua Linda, 100");
        c1.setCpf("12312312332");

        c2.setNome("Dyonisio");
        c2.setEndereco("Rua Feia, 99");
        c2.setCpf("12312312333");

        c3.setNome("Lindo");
        c3.setEndereco("Rua Feia, 98");
        c3.setCpf("12312312334");

        saveCliente(c1);
        saveCliente(c2);
        saveCliente(c3);
    }

    public List<Cliente> getAllClientes(){
        return clientes;
    }

    public Optional<Cliente> getClienteByCod(int cod){
        for(Cliente cliente : clientes)
            if(cliente.getCod() == cod)
                return Optional.of(cliente);

        return Optional.empty();
    }

    public Cliente saveCliente(Cliente cliente){
        cliente.setCod(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

    public void removeCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public Cliente updateCliente(Cliente newCliente){
        Cliente cliente = getClienteByCod(newCliente.getCod()).get();

        if(cliente != null){
            cliente.setEndereco(newCliente.getEndereco());
            cliente.setNome(newCliente.getNome());
            cliente.setCpf(newCliente.getCpf());
        }

        return cliente;
    }
}

package com.springboot.projetofinal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.springboot.projetofinal.dto.ClienteDTO;
import com.springboot.projetofinal.dto.ReservaDTO;
import com.springboot.projetofinal.model.Cliente;
import com.springboot.projetofinal.model.Reserva;
import com.springboot.projetofinal.service.ClienteService;
import com.springboot.projetofinal.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping()
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{cod}")
    public ResponseEntity<Cliente> getClienteByCod(@PathVariable int cod){
        Cliente cliente = clienteService.getClienteByCod(cod);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/{codCliente}/veiculos/{codVeiculo}")
    public ResponseEntity<Reserva> criarReserva(@Valid @RequestBody ReservaDTO reservaDTO, @PathVariable int codCliente, @PathVariable int codVeiculo){
        Reserva reserva = reservaService.fromDTO(reservaDTO);
        return ResponseEntity.ok(reservaService.criarReserva(reserva, codCliente, codVeiculo));
    }
    @GetMapping("/{cod}/reservas")
    public List<Reserva> getReservasFromCliente(@PathVariable int cod){
        return clienteService.getAllReservasFromCliente(cod);
    }

    @PostMapping()
    public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody ClienteDTO clienteDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        Cliente newCliente = clienteService.saveCliente(cliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + newCliente.getCod()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{cod}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable int cod){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setCod(cod);
        cliente = clienteService.updateCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity<Void> removeCliente(@PathVariable int cod){
        clienteService.removeByCod(cod);
        return ResponseEntity.noContent().build();
    }
}

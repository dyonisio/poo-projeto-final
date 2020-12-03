package com.springboot.projetofinal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.springboot.projetofinal.dto.VeiculoDTO;
import com.springboot.projetofinal.model.Reserva;
import com.springboot.projetofinal.model.Veiculo;
import com.springboot.projetofinal.service.VeiculoService;

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
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping()
    public List<Veiculo> getVeiculos(){
        return veiculoService.getAllVeiculos();
    }

    @GetMapping("/{cod}")
    public ResponseEntity<Veiculo> getClienteByCod(@PathVariable int cod){
        Veiculo veiculo = veiculoService.getVeiculoByCod(cod);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("/{cod}/reservas")
    public List<Reserva> getReservasFromVeiculo(@PathVariable int cod){
        return veiculoService.getAllReservasFromVeiculo(cod);
    }

    @PostMapping()
    public ResponseEntity<Veiculo> saveCliente(@Valid @RequestBody VeiculoDTO veiculoDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
        Veiculo newVeiculo = veiculoService.saveVeiculo(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + newVeiculo.getCod()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{cod}")
    public ResponseEntity<Veiculo> updateVeiculo(@RequestBody VeiculoDTO veiculoDTO, @PathVariable int cod){
        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
        veiculo.setCod(cod);
        veiculo = veiculoService.updateVeiculo(veiculo);
        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity<Void> removeVeiculo(@PathVariable int cod){
        veiculoService.removeByCod(cod);
        return ResponseEntity.noContent().build();
    }
}

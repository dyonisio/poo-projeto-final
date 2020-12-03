package com.springboot.projetofinal.service;

import java.util.List;
import java.util.Optional;

import com.springboot.projetofinal.dto.VeiculoDTO;
import com.springboot.projetofinal.model.Reserva;
import com.springboot.projetofinal.model.Veiculo;
import com.springboot.projetofinal.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    public Veiculo fromDTO(VeiculoDTO veiculoDTO){
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setValorDiaria(veiculoDTO.getValorDiaria());
        return veiculo;
    }

    public List<Veiculo> getAllVeiculos(){
        return repository.getAllVeiculos();
    }

    public Veiculo getVeiculoByCod(int cod){
        Optional<Veiculo> op = repository.getVeiculoByCod(cod);

        return op.orElseThrow( () ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado")
        );
    }

    public void removeByCod(int cod){
        repository.removeVeiculo(getVeiculoByCod(cod));
    }

    public Veiculo saveVeiculo(Veiculo veiculo){
        return repository.saveVeiculo(veiculo);
    }

    public Veiculo updateVeiculo(Veiculo veiculo){
        getVeiculoByCod(veiculo.getCod());
        return repository.updateVeiculo(veiculo);
    }

    public List<Reserva> getAllReservasFromVeiculo(int cod){
        Veiculo veiculo = getVeiculoByCod(cod);

        return veiculo.getReservas();
    }
}

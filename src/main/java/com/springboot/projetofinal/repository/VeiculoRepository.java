package com.springboot.projetofinal.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.springboot.projetofinal.model.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class VeiculoRepository {
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();
    private int nextCode;
    
    @PostConstruct
    public void init() {
        Veiculo c1 = new Veiculo();
        Veiculo c2 = new Veiculo();
        Veiculo c3 = new Veiculo();

        c1.setModelo("Fiat Toro");;
        c1.setValorDiaria(150);

        c2.setModelo("Fiat Strada");;
        c2.setValorDiaria(100);

        c2.setModelo("Fiat Uno");;
        c2.setValorDiaria(30);

        saveVeiculo(c1);
        saveVeiculo(c2);
        saveVeiculo(c3);
    }

    public List<Veiculo> getAllVeiculos(){
        return veiculos;
    }

    public Optional<Veiculo> getVeiculoByCod(int cod){
        for(Veiculo veiculo : veiculos)
            if(veiculo.getCod() == cod)
                return Optional.of(veiculo);
        
        return Optional.empty();
    }

    public Veiculo saveVeiculo(Veiculo veiculo){
        veiculo.setCod(nextCode++);
        veiculos.add(veiculo);
        return veiculo;
    }

    public void removeVeiculo(Veiculo veiculo){
        veiculos.remove(veiculo);
    }

    public Veiculo updateVeiculo(Veiculo newVeiculo){
        Veiculo veiculo = getVeiculoByCod(newVeiculo.getCod()).get();

        if(veiculo != null){
            veiculo.setValorDiaria(newVeiculo.getValorDiaria());
        }

        return veiculo;
    }
}

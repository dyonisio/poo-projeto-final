package com.springboot.projetofinal.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class VeiculoDTO {
    @NotBlank(message = "Modelo obrigatório!")
    @Length(min=2,max=40, message = "Modelo deve ter no mínimo 2 e no máximo 40 caracteres!")
    private String modelo;

    private double valorDiaria;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    
}

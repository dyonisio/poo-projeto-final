package com.springboot.projetofinal.dto;

import javax.validation.constraints.NotBlank;

public class ReservaDTO {
    @NotBlank(message = "Data de Inicio obrigatória!")
    private String dataInicio;

    @NotBlank(message = "Data de Inicio obrigatória!")
    private String dataFim;

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}

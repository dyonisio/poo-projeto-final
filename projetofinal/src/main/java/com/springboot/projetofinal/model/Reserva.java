package com.springboot.projetofinal.model;

import java.time.LocalDate;

public class Reserva {
    private int cod;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private Cliente cliente;
    private Veiculo veiculo;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public double getValorReserva(){
        double valorDiaria = veiculo.getValorDiaria();
        return valorDiaria*(dataFim.compareTo(dataInicio));
    }
}

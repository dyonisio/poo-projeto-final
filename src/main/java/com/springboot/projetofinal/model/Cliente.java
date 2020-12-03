package com.springboot.projetofinal.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cliente {
    private int cod;
    private String nome;
    private String endereco;
    private String cpf;

    @JsonIgnore
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean addReserva(Reserva reserva){
        return reservas.add(reserva);
    }

    public boolean removeReserva(Reserva reserva){
        return reservas.remove(reserva);
    } 
}

package com.springboot.projetofinal.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.projetofinal.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository {
    private List<Reserva> reservas = new ArrayList<Reserva>();
    private int nextCode;

    public List<Reserva> getAllReservas(){
        return reservas;
    }

    public Optional<Reserva> getReservaByCod(int cod){
        for(Reserva reserva : reservas)
            if(reserva.getCod() == cod)
                return Optional.of(reserva);

        return Optional.empty();
    }

    public Reserva saveReserva(Reserva reserva){
        reserva.setCod(nextCode++);
        reservas.add(reserva);
        return reserva;
    }
}

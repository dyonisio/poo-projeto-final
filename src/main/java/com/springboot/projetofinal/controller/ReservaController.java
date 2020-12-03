package com.springboot.projetofinal.controller;

import java.util.List;

import com.springboot.projetofinal.model.Reserva;
import com.springboot.projetofinal.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping()
    public List<Reserva> getReservas(){
        return reservaService.getAllReservas();
    }

    @GetMapping("/{cod}")
    public ResponseEntity<Reserva> getReservaByCod(@PathVariable int cod){
        Reserva reserva = reservaService.getReservaByCod(cod);
        return ResponseEntity.ok(reserva);
    }
}

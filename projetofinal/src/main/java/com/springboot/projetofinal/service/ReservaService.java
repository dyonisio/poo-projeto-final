package com.springboot.projetofinal.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.springboot.projetofinal.dto.ReservaDTO;
import com.springboot.projetofinal.model.Cliente;
import com.springboot.projetofinal.model.Reserva;
import com.springboot.projetofinal.model.Veiculo;
import com.springboot.projetofinal.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ReservaRepository repository;

    public Reserva fromDTO(ReservaDTO reservaDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate dataInicio = LocalDate.parse(reservaDTO.getDataInicio(), formatter);
        LocalDate dataFim = LocalDate.parse(reservaDTO.getDataFim(), formatter);

        Reserva reserva = new Reserva();
        reserva.setDataInicio(dataInicio);
        reserva.setDataFim(dataFim);

        return reserva;
    }
    
    public Reserva criarReserva(Reserva reserva, int codCliente, int codVeiculo) {
        //Verificar se existe o codCliente, se nao existir 404 NotFound, ao lançar, não continua a execução
        Cliente cliente = clienteService.getClienteByCod(codCliente);
        //Verificar se existe o codVeiculo, se nao existir 404 NotFound, ao lançar, não continua a execução
        Veiculo veiculo = veiculoService.getVeiculoByCod(codVeiculo);

        verificarData(reserva.getDataInicio(), reserva.getDataFim(), codCliente, codVeiculo);

        reserva.setCliente(cliente);
        cliente.addReserva(reserva);

        reserva.setVeiculo(veiculo);
        veiculo.addReserva(reserva);

        return repository.saveReserva(reserva);
    }

    private int verificarData(LocalDate dataInicio, LocalDate dataFim, int codCliente, int codVeiculo){
        LocalDate dataNow = LocalDate.now();

        //CMP1: Deverá ser maior que a data do sistema
        //CMP2: Não pode começar no Domingo
        //CMP3: Deverá ser maior que a data de Início
        Optional<Integer> cmp = Optional.of(1);
        
        cmp = Optional.of(dataNow.compareTo(dataInicio));
        if(dataInicio.compareTo(dataNow) <= 0){
            cmp = Optional.empty();

            return cmp.orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de Inicio deve ser maior que a data atual!")
            );
        }
        if(dataFim.compareTo(dataInicio) <= 0){
            cmp = Optional.empty();

            return cmp.orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de fim deve ser maior que a data de inicio!")
            );
        }

        DayOfWeek dayOfWeek;

        dayOfWeek = dataInicio.getDayOfWeek();
        if(dayOfWeek.equals(DayOfWeek.SUNDAY)){
            cmp = Optional.empty();

            return cmp.orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de inicio deve ser diferente de domingo!")
            );
        }

        dayOfWeek = dataFim.getDayOfWeek();
        if(dayOfWeek.toString() == "SUNDAY"){
            cmp = Optional.empty();

            return cmp.orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de fim deve ser diferente de domingo!")
            );
        }

        List<Reserva> reservas = repository.getAllReservas();
        for(Reserva reserva : reservas){
            if((!dataInicio.isAfter(reserva.getDataFim()) && !reserva.getDataInicio().isAfter(dataFim)) && reserva.getVeiculo().getCod() == codVeiculo){
                cmp = Optional.empty();

                return cmp.orElseThrow( () -> 
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "O carro já está reservado nessa data!")
                );
            }
        }

        return cmp.get();
    }

    public List<Reserva> getAllReservas(){
        return repository.getAllReservas();
    }

    public Reserva getReservaByCod(int cod){
        Optional<Reserva> op = repository.getReservaByCod(cod);

        return op.orElseThrow( () ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada")
        );
    }

    public Reserva saveReserva(Reserva reserva){
        return repository.saveReserva(reserva);
    }
}

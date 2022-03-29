package com.springBajo8.springBajo8.service.impl;

//import com.yoandypv.reactivestack.messages.domain.Message;
//import com.yoandypv.reactivestack.messages.repository.MessageRepository;
//import com.yoandypv.reactivestack.messages.service.MessageService;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }


    @Override
    public Flux<citasDTOReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    @Override
    public Flux<citasDTOReactiva> consultarFechaYHora(String fechaReserva, String horaReserva) {
        return IcitasReactivaRepository.findByFechaReservaCita(fechaReserva,horaReserva).filter(cita ->cita.getHoraReservaCita().equals(horaReserva));


    }

    @Override
    public Mono<citasDTOReactiva> cancelarCita(String id) {



        return IcitasReactivaRepository.findById(id).flatMap(
                citasDTOReactiva -> {

                     citasDTOReactiva.setEstadoReservaCita("cancelado");
                   return IcitasReactivaRepository.save(citasDTOReactiva);

                }
        );
    }

    @Override
    public Mono<String> consultarMedico(String id) {
        return IcitasReactivaRepository.findById(id).flatMap(
                citasDTOReactiva -> Mono.just("El nombre del médico que lo atenderá es: "+citasDTOReactiva.getNombreMedico())
        );
    }

    @Override
    public Flux<String> consultarPadecimientos(String idPaciente) {



        return IcitasReactivaRepository.findByIdPaciente(idPaciente).flatMap(citasDTOReactiva ->
                Flux.just("Los padecimientos del paciente son: " + citasDTOReactiva.getPadecimientos().toString()));
    }

//    @Override
//    public Mono<citasDTOReactiva> consultarMedico(String idPaciente, String nombreMedico) {
//        return null;
//    }

//    @Override
//    public  Mono<citasDTOReactiva>consultarMedico(String idPaciente, String nombreMedico)
//    {
//        return IcitasReactivaRepository.findByIdPaciente(idPaciente).flatMap(citasDTOReactiva ->
//        {
//            citasDTOReactiva.getNombreMedico();
//            citasDTOReactiva.getApellidosMedico();
//
//            return Mono.just();
//        });
//
//    }



}

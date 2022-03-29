package com.springBajo8.springBajo8.service.impl;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class citasReactivaServiceImplTest
{

    @MockBean
    IcitasReactivaService icitasReactivaServicee;

//    @Autowired
//    IcitasReactivaService icitasReactivaService;

    @Autowired
  private MockMvc mockMvc;





    @Test
    void obtenerCitas()throws Exception
    {
        citasDTOReactiva dtoReactiva = new citasDTOReactiva("1","1","Santiago","Cifuentes","Sofia","Gonzalez","22-03-31","1313","En reserva");

        Flux<citasDTOReactiva> citasDTOReactivas = new Flux<citasDTOReactiva>() {
            @Override
            public void subscribe(CoreSubscriber<? super citasDTOReactiva> coreSubscriber) {

            }
        };

        citasDTOReactivas.collectList();
//        citasDTOReactivas.add(new citasDTOReactiva("1","1","Santiago","Cifuentes","Sofia","Gonzalez","22-03-31","1313","En reserva"));
//        citasDTOReactivas.add(new citasDTOReactiva("2","2","Santiago","Cifuentes","Sofia","Gonzalez","22-03-31","1313","En reserva"));
        //Flux<citasDTOReactiva> citasDTOReactivaFlux =  icitasReactivaServicee.findAll();

        doReturn(citasDTOReactivas).when(icitasReactivaServicee).findAll();

        mockMvc.perform(get( "/citasReactivas"))
                .andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(0)));
    }

//    @Test
//    void test1()
//    {
//        citasDTOReactiva citas = new citasDTOReactiva();
//        citas.setNombrePaciente("Santiago");
//        citas .setApellidosMedico("González");
//        citas .setEstadoReservaCita("Reservadp");
//        citas .setNombreMedico("Sofía");
//        citas .setFechaReservaCita("22-03-31");
//        citas .setHoraReservaCita("2241");
//
//
//        Mono<citasDTOReactiva> citasDTOReactivaMono = icitasReactivaService.save(citas);
//        StepVerifier.create(citasDTOReactivaMono).expectNext(citas).verifyComplete();
//    }
//
//
//    @Test
//    @DisplayName("GET/biblioteca/recurso/{id}")
//    void citaCreada() throws Exception {
//
//
//        citasDTOReactiva citastoReturn= new citasDTOReactiva("1","1","Santiago","Cifuentes","Sofia","Gonzalez","22-03-31","1313","En reserva");
//        doReturn(Optional.of(citastoReturn)).when(icitasReactivaService).findAll();
//
//        mockMvc.perform(get("/biblioteca/recurso/{id}","1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                // Validate headers
//                .andExpect(jsonPath("$.id", is("1")))
//                .andExpect(jsonPath("$.tipo", is("libros")))
//                .andExpect(jsonPath("$.tematica", is("ciencias")))
//                .andExpect(jsonPath("$.disponible", is(true)));
//
//
////    }


}
//}
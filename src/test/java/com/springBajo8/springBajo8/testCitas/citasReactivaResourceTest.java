package com.springBajo8.springBajo8.testCitas;

import static org.junit.jupiter.api.Assertions.*;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class citasReactivaResourceTest {

    @Autowired
    WebTestClient webClient;
    @Autowired
    IcitasReactivaService icitasReactivaService;



    @Test
    @DisplayName("Trae todas las citas")
    void traerCitas() throws Exception {
        webClient.get().uri("/citasReactivas")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBodyList(citasDTOReactiva.class);
    }

    @Test
    @DisplayName("Citas por id")
    public void citasPorId() throws Exception {
        webClient.get().uri("/citasReactivas/{idPaciente}/byidPaciente", "1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBodyList(citasDTOReactiva.class);
    }
    @Test
    @DisplayName("getPadecimientos")
    public void getPadecimientos() throws Exception {



        webClient.get().uri("/citasReactivas/padecimientos/{idPaciente}", "6")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(TEXT_PLAIN_VALUE)
                .expectBodyList(String.class);
    }


    @Test
    void generarCita()
    {
        citasDTOReactiva citas = new citasDTOReactiva();
        citas.setNombrePaciente("Santiago");
        citas .setApellidosMedico("González");
        citas .setEstadoReservaCita("Reservadp");
        citas .setNombreMedico("Sofía");
        citas .setFechaReservaCita("22-03-31");
        citas .setHoraReservaCita("2241");


        Mono<citasDTOReactiva> citasDTOReactivaMono = icitasReactivaService.save(citas);
        StepVerifier.create(citasDTOReactivaMono).expectNext(citas).verifyComplete();
    }






}
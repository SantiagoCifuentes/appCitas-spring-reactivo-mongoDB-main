package com.springBajo8.springBajo8.web;

import static org.junit.jupiter.api.Assertions.*;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class citasReactivaResourceTest {

    @Autowired
    WebTestClient webClient;

    @Test
    @DisplayName("Trae todas las citas")
    void traerCitas() throws Exception {
        webClient.get().uri("/citasReactivas")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBodyList(citasDTOReactiva.class);
    }

}
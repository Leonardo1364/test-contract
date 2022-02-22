package com.test.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mock.contract.v1.ClientController;
import com.test.mock.model.ClientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static com.test.mock.common.gender.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebFluxTest
@ContextConfiguration(classes = {ClientController.class, ClientEntity.class, ApplicationContext.class})
class MockApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ClientController clientController;

    @Autowired
    ApplicationContext applicationContext;

    @MockBean
    ClientEntity clientEntity;

    @BeforeEach
    void controller() throws Exception {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .configureClient()
                .baseUrl("/v1/client/")
                .build();
    }

    @Test
    void getAll() throws Exception {
        var id = "id";
        var name = "name";
        var age = 20;
        var gender = MALE;

        Mockito.when(clientController.getAll())
                .thenReturn(Flux.just(ClientEntity.builder()
                        .id(id)
                        .name(name)
                        .age(age)
                        .gender(gender)
                        .build()));

        webTestClient.get()
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ClientEntity.class)
                .consumeWith(client -> {
                    ClientEntity clientEntityExpected = client.getResponseBody();
                    assertEquals(Objects.requireNonNull(clientEntityExpected).getId(), id);
                    assertEquals(Objects.requireNonNull(clientEntityExpected).getName(), name);
                    assertEquals(Objects.requireNonNull(clientEntityExpected).getAge(), age);
                    assertEquals(Objects.requireNonNull(clientEntityExpected).getGender(), gender);
                });

    }

}

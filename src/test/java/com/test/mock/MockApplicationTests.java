package com.test.mock;

import com.test.mock.model.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static com.test.mock.common.gender.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class MockApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void getAll() throws Exception {
        ClientEntity clientExpected = ClientEntity.builder()
                .id("id")
                .name("name")
                .age(20)
                .gender(MALE)
                .build();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/client/").build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(clientExpected.getId())
                .jsonPath("$.[0].name").isEqualTo(clientExpected.getName())
                .jsonPath("$.[0].age").isEqualTo(clientExpected.getAge())
                .jsonPath("$.[0].gender").isEqualTo(clientExpected.getGender());
    }

    @Test
    void post_test() throws Exception {
        ClientEntity clientExpected = ClientEntity.builder()
                .id("id")
                .name("name")
                .age(20)
                .gender(MALE)
                .build();
        ClientEntity clientActual = ClientEntity.builder()
                .id("id")
                .name("name")
                .age(20)
                .gender(MALE)
                .build();

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("v1/client/").build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ClientEntity.class)
                .consumeWith(entityExchangeResult -> {
                    assertEquals(clientExpected.getId(), clientActual.getId());
                    assertEquals(clientExpected.getName(), clientActual.getName());
                    assertEquals(clientExpected.getAge(), clientActual.getAge());
                    assertEquals(clientExpected.getGender(), clientActual.getGender());
                });
    }

}


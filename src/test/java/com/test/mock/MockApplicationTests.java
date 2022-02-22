package com.test.mock;

import com.test.mock.model.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.test.mock.common.gender.Gender.MALE;

//@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class MockApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    /*@BeforeEach
    void controller() throws Exception {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .configureClient()
                .baseUrl("/v1/client/")
                .build();
    }*/

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
                .expectBody(ClientEntity.class).isEqualTo(clientExpected);

    }

}


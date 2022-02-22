package com.test.mock.contract.v1;

import com.test.mock.model.ClientEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.test.mock.common.gender.Gender.MALE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/client/")
public class ClientController {

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<ClientEntity> save(ClientEntity client) {
        return Mono.just(ClientEntity.builder()
                .name(client.getName())
                .age(client.getAge())
                .gender(client.getGender())
                .build());
    }

    @GetMapping
    @ResponseStatus(OK)
    public Mono<ClientEntity> getAll() {
        return Mono.just(ClientEntity.builder()
                .id("id")
                .name("name")
                .age(20)
                .gender(MALE)
                .build());
    }
}

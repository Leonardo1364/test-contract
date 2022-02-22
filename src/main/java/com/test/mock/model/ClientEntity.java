package com.test.mock.model;

import com.test.mock.common.gender.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientEntity {

    private String id;
    private String name;
    private Integer age;
    private Gender gender;
}

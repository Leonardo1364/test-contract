package com.test.mock.common.gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Gender {

    FEMALE("FEMALE"), MALE("MALE");

    private String name;

}

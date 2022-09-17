package com.enums;

import lombok.Getter;

@Getter
public enum ActiveStatus {
    ACTIVE("A"),
    INACTIVE("I");

    private String value;

    ActiveStatus(String value) {
        this.value = value;
    }
}
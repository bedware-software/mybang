package com.sebastian_daschner.example.coffee;

import java.util.stream.Stream;

public enum CoffeeType {

    CAPPUCCINO, ESPRESSO, FILTER;

    public static CoffeeType fromString(String string) {
        return Stream.of(CoffeeType.values())
                .filter(t -> t.name().equalsIgnoreCase(string))
                .findAny().orElse(null);
    }

}

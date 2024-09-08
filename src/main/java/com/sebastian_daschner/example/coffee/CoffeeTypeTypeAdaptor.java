package com.sebastian_daschner.example.coffee;

import javax.json.bind.adapter.JsonbAdapter;

public class CoffeeTypeTypeAdaptor implements JsonbAdapter<CoffeeType, String> {

    @Override
    public String adaptToJson(CoffeeType coffeeType) {
        return coffeeType.name().toLowerCase();
    }

    @Override
    public CoffeeType adaptFromJson(String string) {
        return CoffeeType.fromString(string);
    }

}

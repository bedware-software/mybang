package com.sebastian_daschner.example.coffee;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonbConfigurator implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class<?> type) {
        JsonbConfig config = new JsonbConfig()
                .withAdapters(new CoffeeTypeTypeAdaptor());
//                .withPropertyVisibilityStrategy(new PrivateVisibilityStrategy());

        return JsonbBuilder.newBuilder()
                .withConfig(config)
                .build();
    }

}

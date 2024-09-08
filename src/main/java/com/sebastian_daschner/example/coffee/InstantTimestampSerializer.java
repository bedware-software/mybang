package com.sebastian_daschner.example.coffee;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.time.Instant;

public class InstantTimestampSerializer implements JsonbSerializer<Instant> {

    @Override
    public void serialize(Instant instant, JsonGenerator generator, SerializationContext ctx) {
        generator.write(instant.toEpochMilli());
    }

}

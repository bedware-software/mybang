package com.sebastian_daschner.example.coffee;

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.json.bind.annotation.JsonbVisibility;
import java.time.Instant;
import java.util.UUID;

//@JsonbVisibility(PrivateVisibilityStrategy.class)
public class CoffeeOrder {

    @JsonbTransient
    private UUID uuid;

    @JsonbTypeSerializer(InstantTimestampSerializer.class)
    private Instant created;

    private CoffeeType type;

    public CoffeeOrder() {
        this.uuid = UUID.randomUUID();
        this.created = Instant.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
               "uuid=" + uuid +
               ", created=" + created +
               ", type=" + type +
               '}';
    }
}

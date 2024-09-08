package org.bedware_software.tools.mybang;

import java.time.Instant;

public class Bang {

    private Instant created;
    private String name;
    private String shortcut;
    private BangType type;

    public Bang(String name, String shortcut, BangType type) {
        this.created = Instant.now();
        this.name = name;
        this.shortcut = shortcut;
        this.type = type;
    }

    public Instant getCreated() {
        return created;
    }
    public String getName() {
        return name;
    }
    public String getShortcut() {
        return shortcut;
    }
    public BangType getType() {
        return type;
    }
}


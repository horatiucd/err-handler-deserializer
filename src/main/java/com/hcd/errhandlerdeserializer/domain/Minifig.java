package com.hcd.errhandlerdeserializer.domain;

public record Minifig(String id,
                      Size size,
                      String name) {

    public Minifig(String name) {
        this(null, null, name);
    }

    public enum Size {
        SMALL, MEDIUM, BIG;
    }
}

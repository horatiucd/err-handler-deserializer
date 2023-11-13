package com.hcd.errhandlerdeserializer.domain;

import java.util.UUID;

public record Minifig(String id,
                      Size size,
                      String name) {

    public enum Size {
        SMALL, MEDIUM, BIG;
    }
}

package com.hcd.errhandlerdeserializer.domain;

import lombok.Data;

@Data
public class MinifigRequest {

    private String id;
    private Type type;
    private String name;

    public enum Type {
        GOOD, EVIL;
    }
}

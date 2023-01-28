package com.example.courseworkthree.model;

import com.example.courseworkthree.exception.NotFoundSocsSizeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public enum Size {
    S(36),
    M(38),
    L(40),
    X(42),
    XXL(48);

    private final float description;

    Size(float description) {
        this.description = description;
    }

    @JsonValue
    public float getDescription() {
        return description;
    }

    @JsonCreator
    public static Size forValues(String description) {
        try {
            for (Size s : Size.values()) {
                if (Float.parseFloat(description) == s.description) {
                    return s;
                }
            }
        } catch (NotFoundSocsSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}





package com.utfpr.moteloo24s.model;

public enum Status {
    DISPONIVEL("Disponível"),
    OCUPADO("Ocupado");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

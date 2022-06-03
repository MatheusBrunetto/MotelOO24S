package com.utfpr.moteloo24s.model;

public enum Situation {
    ABERTA("Aberta"),
    FINALIZADA("Finalizada");

    private String description;

    Situation (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

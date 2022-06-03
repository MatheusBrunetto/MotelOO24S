package com.utfpr.moteloo24s.model;

public enum PeriodType {
    PERIODO("Período"),
    PERNOITE("Pernoite");

    private String description;

    PeriodType (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

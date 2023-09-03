package com.oreillys.oreillysinvoice.dto;

public enum TenderType {
    CASH("cash"),
    CREDIT("credit");

    private String label;

    TenderType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

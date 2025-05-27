package com.e_sharing.E_sharing.Enum;

public enum LeadState {
    RENTED(0, "Rented"),
    CANCELLED(1, "Cancelled");

    private int id;
    private String name;

    LeadState(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

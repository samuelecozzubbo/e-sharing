package com.e_sharing.E_sharing.Model;

public enum TipoVeicolo {

    MONOPATTINO(1L , "Monopattino"),
    BICICLETTA(2L , "Bicicletta"),
    SKATEBOARD(3L , "Skateboard");


    private final Long id;
    private final String tipoVeicolo;

    TipoVeicolo(Long id, String tipoVeicolo) {
        this.id = id;
        this.tipoVeicolo = tipoVeicolo;
    }

    public Long getId() {
        return id;
    }

    public String getTipoVeicolo() {
        return tipoVeicolo;
    }
}

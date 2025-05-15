package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;

public class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TipoVeicolo tipoVeicolo;
    private Double costoNoleggio;
    private Double livelloBatteria;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;
}

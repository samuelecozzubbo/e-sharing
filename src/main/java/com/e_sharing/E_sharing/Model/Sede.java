package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Sede")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String indirizzo;
    private Integer capienza;

    @OneToMany(mappedBy = "veicolo")
    private List<Veicolo> veicoli;

}

package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entità che rappresenta un sito (Site) nella piattaforma di e-sharing.
 * <p>
 * Un sito è un luogo fisico dove sono presenti uno o più veicoli disponibili per il noleggio.
 * </p>
 *
 * <ul>
 *   <li>{@code id}: identificativo univoco del sito</li>
 *   <li>{@code name}: nome del sito</li>
 *   <li>{@code address}: indirizzo del sito</li>
 *   <li>{@code capacity}: capacità massima di veicoli del sito</li>
 *   <li>{@code city}: città in cui si trova il sito</li>
 *   <li>{@code vehicles}: lista dei veicoli associati a questo sito</li>
 * </ul>
 */
@Entity
@Table(name = "site")
public class Site {

    // Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Integer capacity;
    private String city;

    // Relazione con Vehicle
    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    /**
     * Costruttore vuoto per JPA.
     */
    public Site() {}

    /**
     * Costruttore completo.
     * @param name nome del sito
     * @param address indirizzo del sito
     * @param capacity capacità massima di veicoli del sito
     * @param city città in cui si trova il sito
     */
    public Site(String name, String address, Integer capacity , String city) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.city = city;
    }

    /**
     * Restituisce l'identificativo del sito.
     * @return id del sito
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del sito.
     * @param id id del sito
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del sito.
     * @return nome del sito
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del sito.
     * @param name nome del sito
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'indirizzo del sito.
     * @return indirizzo del sito
     */
    public String getAddress() {
        return address;
    }

    /**
     * Imposta l'indirizzo del sito.
     * @param address indirizzo del sito
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Restituisce la capacità massima di veicoli del sito.
     * @return capacità del sito
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Imposta la capacità massima di veicoli del sito.
     * @param capacity capacità del sito
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Restituisce la città in cui si trova il sito.
     * @return città del sito
     */
    public String getCity() {
        return city;
    }

    /**
     * Imposta la città in cui si trova il sito.
     * @param city città del sito
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Restituisce la lista dei veicoli associati a questo sito.
     * @return lista di veicoli
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Imposta la lista dei veicoli associati a questo sito.
     * @param vehicles lista di veicoli
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
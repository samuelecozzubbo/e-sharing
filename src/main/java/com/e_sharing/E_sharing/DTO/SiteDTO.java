package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Model.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) utilizzato per rappresentare e trasferire i dati relativi ad un sito (Site)
 * tra i vari livelli dell'applicazione senza esporre direttamente l'entità del dominio.
 * <p>
 * Contiene le informazioni principali di un sito:
 * <ul>
 *   <li>{@code id}: identificativo univoco del sito</li>
 *   <li>{@code name}: nome del sito</li>
 *   <li>{@code address}: indirizzo del sito</li>
 *   <li>{@code capacity}: capacità massima del sito</li>
 *   <li>{@code city}: città in cui si trova il sito</li>
 * </ul>
 * </p>
 */
public class SiteDTO {

    private Long id;
    private String name;
    private String address;
    private Integer capacity;
    private String city;

    /**
     * Costruttore vuoto per la creazione di un oggetto SiteDTO senza parametri.
     */
    public SiteDTO() {}

    /**
     * Costruttore per la creazione di un oggetto SiteDTO con parametri.
     *
     * @param name nome del sito
     * @param address indirizzo del sito
     * @param capacity capacità massima del sito
     * @param city città in cui si trova il sito
     */
    public SiteDTO(String name, String address, Integer capacity , String city) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.city = city;
    }

    /**
     * Restituisce l'identificativo univoco del sito.
     *
     * @return id del sito
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'identificativo univoco del sito.
     *
     * @param id id del sito
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del sito.
     *
     * @return nome del sito
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del sito.
     *
     * @param name nome del sito
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'indirizzo del sito.
     *
     * @return indirizzo del sito
     */
    public String getAddress() {
        return address;
    }

    /**
     * Imposta l'indirizzo del sito.
     *
     * @param address indirizzo del sito
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Restituisce la capacità massima del sito.
     *
     * @return capacità del sito
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Imposta la capacità massima del sito.
     *
     * @param capacity capacità del sito
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Metodo di utilità per convertire una collezione di {@link Site} in una lista di {@link SiteDTO}.
     * (Attualmente il metodo restituisce solo una lista vuota: implementare la logica di conversione se necessario.)
     *
     * @param sites iterable di oggetti Site
     * @return lista di SiteDTO
     */
    public static List<SiteDTO> siteIterToList(Iterable<Site> sites) {
        List<SiteDTO> siteDTO = new ArrayList<>();
        return siteDTO;
    }

    /**
     * Restituisce la città in cui si trova il sito.
     *
     * @return città del sito
     */
    public String getCity() {
        return city;
    }

    /**
     * Imposta la città in cui si trova il sito.
     *
     * @param city città del sito
     */
    public void setCity(String city) {
        this.city = city;
    }
}
package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Enum.LeadState;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) utilizzato per rappresentare e trasferire i dati relativi a un Lead tra i vari livelli dell'applicazione.
 * <p>
 * Questo oggetto contiene le informazioni principali di un Lead, senza esporre direttamente l'entità del dominio.
 * </p>
 *
 * <ul>
 *   <li>{@code id}: identificativo univoco del Lead</li>
 *   <li>{@code dataAcquisto}: data di acquisto del Lead</li>
 *   <li>{@code status}: stato attuale del Lead (valore dell'enum {@link LeadState})</li>
 *   <li>{@code sconto}: percentuale di sconto applicata al Lead</li>
 *   <li>{@code userEmail}: email dell'utente associato al Lead</li>
 * </ul>
 */
public class LeadDTO {
    private Long id;
    private LocalDate dataAcquisto;
    private LeadState status;
    private int sconto;
    private String userEmail;

    /**
     * Restituisce l'identificativo univoco del Lead.
     *
     * @return id del Lead
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'identificativo univoco del Lead.
     *
     * @param id id del Lead
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce la data di acquisto del Lead.
     *
     * @return data di acquisto
     */
    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    /**
     * Imposta la data di acquisto del Lead.
     *
     * @param dataAcquisto data di acquisto
     */
    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    /**
     * Restituisce lo stato attuale del Lead.
     *
     * @return stato del Lead
     */
    public LeadState getStatus() {
        return status;
    }

    /**
     * Imposta lo stato attuale del Lead.
     *
     * @param status stato del Lead
     */
    public void setStatus(LeadState status) {
        this.status = status;
    }

    /**
     * Restituisce la percentuale di sconto applicata al Lead.
     *
     * @return percentuale di sconto
     */
    public int getSconto() {
        return sconto;
    }

    /**
     * Imposta la percentuale di sconto applicata al Lead.
     *
     * @param sconto percentuale di sconto
     */
    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    /**
     * Restituisce l'email dell'utente associato al Lead.
     *
     * @return email dell'utente
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Imposta l'email dell'utente associato al Lead.
     *
     * @param userEmail email dell'utente
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
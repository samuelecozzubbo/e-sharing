package com.e_sharing.E_sharing.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) utilizzato per rappresentare e trasferire i dati relativi a un account utente.
 * <p>
 * Questo oggetto contiene le informazioni principali di un account utente,
 * senza esporre direttamente l'entità del dominio.
 * </p>
 *
 * <ul>
 *   <li>{@code email}: indirizzo email dell'utente</li>
 *   <li>{@code nome}: nome dell'utente</li>
 *   <li>{@code cognome}: cognome dell'utente</li>
 *   <li>{@code username}: username scelto dall'utente</li>
 *   <li>{@code password}: password dell'utente</li>
 * </ul>
 */
public class UserAccountDTO {
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String password;

    /**
     * Costruttore vuoto per la creazione di un oggetto UserAccountDTO senza parametri.
     */
    public UserAccountDTO() {}

    /**
     * Costruttore per la creazione di un oggetto UserAccountDTO con parametri.
     *
     * @param email indirizzo email dell'utente
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param username username scelto dall'utente
     * @param password password dell'utente
     */
    public UserAccountDTO(String email, String nome, String cognome, String username, String password) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     * @return email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente.
     * @param email email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce il nome dell'utente.
     * @return nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     * @param nome nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     * @return cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     * @param cognome cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce lo username dell'utente.
     * @return username dell'utente
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta lo username dell'utente.
     * @param username username dell'utente
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Restituisce la password dell'utente.
     * @return password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     * @param password password dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce una rappresentazione testuale dei dati dell'account utente.
     * @return stringa con i dettagli dell'utente
     */
    @Override
    public String toString() {
        return "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'';
    }
}
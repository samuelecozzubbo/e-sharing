package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Entità che rappresenta un account utente nella piattaforma di e-sharing.
 * <p>
 * Un account utente contiene le informazioni anagrafiche e di autenticazione dell'utente,
 * oltre alla lista dei lead (prenotazioni/noleggi) associati.
 * </p>
 *
 * <ul>
 *   <li>{@code email}: indirizzo email dell'utente (chiave primaria)</li>
 *   <li>{@code nome}: nome dell'utente</li>
 *   <li>{@code cognome}: cognome dell'utente</li>
 *   <li>{@code username}: username scelto dall'utente</li>
 *   <li>{@code password}: password dell'utente</li>
 *   <li>{@code leads}: lista dei lead associati all'utente</li>
 * </ul>
 */
@Entity
@Table(name = "user_account")
public class UserAccount {
    // Attributi
    @Id
    @Email(message = "Email non valida")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 50)
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(max = 50)
    @Column(nullable = false)
    private String cognome;

    @NotBlank(message = "Lo username è obbligatorio")
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "La password è obbligatoria")
    @Column(nullable = false)
    private String password;

    // Relazione con Lead
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

    /**
     * Costruttore vuoto per JPA.
     */
    public UserAccount() {}

    /**
     * Costruttore completo.
     * @param email indirizzo email dell'utente
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param username username scelto dall'utente
     * @param password password dell'utente
     */
    public UserAccount(String email, String nome, String cognome, String username, String password) {
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
     * Restituisce la lista dei lead associati all'utente.
     * @return lista di lead
     */
    public List<Lead> getLeads() {
        return leads;
    }

    /**
     * Imposta la lista dei lead associati all'utente.
     * @param leads lista di lead
     */
    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }
}
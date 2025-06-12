package com.e_sharing.E_sharing.Model;

import com.e_sharing.E_sharing.Enum.LeadState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;

/**
 * Entità che rappresenta un Lead (prenotazione/noleggio) nel sistema.
 * <p>
 * Un Lead contiene informazioni relative alla data di acquisto, stato, eventuale sconto e utente associato.
 * </p>
 *
 * <ul>
 *   <li>{@code id}: identificativo univoco del lead</li>
 *   <li>{@code dataAcquisto}: data di acquisto o creazione del lead</li>
 *   <li>{@code status}: stato del lead (enum {@link LeadState})</li>
 *   <li>{@code sconto}: percentuale di sconto applicata</li>
 *   <li>{@code userAccount}: riferimento all'utente che ha effettuato il lead</li>
 * </ul>
 */
@Entity
@Table(name = "lead")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "La data di acquisto è obbligatoria")
    @Column(nullable = false)
    private LocalDate dataAcquisto;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Lo stato è obbligatorio")
    @Column(nullable = false)
    private LeadState status;

    @Column(nullable = false)
    private int sconto;

    // Relazione con Utente
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_account_email", referencedColumnName = "email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserAccount userAccount;

    /**
     * Costruttore vuoto per JPA.
     */
    public Lead() {}

    /**
     * Costruttore completo.
     *
     * @param dataAcquisto data di acquisto o creazione del lead
     * @param status stato del lead
     * @param sconto percentuale di sconto applicata
     * @param userAccount utente associato al lead
     */
    public Lead(LocalDate dataAcquisto, LeadState status, int sconto, UserAccount userAccount) {
        this.dataAcquisto = dataAcquisto;
        this.status = status;
        this.sconto = sconto;
        this.userAccount = userAccount;
    }

    /**
     * Restituisce l'identificativo del lead.
     * @return id del lead
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del lead.
     * @param id id del lead
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce la data di acquisto o creazione del lead.
     * @return data di acquisto
     */
    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    /**
     * Imposta la data di acquisto o creazione del lead.
     * @param dataAcquisto data di acquisto
     */
    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    /**
     * Restituisce lo stato del lead.
     * @return stato del lead
     */
    public LeadState getStatus() {
        return status;
    }

    /**
     * Imposta lo stato del lead.
     * @param status stato del lead
     */
    public void setStatus(LeadState status) {
        this.status = status;
    }

    /**
     * Restituisce la percentuale di sconto applicata.
     * @return sconto
     */
    public int getSconto() {
        return sconto;
    }

    /**
     * Imposta la percentuale di sconto applicata.
     * @param sconto percentuale di sconto
     */
    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    /**
     * Restituisce l'utente associato al lead.
     * @return utente che ha effettuato il lead
     */
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /**
     * Imposta l'utente associato al lead.
     * @param userAccount utente che ha effettuato il lead
     */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
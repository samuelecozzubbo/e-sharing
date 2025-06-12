package com.e_sharing.E_sharing.Enum;

/**
 * Enum per rappresentare lo stato di un veicolo.
 * <p>
 * I possibili stati sono:
 * <ul>
 *     <li>{@link #DISPONIBILE} – il veicolo è disponibile per il noleggio</li>
 *     <li>{@link #NOLEGGIATO} – il veicolo è attualmente noleggiato da un utente</li>
 *     <li>{@link #MANUTENZIONE} – il veicolo è in manutenzione e non può essere noleggiato</li>
 * </ul>
 */
public enum VehicleState {
    DISPONIBILE,
    NOLEGGIATO,
    MANUTENZIONE
}
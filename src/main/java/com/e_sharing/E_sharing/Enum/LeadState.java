package com.e_sharing.E_sharing.Enum;

/**
 * Enum per rappresentare lo stato di un lead (noleggio).
 * <p>
 * I possibili stati sono:
 * <ul>
 *     <li>{@link #RENTED} – il noleggio è attivo/evaso</li>
 *     <li>{@link #CANCELLED} – il noleggio è stato annullato</li>
 * </ul>
 */
public enum LeadState {
    RENTED,
    CANCELLED
}
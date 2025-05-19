package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Enum.LeadState;

import java.time.LocalDate;

public class LeadDTO {
    private Long id;
    private LocalDate dataAcquisto;
    private LeadState status;
    private int sconto;
    private String userEmail;

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public LeadState getStatus() {
        return status;
    }

    public void setStatus(LeadState status) {
        this.status = status;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

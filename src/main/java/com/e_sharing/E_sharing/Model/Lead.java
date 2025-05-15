package com.e_sharing.E_sharing.Model;
import com.e_sharing.E_sharing.Enum.LeadState;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;

@Entity
@Table(name = "lead")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataAcquisto;
    private LeadState status;
    private int sconto;

    //Relazioni con utente
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Utente_email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    //Relazioni con pivot

    //Costruttori
    public Lead() {}

    //Getter e Setter
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

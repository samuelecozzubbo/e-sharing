package com.e_sharing.E_sharing.Model;
import com.e_sharing.E_sharing.Enum.LeadState;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "user_account_email", referencedColumnName = "email")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserAccount userAccount;

    //Relazioni con LeadVehicle
    @OneToMany(mappedBy = "lead")
    private List<LeadVehicle> leadVehicles;

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
    public UserAccount getUser() {
        return userAccount;
    }
    public void setUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}

package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Email(message = "Email non valida", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String password;

    //Relazione con tabella lead
    // Relazione con la tabella pivot (Table)
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Lead> leads;

    //Costruttori
    public User() {}
    public User(String email, String nome, String cognome, String username, String password) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    //Getter e setter
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Lead> getLeads() {
        return leads;
    }
    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }
}

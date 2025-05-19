package com.e_sharing.E_sharing.DTO;

public class UserAccountDTO {
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String password;

    public UserAccountDTO() {}
    public UserAccountDTO(String email, String nome, String cognome, String username, String password) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    // Getters e Setters
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
}

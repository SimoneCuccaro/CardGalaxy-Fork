package model.admin;

import java.util.Date;

public class Admin
{
    private int idAdmin;
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String via;
    private int cap;
    private String citta;
    private String nazione;
    private Date dataNascita;

    public Admin(int idAdmin, String email, String nome, String cognome, String username, String password, String via, int cap, String citta, String nazione, Date dataNascita) {
        this.idAdmin = idAdmin;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.nazione = nazione;
        this.dataNascita = dataNascita;
    }

    public Admin()
    {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
}

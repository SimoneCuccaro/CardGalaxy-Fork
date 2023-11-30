package model.utente;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utente
{
    private int id;
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String pass;
    private String indirizzo;
    private String nazione;
    private String citta;
    private int cap;
    private String data_nascita;
    private boolean is_admin;


    public Utente(int id, String email, String nome, String cognome, String username, String pass, String indirizzo,String nazione,String citta,int cap,String data_nascita,boolean is_admin) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.pass = pass;
        this.indirizzo=indirizzo;
        this.nazione=nazione;
        this.citta=citta;
        this.cap=cap;
        this.data_nascita = data_nascita;
        this.is_admin=is_admin;
    }
    public  Utente(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes(StandardCharsets.UTF_8));
            this.pass= String.format("%040x", new
                    BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getNazione() {return nazione;}

    public void setNazione(String nazione) {this.nazione = nazione;}

    public String getCitta() {return citta;}

    public void setCitta(String citta) {this.citta = citta;}

    public int getCap() {return cap;}

    public void setCap(int cap) {this.cap = cap;}

    public String getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
    }

    public boolean is_admin() {
        return is_admin;
    }

    public void setAdmin(boolean admin) {
        is_admin = admin;
    }
}

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
    private String dataNascita;
    private boolean isAdmin;


    public Utente(int id, String email, String nome, String cognome, String username, String pass, String indirizzo,String dataNascita,boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.pass = pass;
        this.indirizzo=indirizzo;
        this.dataNascita = dataNascita;
        this.isAdmin=isAdmin;
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

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

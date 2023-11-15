package model.utente;

public class UtenteSession {
    private final int id;
    private final boolean isAdmin;
    private final String nome,cognome,username,email;


    public UtenteSession(Utente utente) {
        this.id = utente.getId();
        this.nome = utente.getNome();
        this.cognome = utente.getCognome();
        this.email=utente.getEmail();
        this.username=utente.getUsername();
        this.isAdmin = utente.isAdmin();
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail(){ return email;}
    public String getCognome() {
        return cognome;
    }

    public String getUsername() {
        return username;
    }
}

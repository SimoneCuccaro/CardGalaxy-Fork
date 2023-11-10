package model.utente;
import model.storage.TableQuery;
public class UtenteQuery extends TableQuery
{
    protected UtenteQuery(String table) {
        super(table);
    }

    public String tuttiUtenti() {
        return String.format("SELECT * FROM %s;",this.table);
    }

    public String singoloUtenteConPass(){
        return String.format("SELECT * FROM %s WHERE email = ? AND passwordhash = SHA1(?);", this.table);
    }


    public String singoloUtente(){
        return String.format("SELECT * FROM %s WHERE email = ?;", this.table);
    }

    public String inserisciUtente(){
        return String.format("INSERT INTO %s (nome, cognome, email, passwordhash, telefono, dataNascita, sesso, isAdmin, via, citta, cap, provincia, nazione) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);", this.table);
    }

    public String aggiornaUtente(){
        return String.format("UPDATE %s SET nome = ?, cognome = ?, email = ?, telefono = ?, dataNascita = ?, sesso = ?, isAdmin = ?, via = ?, citta = ?, cap = ?, provincia = ?, nazione = ? WHERE email = ?;", this.table);
    }

    public String eliminaUtente(){
        return String.format("DELETE FROM %s WHERE email=?;", this.table);
    }


}




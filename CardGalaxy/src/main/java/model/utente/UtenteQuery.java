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
        return String.format("SELECT * FROM %s WHERE username = ? AND pass = ?;", this.table);
    }

    public String singoloUtente(){
        return String.format("SELECT * FROM %s WHERE id = ?;", this.table);
    }

    public String inserisciUtente(){
        return String.format("INSERT INTO %s (email, nome, cognome, username, pass, indirizzo, nazione , citta, cap, data_nascita, is_admin) VALUES (?,?,?,?,?,?,?,?,?,?,?);", this.table);
    }

    public String aggiornaUtente(){
        return String.format("UPDATE %s SET email = ?,nome = ?, cognome = ?, username= ?, indirizzo = ?,nazione = ?,citta = ?,cap = ?, data_nascita = ?, is_admin = ?, pass = ? WHERE id = ?;", this.table);
    }

    public String eliminaUtente(){
        return String.format("DELETE FROM %s WHERE id=?;", this.table);
    }

    public String contaUtenti(){ return String.format("SELECT COUNT(*) as utentiTotali FROM %s",this.table);}


}




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
        return String.format("SELECT * FROM %s WHERE email = ? AND password = SHA1(?);", this.table);
    }

    public String singoloUtente(){
        return String.format("SELECT * FROM %s WHERE email = ?;", this.table);
    }

    public String inserisciUtente(){
        return String.format("INSERT INTO %s (nome, cognome, email,username, pass,indirizzo, dataNascita,isAdmin) VALUES (?,?,?,?,?,?,?,?);", this.table);
    }

    public String aggiornaUtente(){
        return String.format("UPDATE %s SET nome = ?, cognome = ?, email = ?, username= ?, indrizzo = ?, dataNascita = ?, isAdmin = ? WHERE email = ?;", this.table);
    }

    public String eliminaUtente(){
        return String.format("DELETE FROM %s WHERE email=?;", this.table);
    }


}




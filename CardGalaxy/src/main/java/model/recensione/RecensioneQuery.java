package model.recensione;


import model.storage.TableQuery;

public class RecensioneQuery extends TableQuery {

    protected RecensioneQuery(String table) {
        super(table);
    }

    public String retriveRecensioni() { return String.format("SELECT * FROM %s ;",this.table);}

    public String retrieveRecensioniByProdotto() { return String.format("SELECT * FROM %s WHERE id_prodotto=?;",this.table);}

    public String inserisciRecensione() { return String.format("INSERT INTO %s (id_utente , id_prodotto , datarecensione , testo) VALUES (?, ?, ?, ?);", this.table);}

    public String aggiornaRecensione() { return String.format("UPDATE %s SET testo = ?, datarecensione = ?  WHERE id_utente = ? AND id_prodotto = ?;", this.table);}

    public String rimuoviRecensione() {return String.format("DELETE FROM %s WHERE id_utente = ? AND id_prodotto = ?;",this.table);}

}


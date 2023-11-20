package model.recensione;


import model.storage.TableQuery;

public class RecensioneQuery extends TableQuery {

    protected RecensioneQuery(String table) {
        super(table);
    }

    public String retriveRecensioni() { return String.format("SELECT * FROM %s ;",this.table);}

    public String retrieveRecensioniByProdotto() { return String.format("SELECT * FROM %s WHERE codiceprodotto=?;",this.table);}

    public String inserisciRecensione() { return String.format("INSERT INTO %s (idutente , codiceprodotto , datarecensione , testo) VALUES (?, ?, ?, ?);", this.table);}

    public String aggiornaRecensione() { return String.format("UPDATE %s SET testo = ?, data = ?,  WHERE idutente = ? AND codiceprodotto = ?;", this.table);}

    public String rimuoviRecensione() {return String.format("DELETE FROM %s WHERE idutente = ? AND codiceprodotto = ?;",this.table);}

}


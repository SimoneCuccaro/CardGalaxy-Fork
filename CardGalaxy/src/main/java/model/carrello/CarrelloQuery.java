package model.carrello;

import model.storage.TableQuery;
public class CarrelloQuery extends TableQuery{
    protected CarrelloQuery(String table){super(table);}
    public String retrieveCarrelloByUtente() { return String.format("SELECT * FROM %s WHERE id_utente = ?;",this.table);}
    public String deleteCarrelloUtente(){return String.format("DELETE FROM %s WHERE id_utente = ?;",this.table);}
}

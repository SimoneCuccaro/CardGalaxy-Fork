package model.carrello;

import model.storage.TableQuery;
public class CarrelloQuery extends TableQuery{
    protected CarrelloQuery(String table){super(table);}
    public String retrieveAllCarrelli() { return String.format("SELECT * FROM %s;",this.table);}
    public String retrieveCarrelloByUtente() { return String.format("SELECT * FROM %s WHERE idutente = ?;",this.table);}
    public String deleteCarrelloUtente(){return String.format("DELETE FROM %s WHERE idutente = ?;",this.table);}
}

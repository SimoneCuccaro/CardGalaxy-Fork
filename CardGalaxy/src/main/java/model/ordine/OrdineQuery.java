package model.ordine;

import model.storage.TableQuery;
public class OrdineQuery extends TableQuery
{
    protected OrdineQuery(String table) {
        super(table);
    }

    public String retrieveAllOrdini() { return String.format("SELECT * FROM %s;",this.table);}
    public String retrieveOrderByUser() { return String.format("SELECT * FROM %s WHERE id_utente = ?;",this.table);}

    public String retrieveOrdineById() { return String.format("SELECT * FROM %s WHERE id = ?;",this.table);}

    public String countAllOrdini() {return String.format("SELECT count(*) AS ordiniTotali FROM %s;",this.table);}

    public String creaOrdine() { return String.format("INSERT INTO %s (prezzo_totale, data_acquisto, id_utente) VALUES (?, ?, ?);",this.table);}

    public String lastOrder() { return String.format("SELECT max(id) as lastorder from %s;",this.table);}

    public String retriveProdotti(){ return String.format("SELECT * FROM prodotto,contenuto WHERE prodotto.id_prodotto=contenuto.id_prodotto AND contenuto.id_ordine = ?;");}

    public String getGuadagno(){return String.format("SELECT prezzo_totale FROM %s;",this.table);}

    public String updateContenuto(){return String.format("UPDATE contenuto SET quantita=? WHERE id_ordine=? AND id_prodotto=?;");}

    public String updateOrder(){return String.format("UPDATE %s SET prezzo_totale=?",this.table);}

    public String removeOrder(){return String.format("DELETE FROM %s WHERE id=?",this.table);}

    public String saveContenuto(){return String.format("INSERT INTO contenuto (id_ordine, id_prodotto, quantita) VALUES (?, ?, ?)");}
}

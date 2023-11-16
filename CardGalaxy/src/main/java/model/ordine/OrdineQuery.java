package model.ordine;

import model.storage.TableQuery;
public class OrdineQuery extends TableQuery
{
    protected OrdineQuery(String table) {
        super(table);
    }

    public String retrieveAllOrdini() { return String.format("SELECT * FROM %s;",this.table);}

    public String retrieveOrdineById() { return String.format("SELECT * FROM %s WHERE codice = ?;",this.table);}

    public String countAllOrdini() {return String.format("SELECT count(*) AS ordiniTotali FROM %s;",this.table);}

    public String creaOrdine() { return String.format("INSERT INTO %s (prezzototale, datacquisto, idutente) VALUES (?, ?, ?);",this.table);}

    public String lastOrder() { return String.format("SELECT max(codice) as lastorder from %s;",this.table);}

    public String retriveProdotti(){ return String.format("SELECT * FROM prodotto,contenuto WHERE prodotto.id=contenuto.idprodotto AND contenuto.codiceordine = ?;");}
}

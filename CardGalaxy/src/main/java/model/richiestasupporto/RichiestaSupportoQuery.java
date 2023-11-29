package model.richiestasupporto;


import model.storage.TableQuery;

public class RichiestaSupportoQuery extends TableQuery {

    protected RichiestaSupportoQuery(String table) {
        super(table);
    }

    public String retriveRichiesteSupporto() { return String.format("SELECT * FROM %s ;",this.table);}

    public String inserisciRichiestaSupporto() { return String.format("INSERT INTO %s (richiesta , oggetto_richiesta, id_utente) VALUES (?, ?, ?);", this.table);}


}
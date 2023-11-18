package model.rispostasupporto;

import model.storage.TableQuery;

public class RispostaSupportoQuery extends TableQuery {

    protected RispostaSupportoQuery(String table) {
        super(table);
    }

    public String retriveRisposteSupporto() { return String.format("SELECT * FROM %s ;",this.table);}

    public String inserisciRispostaSupporto() { return String.format("INSERT INTO %s (risposta, idrichiestasupporto) VALUES (?, ?);", this.table);}


}

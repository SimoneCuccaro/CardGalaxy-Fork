package model.richiestasupporto;


import model.storage.TableQuery;

public class RichiestaSupportoQuery extends TableQuery {

    protected RichiestaSupportoQuery(String table) {
        super(table);
    }

    public String retriveRichiesteSupporto() { return String.format("SELECT * FROM %s a\n" +
            "where not exists (select id_richiesta_supporto from rispostasupporto b where b.id_richiesta_supporto=a.id_richiesta)",this.table);}
    public String retriveRichiesteSupportoById() { return String.format("SELECT * FROM %s WHERE id_richiesta=?;",this.table);}
    public String inserisciRichiestaSupporto() { return String.format("INSERT INTO %s (richiesta , oggetto_richiesta, id_utente) VALUES (?, ?, ?);", this.table);}


}
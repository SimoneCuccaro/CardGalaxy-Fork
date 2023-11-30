package model.richiestasupporto;

import model.utente.Utente;
public class RichiestaSupporto
{
    private int id_richiesta;
    private String richiesta;
    private String oggetto_richiesta;
    private int id_utente;

    public RichiestaSupporto(int id_richiesta, String richiesta, String oggetto_richiesta, int id_utente) {
        this.id_richiesta = id_richiesta;
        this.richiesta = richiesta;
        this.oggetto_richiesta = oggetto_richiesta;
        this.id_utente = id_utente;
    }

    public RichiestaSupporto(){}

    public int getId_richiesta() {return id_richiesta;}

    public void setId_richiesta(int id_richiesta) {this.id_richiesta = id_richiesta;}

    public String getRichiesta() {return richiesta;}

    public void setRichiesta(String richiesta) {this.richiesta = richiesta;}

    public String getOggetto_richiesta() {return oggetto_richiesta;}

    public void setOggetto_richiesta(String oggetto_richiesta) {this.oggetto_richiesta = oggetto_richiesta;}

    public int getId_utente() {return id_utente;}

    public void setId_utente(int id_utente) {this.id_utente = id_utente;}
}

package model.richiestasupporto;

import model.utente.Utente;
public class RichiestaSupporto
{
    private int id;
    private String richiesta;
    private String oggettorichiesta;
    private int idutente;

    public RichiestaSupporto(int id, String richiesta, String oggettorichiesta, int idutente) {
        this.id = id;
        this.richiesta = richiesta;
        this.oggettorichiesta = oggettorichiesta;
        this.idutente = idutente;
    }

    public RichiestaSupporto() {}

    public int getId() {
        return id;
    }

    public void setId(int idSupporto) {
        this.id = id;
    }

    public String getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(String richiesta) {
        this.richiesta = richiesta;
    }

    public String getOggettorichiesta() {
        return oggettorichiesta;
    }

    public void setOggettorichiesta(String oggettorichiesta) {
        this.oggettorichiesta = oggettorichiesta;
    }

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }
}

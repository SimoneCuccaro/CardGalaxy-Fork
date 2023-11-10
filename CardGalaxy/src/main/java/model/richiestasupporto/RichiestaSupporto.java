package model.richiestasupporto;

import model.utente.Utente;
public class RichiestaSupporto
{
    private int idSupporto;
    private String richiesta;
    private String oggettoRichiesta;
    private Utente utente;

    public RichiestaSupporto(int idSupporto, String richiesta, String oggettoRichiesta, Utente utente) {
        this.idSupporto = idSupporto;
        this.richiesta = richiesta;
        this.oggettoRichiesta = oggettoRichiesta;
        this.utente = utente;
    }

    public RichiestaSupporto() {}

    public int getIdSupporto() {
        return idSupporto;
    }

    public void setIdSupporto(int idSupporto) {
        this.idSupporto = idSupporto;
    }

    public String getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(String richiesta) {
        this.richiesta = richiesta;
    }

    public String getOggettoRichiesta() {
        return oggettoRichiesta;
    }

    public void setOggettoRichiesta(String oggettoRichiesta) {
        this.oggettoRichiesta = oggettoRichiesta;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}

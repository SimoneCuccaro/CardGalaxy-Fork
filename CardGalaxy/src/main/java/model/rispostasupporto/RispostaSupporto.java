package model.rispostasupporto;

import model.richiestasupporto.RichiestaSupporto;
import model.utente.Utente;

public class RispostaSupporto
{
    private int id;
    private String risposta;
    private int richiestasupporto;

    public RispostaSupporto(int id, String risposta, int richiestasupporto)
    {
        this.id = id;
        this.risposta = risposta;
        this.richiestasupporto = richiestasupporto;
    }

    public RispostaSupporto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public int getRichiestasupporto() {
        return richiestasupporto;
    }

    public void setRichiestasupporto(int richiestasupporto) {
        this.richiestasupporto = richiestasupporto;
    }
}

package model.rispostasupporto;

import model.richiestasupporto.RichiestaSupporto;
import model.admin.Admin;

public class RispostaSupporto
{
    private int idRispostaSupporto;
    private String risposta;
    private Admin admin;
    private RichiestaSupporto richiestaSupporto;

    public RispostaSupporto(int idRispostaSupporto, String risposta, Admin admin, RichiestaSupporto richiestaSupporto)
    {
        this.idRispostaSupporto = idRispostaSupporto;
        this.risposta = risposta;
        this.admin = admin;
        this.richiestaSupporto = richiestaSupporto;
    }

    public RispostaSupporto() {}

    public int getIdRispostaSupporto() {
        return idRispostaSupporto;
    }

    public void setIdRispostaSupporto(int idRispostaSupporto) {
        this.idRispostaSupporto = idRispostaSupporto;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public RichiestaSupporto getRichiestaSupporto() {
        return richiestaSupporto;
    }

    public void setRichiestaSupporto(RichiestaSupporto richiestaSupporto) {
        this.richiestaSupporto = richiestaSupporto;
    }
}

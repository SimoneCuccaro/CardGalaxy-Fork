package model.rispostasupporto;

import model.richiestasupporto.RichiestaSupporto;
import model.utente.Utente;

public class RispostaSupporto
{
    private int id_utente;
    private String risposta;
    private int id_richiesta_supporto;

    public RispostaSupporto(int id_utente, String risposta, int id_richiesta_supporto)
    {
        this.id_utente = id_utente;
        this.risposta = risposta;
        this.id_richiesta_supporto =id_richiesta_supporto;
    }

    public RispostaSupporto() {}

    public int getId_utente() {return id_utente;}

    public void setId_utente(int id_utente) {this.id_utente = id_utente;}

    public String getRisposta() {return risposta;}

    public void setRisposta(String risposta) {this.risposta = risposta;}

    public int getId_richiesta_supporto() {return id_richiesta_supporto;}

    public void setId_richiesta_supporto(int id_richiesta_supporto) {this.id_richiesta_supporto = id_richiesta_supporto;}
}

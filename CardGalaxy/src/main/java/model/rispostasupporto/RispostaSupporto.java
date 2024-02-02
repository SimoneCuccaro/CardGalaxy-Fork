package model.rispostasupporto;

import model.richiestasupporto.RichiestaSupporto;
import model.utente.Utente;

/** Un oggetto <code>RispostaSupporto</code> rappresenta una risposta a una richiesta di supporto effettuata da un utente.
 * E' costituito dall' id dell' account utente che ha effettuato la richiesta, una stringa contenente la risposta e l' id
 * della richiesta a cui si risponde
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RispostaSupporto
{
    private int id_utente;
    private String risposta;
    private int id_richiesta_supporto;

    /**Costruttore dell' oggetto RispostaSupporto
     *
     * @param id_utente id dell' account utente che ha effettuato la richiesta da rispondere
     * @param risposta testo della risposta
     * @param id_richiesta_supporto id della richiesta da rispondere
     */
    public RispostaSupporto(int id_utente, String risposta, int id_richiesta_supporto)
    {
        this.id_utente = id_utente;
        this.risposta = risposta;
        this.id_richiesta_supporto =id_richiesta_supporto;
    }

    /**Costruttore vuoto dell' oggetto RispostaSupporto
     *
     */
    public RispostaSupporto() {}

    /**Il metodo <code>getId_utente</code> consente di ottenere
     * l' id dell' account utente che ha effettuato la richiesta di supporto a cui fa riferimento la risposta
     *
     * @return id dell' account utente che ha effettuato la richiesta di supporto a cui fa riferimento la risposta
     */
    public int getId_utente() {return id_utente;}

    /**Il metodo <code>setId_utente</code> consente di impostare
     * l' id dell' account utente che ha effettuato la richiesta di supporto a cui fa riferimento la risposta
     *
     * @param id_utente id dell' account utente che ha effettuato la richiesta di supporto a cui fa riferimento la risposta
     */
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}

    /**Il metodo <code>getRisposta</code> consente di ottenere
     * il testo della risposta
     *
     * @return stringa contenente il testo della risposta
     */
    public String getRisposta() {return risposta;}

    /**Il metodo <code>setRisposta</code> consente di impostare
     * il testo della risposta
     *
     * @param risposta testo da impostare per la risposta
     */
    public void setRisposta(String risposta) {this.risposta = risposta;}

    /**Il metodo <code>getId_richiesta_supporto</code> consente di ottenere
     * l' id della richiesta di supporto a cui fa riferimento la risposta
     *
     * @return id della richiesta di supporto a cui fa riferimento la risposta
     */
    public int getId_richiesta_supporto() {return id_richiesta_supporto;}

    /**Il metodo <code>setId_richiesta_supporto</code> consente di impostare
     * l' id della richiesta di supporto a cui fa riferimento la risposta
     *
     * @param id_richiesta_supporto id della richiesta di supporto a cui fa riferimento la risposta
     */
    public void setId_richiesta_supporto(int id_richiesta_supporto) {this.id_richiesta_supporto = id_richiesta_supporto;}
}

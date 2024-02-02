package model.richiestasupporto;

import model.utente.Utente;

/** Un oggetto <code>RichiestaSupporto</code> rappresenta una richiesta di supporto effettuata da un utente.
 * E' costituito dall' id della richiesta, da una stringa che rappresenta la richiesta, una stringa oggetto_richiesta
 * che ne fornisce una breve descrizione e dall' id dell' account utente che la effettua
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RichiestaSupporto
{
    private int id_richiesta;
    private String richiesta;
    private String oggetto_richiesta;
    private int id_utente;

    /**Costruttore dell' oggetto RichiestaSupporto
     *
     * @param id_richiesta id dell' oggetto RichiestaSupporto
     * @param richiesta stringa contenente la richiesta
     * @param oggetto_richiesta stringa contenente una breve richiesta di supporto
     * @param id_utente id dell' account utente che effettua la richiesta
     */
    public RichiestaSupporto(int id_richiesta, String richiesta, String oggetto_richiesta, int id_utente) {
        this.id_richiesta = id_richiesta;
        this.richiesta = richiesta;
        this.oggetto_richiesta = oggetto_richiesta;
        this.id_utente = id_utente;
    }

    /**Costruttore nullo dell' oggetto RichiestaSupporto
     *
     */
    public RichiestaSupporto(){}

    /**Il metodo <code>getId_richiesta</code> consente di ottenere
     * l' id della richiesta di supporto
     *
     * @return id della richiesta di supporto
     */
    public int getId_richiesta() {return id_richiesta;}

    /**Il metodo <code>setId_richiesta</code> consente di impostare
     * l' id della richiesta di supporto
     *
     * @param id_richiesta id della richiesta di supporto
     */
    public void setId_richiesta(int id_richiesta) {this.id_richiesta = id_richiesta;}

    /**Il metodo <code>getRichiesta</code> consente di ottenere
     * il testo della richiesta di supporto
     *
     * @return stringa contenente il testo della richiesta di supporto
     */
    public String getRichiesta() {return richiesta;}

    /**Il metodo <code>setRichiesta</code> consente di impostare
     * il testo della richiesta di supporto
     *
     * @param richiesta testo da impostare per la richiesta di supporto
     */
    public void setRichiesta(String richiesta) {this.richiesta = richiesta;}

    /**Il metodo <code>getOggetto_richiesta</code> consente di ottenere
     * il testo della sezione oggetto della richiesta di supporto
     *
     * @return stringa contenente il testo della sezione oggetto della richiesta di supporto
     */
    public String getOggetto_richiesta() {return oggetto_richiesta;}

    /**Il metodo <code>setOggetto_richiesta</code> consente di impostare
     * il testo della sezione oggetto della richiesta di supporto
     *
     * @param oggetto_richiesta testo da impostare per la sezione oggetto della richiesta di supporto
     */
    public void setOggetto_richiesta(String oggetto_richiesta) {this.oggetto_richiesta = oggetto_richiesta;}

    /**Il metodo <code>getId_utente</code> consente di ottenere
     * l' id dell' account utente che ha effettuato la richiesta di supporto
     *
     * @return id dell' account utente che ha effettuato la richiesta di supporto
     */
    public int getId_utente() {return id_utente;}

    /**Il metodo <code>setId_utente</code> consente di impostare
     * l' id dell' account utente che ha effettuato la richiesta di supporto
     *
     * @param id_utente id dell' account utente che ha effettuato la richiesta di supporto
     */
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}
}

package model.rispostasupporto;

import model.storage.TableQuery;

/** La classe <code>RispostaSupportoQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione delle risposte alle richieste di supporto
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RispostaSupportoQuery extends TableQuery {

    /** Costruttore dell' oggetto RispostaSupportoQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected RispostaSupportoQuery(String table) {
        super(table);
    }

    /** Il metodo <code>retriveRisposteSupporto</code> contiene la query usata per
     * ottenere tutti gli oggetti RispostaSupporto salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String retriveRisposteSupporto() { return String.format("SELECT * FROM %s ;",this.table);}

    /** Il metodo <code>inserisciRispostaSupporto</code> contiene la query usata per
     * inserire un oggetto RispostaSupporto all' interno del database
     *
     * @return  stringa contenente la query
     */
    public String inserisciRispostaSupporto() { return String.format("INSERT INTO %s (id_utente, risposta, id_richiesta_supporto) VALUES (?, ?, ?);", this.table);}


}

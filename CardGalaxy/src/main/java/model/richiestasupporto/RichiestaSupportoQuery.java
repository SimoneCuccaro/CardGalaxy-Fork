package model.richiestasupporto;


import model.storage.TableQuery;

/** La classe <code>RichiestaSupportoQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione delle risposte alle richieste di supporto
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RichiestaSupportoQuery extends TableQuery {

    /** Costruttore dell' oggetto RichiestaSupportoQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected RichiestaSupportoQuery(String table) {
        super(table);
    }

    /** Il metodo <code>retriveRichiesteSupporto</code> contiene la query usata per
     * ottenere tutti gli oggetti RichiestaSupporto salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String retriveRichiesteSupporto() { return String.format("SELECT * FROM %s a\n" +
            "where not exists (select id_richiesta_supporto from rispostasupporto b where b.id_richiesta_supporto=a.id_richiesta)",this.table);}

    /** Il metodo <code>retriveRichiesteSupportoById</code> contiene la query usata per
     * ottenere un oggetto RichiestaSupporto salvato nel database con uno specifico id
     *
     * @return  stringa contenente la query
     */
    public String retriveRichiesteSupportoById() { return String.format("SELECT * FROM %s WHERE id_richiesta=?;",this.table);}

    /** Il metodo <code>retriveRichiestaSupporto</code> contiene la query usata per
     * inserire un oggetto RichiestaSupporto nel database
     *
     * @return  stringa contenente la query
     */
    public String inserisciRichiestaSupporto() { return String.format("INSERT INTO %s (richiesta , oggetto_richiesta, id_utente) VALUES (?, ?, ?);", this.table);}


}
package model.recensione;


import model.storage.TableQuery;

/** La classe <code>RecensioneQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione delle recensioni
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RecensioneQuery extends TableQuery {

    /** Costruttore dell' oggetto RecensioneQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected RecensioneQuery(String table) {
        super(table);
    }

    /** Il metodo <code>retriveRecensioni</code> contiene la query usata per
     * ottenere tutti gli oggetti Recensione salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String retriveRecensioni() { return String.format("SELECT * FROM %s ;",this.table);}

    /** Il metodo <code>retrieveRecensioniByProdotto</code> contiene la query usata per
     * ottenere tutti gli oggetti Recensione inerenti ad uno specifico oggetto GiftCard salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String retrieveRecensioniByProdotto() { return String.format("SELECT * FROM %s WHERE id_prodotto=?;",this.table);}

    /** Il metodo <code>inserisciRecensione</code> contiene la query usata per
     * inserire un oggetto Recensione nel database
     *
     * @return  stringa contenente la query
     */
    public String inserisciRecensione() { return String.format("INSERT INTO %s (id_utente , id_prodotto , datarecensione , testo) VALUES (?, ?, ?, ?);", this.table);}

    /** Il metodo <code>aggiornaRecensione</code> contiene la query usata per
     * aggiornare un oggetto Recensione nel database
     *
     * @return  stringa contenente la query
     */
    public String aggiornaRecensione() { return String.format("UPDATE %s SET testo = ?, datarecensione = ?  WHERE id_utente = ? AND id_prodotto = ?;", this.table);}

    /** Il metodo <code>rimuoviRecensione</code> contiene la query usata per
     * rimuovere un oggetto Recensione dal database
     *
     * @return  stringa contenente la query
     */
    public String rimuoviRecensione() {return String.format("DELETE FROM %s WHERE id_utente = ? AND id_prodotto = ?;",this.table);}

}


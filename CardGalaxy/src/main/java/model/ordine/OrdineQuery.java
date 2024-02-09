package model.ordine;

import model.storage.TableQuery;

/** La classe <code>OrdineQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione degli ordini
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class OrdineQuery extends TableQuery
{
    /** Costruttore dell' oggetto OrdineQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected OrdineQuery(String table) {
        super(table);
    }

    /** Il metodo <code>retriveAllOrdini</code> contiene la query usata per
     * ottenere tutti gli ordini presenti nel database
     *
     * @return  stringa contenente la query
     */
    public String retrieveAllOrdini() { return String.format("SELECT * FROM %s;",this.table);}

    /** Il metodo <code>retriveOrderByUser</code> contiene la query usata per
     * ottenere tutti gli ordini di un determinato utente
     *
     * @return  stringa contenente la query
     */
    public String retrieveOrderByUser() { return String.format("SELECT * FROM %s WHERE id_utente = ?;",this.table);}

    /** Il metodo <code>retriveOrdineById</code> contiene la query usata per
     * ottenere un ordine partendo dal suo id
     *
     * @return  stringa contenente la query
     */
    public String retrieveOrdineById() { return String.format("SELECT * FROM %s WHERE id = ?;",this.table);}

    /** Il metodo <code>countAllOrdini</code> contiene la query usata per
     * contare tutti gli ordini presenti nel database
     *
     * @return  stringa contenente la query
     */
    public String countAllOrdini() {return String.format("SELECT count(*) AS ordiniTotali FROM %s;",this.table);}

    /** Il metodo <code>creaOrdine</code> contiene la query usata per
     * creare e salvare un nuovo ordine nel database
     *
     * @return  stringa contenente la query
     */
    public String creaOrdine() { return String.format("INSERT INTO %s (prezzo_totale, data_acquisto, id_utente) VALUES (?, ?, ?);",this.table);}

    /** Il metodo <code>lastOrder</code> contiene la query usata per
     * ottenere l' id dell' ultimo ordine salvato nel database
     *
     * @return  stringa contenente la query
     */
    public String lastOrder() { return String.format("SELECT max(id) as lastorder from %s;",this.table);}

    /** Il metodo <code>retriveProdotti</code> contiene la query usata per
     * ottenere i prodotti di un determinato ordine
     *
     * @return  stringa contenente la query
     */
    public String retriveProdotti(){ return String.format("SELECT * FROM prodotto,contenuto WHERE prodotto.id_prodotto=contenuto.id_prodotto AND contenuto.id_ordine = ?;");}

    /** Il metodo <code>getGuadagno</code> contiene la query usata per
     * ottenere la somma totale del prezzo degli ordini salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String getGuadagno(){return String.format("SELECT prezzo_totale FROM %s;",this.table);}

    /** Il metodo <code>updateContenuto</code> contiene la query usata per
     * aggiornare la quantità di un prodotto in un determinato ordine
     *
     * @return  stringa contenente la query
     */
    public String updateContenuto(){return String.format("UPDATE contenuto SET quantita=? WHERE id_ordine=? AND id_prodotto=?;");}

    /** Il metodo <code>updateOrder</code> contiene la query usata per
     * aggiornare il prezzo di un ordine salvato nel database
     *
     * @return  stringa contenente la query
     */
    public String updateOrder(){return String.format("UPDATE %s SET prezzo_totale=?",this.table);}

    /** Il metodo <code>removeOrder</code> contiene la query usata per
     * rimuovere un ordine dal database
     *
     * @return  stringa contenente la query
     */
    public String removeOrder(){return String.format("DELETE FROM %s WHERE id=?",this.table);}

    /** Il metodo <code>saveContenuto</code> contiene la query usata per
     * mappare i prodotti e la rispettiva quantità all' interno di un ordine
     * nella tabella Contenuto
     *
     * @return  stringa contenente la query
     */
    public String saveContenuto(){return String.format("INSERT INTO contenuto (id_ordine, id_prodotto, quantita) VALUES (?, ?, ?)");}
}

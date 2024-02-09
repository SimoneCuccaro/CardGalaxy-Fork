package model.carrello;

import model.storage.TableQuery;

/** La classe <code>CarrelloQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione del carrello
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class CarrelloQuery extends TableQuery{
    /** Costruttore dell' oggetto CarrelloQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected CarrelloQuery(String table){super(table);}

    /** Il metodo <code>retriveCarrelloByUtente</code> contiene la query usata per
     * ottenere i prodotti del carrello di un utente
     *
     * @return  stringa contenente la query
     */
    public String retrieveCarrelloByUtente() { return String.format("SELECT * FROM %s WHERE id_utente = ?;",this.table);}

    /** Il metodo <code>deleteCarrelloUtente</code> contiene la query usata per
     * rimuovere il carrello di un utente
     *
     * @return  stringa contenente la query
     */
    public String deleteCarrelloUtente(){return String.format("DELETE FROM %s WHERE id_utente = ?;",this.table);}

    /** Il metodo <code>addToCart</code> contiene la query usata per
     * aggiungere un prodotto al carrello di un utente
     *
     * @return  stringa contenente la query
     */
    public String addToCart(){return String.format("INSERT INTO %s (id_utente, id_prodotto, quantita) VALUES (?,?,?);",this.table);}

    /** Il metodo <code>removeFromCart</code> contiene la query usata per
     * rimuovere un prodotto dal carrello di un utente
     *
     * @return  stringa contenente la query
     */
    public String removeFromCart(){return String.format("DELETE FROM %s WHERE id_utente = ? AND id_prodotto =?",this.table);}
}

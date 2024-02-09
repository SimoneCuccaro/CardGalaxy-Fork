package model.prodotto;


import model.storage.TableQuery;

/** La classe <code>GiftCardQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione delle giftcard
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class GiftCardQuery extends TableQuery {

    /** Costruttore dell' oggetto GiftCardQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected GiftCardQuery(String table) {
        super(table);
    }

    /** Il metodo <code>tutteGiftCard</code> contiene la query usata per
     * ottenere tutte gli oggetti GiftCard presenti nel databse
     *
     * @return  stringa contenente la query
     */
    public String tutteGiftCard() {
        return String.format("SELECT * FROM %s;", this.table);
    }

    /** Il metodo <code>retrieveGiftCardByID</code> contiene la query usata per
     * ottenere un oggetto GiftCard presente nel database tramite il suo id
     *
     * @return  stringa contenente la query
     */
    public String retrieveGiftCardByID() {
        return String.format("SELECT * FROM %s WHERE id_prodotto=?;", this.table);
    }

    /** Il metodo <code>inserisciGiftCard</code> contiene la query usata per
     * inserire un oggetto GiftCard nel database
     *
     * @return  stringa contenente la query
     */
    public String inserisciGiftCard() {
        return String.format("INSERT INTO %s (nome , piattaforma , descrizione , prezzo, foto, isAvailable) VALUES (?, ?, ?, ?, ?, ?);", this.table);
    }

    /** Il metodo <code>aggiornaGiftCard</code> contiene la query usata per
     * aggiornare un oggetto GiftCard nel database
     *
     * @return  stringa contenente la query
     */
    public String aggiornaGiftCard() {
        return String.format("UPDATE %s SET nome = ?, piattaforma = ?, descrizione = ?, prezzo = ?, foto = ?, isAvailable = ? WHERE id_prodotto = ?;", this.table);
    }

    /** Il metodo <code>rimuoviGiftCard</code> contiene la query usata per
     * impostare la visibilità di un oggetto GiftCard a false
     *
     * @return  stringa contenente la query
     */
    public String rimuoviGiftCard() {
        return String.format("UPDATE %s SET isAvailable = false WHERE id_prodotto = ?;", this.table);
    }

    /** Il metodo <code>countAllGiftCard</code> contiene la query usata per
     * contare tutti gli oggetti GiftCard presenti nel database
     *
     * @return  stringa contenente la query
     */
    public String countAllGiftCard() {
        return String.format("SELECT count(*) AS giftCardTotali FROM %s WHERE isAvailable=1;", this.table);
    }


}


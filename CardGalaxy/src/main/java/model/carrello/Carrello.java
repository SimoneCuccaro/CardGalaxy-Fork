package model.carrello;

import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.ArrayList;

/** Un oggetto <code>Carrello</code> rappresenta un prodotto all' interno del carrello,
 * contiene l' id dell'utente che l' ha aggiunto, l' id del prodotto aggiunto e la sua quantità
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Carrello
{
    private int id_utente;
    private int id_prodotto;
    private int quantita;

    /** Costruttore dell' oggetto Carrello
     *
     * @param id_utente     id dell'utente che ha aggiunto il prodotto al carrello
     * @param id_prodotto   id del prodotto che l'utente ha aggiunto al carrello
     * @param quantita      quantità del prodotto aggiunto al carrello
     */
    public Carrello(int id_utente,int id_prodotto,int quantita)
    {
        this.id_utente = id_utente;
        this.id_prodotto = id_prodotto;
        this.quantita=quantita;
    }

    /** Costruttore nullo dell' oggetto carrello */
    public Carrello() {}

    /**Il metodo <code>getId_utente</code> ritorna l' id dell' utente
     * che ha aggiunto il prodotto al carrello
     *
     * @return id dell'utente che ha aggiunto il prodotto al carrello
     */
    public int getId_utente() {return id_utente;}

    /**Il metodo <code>setId_utente</code> consente di impostare l' id dell' utente
     * che ha aggiunto il prodotto al carrello
     *
     * @param id_utente  id dell'utente che ha aggiunto il prodotto al carrello (l' id deve essere presente nel database)
     */
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}

    /**Il metodo <code>getId_prodotto</code> ritorna l 'id del prodotto
     * che un utente ha aggiunto al proprio carrello
     *
     * @return id di un determinato prodotto presente nel carrello di un utente
     */
    public int getId_prodotto() {return id_prodotto;}

    /**Il metodo <code>setId_prodotto</code> consente di impostare l' id di un prodotto
     * aggiunto al carrello da un utente
     *
     * @param id_prodotto id del prodotto che l' utente ha aggiunto al carrello (l' id deve essere presente nel database)
     */
    public void setId_prodotto(int id_prodotto) {this.id_prodotto = id_prodotto;}

    /**Il metodo <code>getQuantita</code> consente di ottenere la quantità di un prodotto
     * presente nel carrello di un utente
     *
     * @return  un intero che indica la quantità di prodotto presente nel carrello di un utente
     */
    public int getQuantita() {return quantita;}

    /**Il metodo <code>setQuantita</code> consente di impostare la quantità di un prodotto presente
     * nel carrello di un utente
     *
     * @param quantita  intero che rappresenta la quantità di prodotto presente nel carrello di un utente
     */
    public void setQuantita(int quantita) {this.quantita = quantita;}
}

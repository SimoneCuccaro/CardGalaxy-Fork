package model.ordine;
import model.prodotto.GiftCard;


import java.util.Hashtable;

/** Un oggetto <code>Ordine</code> rappresenta un ordine effettuato da un utente,
 * contiene l' id univoco dell'ordine, il suo prezzo totale, la data in cui è stato effettuato,
 * l' id dell' utente che lo ha effettuato e una hashtable di tipo GiftCard,Integer contenente
 * tutti i prodotti al suo interno e la loro rispettiva quantità
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Ordine {
    private int id;
    private double prezzo_totale;
    private String data_acquisto;
    private int id_utente;

    private Hashtable<GiftCard,Integer> prodottoList;

    /**Costruttore dell' oggetto Ordine
     *
     * @param id  id univoco dell' ordine
     * @param prezzo_totale costo totale dell'ordine
     * @param data_acquisto data in cui è stato effettuato l'ordine
     * @param id_utente id dell' utente che ha effettuato l' ordine
     */
    public Ordine(int id, double prezzo_totale, String data_acquisto, int id_utente)
    {
        this.id = id;
        this.prezzo_totale = prezzo_totale;
        this.data_acquisto = data_acquisto;
        this.id_utente = id_utente;
        this.prodottoList=new Hashtable<>();
    }

    /**Costruttore nullo dell' oggetto Ordine
     *
     */
    public Ordine()
    {
        this.prodottoList=new Hashtable<>();
    }

    /**Il metodo <code>getId</code> consente di ricavare l' id dell' ordine
     *
     * @return id intero che identifica univocamente un ordine
     */
    public int getId() {return id;}

    /**Il metodo<code>setId</code> consente di impostare un id univoco per un ordine
     *
     * @param id id intero usato per identificare univocamente l' ordine
     */
    public void setId(int id) {this.id = id;}

    /**Il metodo <code>getPrezzo_totale</code> serve a ricavare il prezzo totale dell' ordine
     *
     * @return valore double che rappresenta il costo totale dell' ordine
     */
    public double getPrezzo_totale() {return prezzo_totale;}

    /**Il metodo <code>setPrezzo_totale</code> serve a impostare il prezzo totale dell' ordine
     *
     * @param prezzo_totale valore double che rappresenta il costo totale dell' ordine
     */
    public void setPrezzo_totale(double prezzo_totale) {this.prezzo_totale = prezzo_totale;}

    /**Il metodo <code>getData_acquisto</code> serve a ricavare la data in cui è stato effettuato l' ordine
     *
     * @return stringa contenente la data in cui è stato effettuato l 'ordine
     */
    public String getData_acquisto() {return data_acquisto;}

    /**Il metodo <code>setData_acquisto</code> serve ad impostare la data in cui viene effettuato l' ordine
     *
     * @param data_acquisto stringa contenente la data in cui è stato effettuato l' ordine
     */
    public void setData_acquisto(String data_acquisto) {this.data_acquisto = data_acquisto;}

    /**Il metodo <code>getId_utente</code> serve a ricavare l' id dell' utente che ha effettuato l' ordine
     *
     * @return id dell'utente che ha effettuato l' ordine
     */
    public int getId_utente() {return id_utente;}

    /**Il metodo <code>setId_utente</code> serve ad impostare l' id dell' utente che ha effettuato l' ordine
     *
     * @param id_utente id dell'utente che ha effettuato l' ordine
     */
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}

    /**Il metodo <code>getProdottoList</code> serve ad ottenere la lista dei prodotti e la loro rispettiva quantità
     * all'interno dell' ordine, organizzati in una struttura dati di tipo Hashtable Giftcard,Integer
     *
     * @return hashtable contenente la lista delle GiftCard e la loro rispettiva quantità all'interno dell' ordine
     */
    public Hashtable<GiftCard, Integer> getProdottoList() {
        return prodottoList;
    }

    /**Il metodo <code>getProdottoList</code> serve ad impostare la lista dei prodotti e la loro rispettiva quantità
     * all'interno dell' ordine, organizzati in una struttura dati di tipo Hashtable Giftcard,Integer
     *
     * @param prodottoList hashtable contenente la lista delle GiftCard e la loro rispettiva quantità all'interno dell' ordine
     */
    public void setProdottoList(Hashtable<GiftCard, Integer> prodottoList) {
        this.prodottoList = prodottoList;
    }
}

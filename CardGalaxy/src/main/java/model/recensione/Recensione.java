package model.recensione;
import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.Date;

/** Un oggetto <code>Recensione</code> rappresenta una recensione di un prodotto effettuata da un utente.
 * E' costituito dall' id dell' account utente che la effettua, dall' id del prodotto(oggetto GiftCard) a cui fa riferimento,
 * dalla data in cui viene effettuata e da una stringa che comprende il testo della recensione
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Recensione
{
    private int id_utente;
    private int id_prodotto;
    private String datarecensione;
    private String testo;

    /**Costruttore dell' oggetto Recensione
     *
     * @param id_utente id dell' account utente che effettua la recensione
     * @param id_prodotto id del prodotto che viene recensito
     * @param datarecensione stringa che contiene la data in cui viene effettuata la recensione
     * @param testo stringa che contine il testo della recensione
     */
    public Recensione(int id_utente,int id_prodotto,String datarecensione,String testo){
        this.id_utente=id_utente;
        this.id_prodotto=id_prodotto;
        this.datarecensione=datarecensione;
        this.testo=testo;
    }

    /**Costruttore nullo dell' oggetto Recensione
     *
     */
    public Recensione() {}

    /**Il metodo <code>getId_utente</code> consente di ottenere
     * l' id dell' account utente che ha effettuato la recensione
     *
     * @return id dell' account utente che ha effettuato la recensione
     */
    public int getId_utente() {
        return id_utente;
    }

    /**Il metodo <code>setId_utente</code> consente di impostare
     * l' id dell' account utente che ha effettuato la recensione
     *
     * @param id_utente id dell' account utente che ha effettuato la recensione
     */
    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    /**Il metodo <code>getId_utente</code> consente di ottenere
     * l' id del prodotto che è stato recensito
     *
     * @return id del prodotto che è stato recensito
     */
    public int getId_prodotto() {
        return id_prodotto;
    }

    /**Il metodo <code>setId_prodotto</code> consente di impostare
     * l' id del prodotto che è stato recensito
     *
     * @param id_prodotto id del prodotto che è stato recensito
     */
    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    /**Il metodo <code>getDatarecensione</code> consente di ottenere la data
     * in cui è stata effettuata la recensione
     *
     * @return stringa contenente la data in cui è stata effettuata la recensione
     */
    public String getDatarecensione() {
        return datarecensione;
    }

    /**Il metodo <code>setDatarecensione</code> consente di impostare la data
     * in cui è stata effettuata la recensione
     *
     * @param datarecensione stringa contenente la data in cui è stata effettuata la recensione
     */
    public void setDatarecensione(String datarecensione) {
        this.datarecensione = datarecensione;
    }

    /**Il metodo <code>getTesto</code> consente di ottenere
     * il testo della recensione
     *
     * @return stringa contenente il testo della recensione
     */
    public String getTesto() {
        return testo;
    }

    /**Il metodo <code>setTesto</code> consente di impostare
     * il testo della recensione
     *
     * @param testo testo da impostare per la recensione
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }
}

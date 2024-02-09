package model.carrello;

import model.prodotto.GiftCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/** La classe <code>CarrelloSession</code> viene usata per la gestione del
 * carrello di un utente nello scope di sessione
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class CarrelloSession {
    private List<CartItems> items;

    /**Costruttore nullo dell' oggetto CarrelloSession in cui
     * si inizializza il suo contenuto
     */
    public CarrelloSession() {
        this.items=new ArrayList<>();
    }

    /** Costruttore dell' oggetto CarrelloSession
     *
     * @param items  lista di CartItems presente nel carrello
     */
    public CarrelloSession(List<CartItems> items) {
        this.items = items;
    }

    /** Il metodo <code>getItems</code> consente di ricavare una lista di oggetti CartItems
     * presenti nel carrello di sessione
     *
     * @return  lista di oggetti CartItems presenti nel carrello di sessione
     */
    public List<CartItems> getItems() {
        return items;
    }

    /**Il metodo <code>setItems</code> consente di aggiungere una lista di oggetti CartItems
     * al carrello di sessione
     *
     * @param items lista di oggetti CartItems da aggiungere al carrello di sessione
     */
    public void setItems(List<CartItems> items) {
        this.items = items;
    }


    /**Il metodo <code>quantita</code> consente di calcolare la quantità di oggetti
     * CartItems presenti nel carrello di sessione
     *
     * @return intero che rappresenta la quantità di oggetti CartItems totali nel carrello di sessione
     */
    public int quantita() {
        int quantita = 0;
        for (CartItems item : items) {
            quantita += item.getQuantita();
        }
        return quantita;
    }

    /** Il metodo <code>totale</code> consente di ricavare il costo totale degli oggetti CartItems
     * presenti nel carrello di sessione
     *
     * @return un double arrotondato a due cifre dopo la virgola che rappresenta il costo totale degli oggetti CartItems presenti nel carrello di sessione
     */
    public double totale() {
        double totale = 0.0;
        for (CartItems item : items) {
            totale += item.totale();
        }
        BigDecimal bd=new BigDecimal(totale).setScale(2, RoundingMode.HALF_UP);
        double tot=bd.doubleValue();
        return tot;
    }

    /** Il metodo <code>addProduct</code> consente di aggiungere un oggetto CartItems al carrello di sessione
     *
     * @param giftCard  oggetto rappresentante il prodotto da aggiungere al carrello di sessione
     * @param quantita  intero che rappresenta la quantità del prodotto aggiunto al carrello di sessione
     * @return          booleano che indica se l'operazione è andata a buon fine
     */
    public boolean addProduct(GiftCard giftCard, int quantita) {
        for (CartItems product : items) {
            if (product.getGiftCard().getId_prodotto()== giftCard.getId_prodotto()) {
                product.setQuantita(product.getQuantita()+1);
                return true;
                }
            }
        return items.add(new CartItems(giftCard, quantita));
    }


    /**il metodo <code>removeProduct</code> consente di rimuovere un oggetto CartItems dal carrello di sessione
     *
     * @param id id del prodotto da rimuovere dal carrello di sessione
     * @return   booleano che indica se l' operazione è andata a buon fine
     */
    public boolean removeProduct(int id) {
        for (CartItems product : items) {
            if (product.getGiftCard().getId_prodotto() == id) {
                return items.remove(product);
            }
        }
        return false;
    }

    /**Il metodo <code>ResetCart</code> consente di azzerare la lista di CartItems presenti nel
     * carrello di sessione
     *
     */
    public void resetCart(){
        items.clear();
    }
}


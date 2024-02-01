package model.carrello;

import model.prodotto.GiftCard;

/** La classe <code>CartItems</code> rappresenta un prodotto nel carrello di sessione,
 * un prodotto è costituito da un oggetto GiftCard e da un intero che ne rappresenta la quantità presente nel carrello
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class CartItems {

    private GiftCard giftCard;
    private int quantita;

    /**Costruttore dell' oggetto CartItems
     *
     * @param giftCard  rappresenta il prodotto presente all' interno dell' oggetto CartItems
     * @param quantita  rappresenta la quantità di prodotto presente all' interno dell' oggetto CartItems
     */
    public CartItems(GiftCard giftCard, int quantita) {
        this.giftCard = giftCard;
        this.quantita = quantita;
    }

    /**Il metodo <code>getGiftCard</code> serve a restituire l'oggetto GiftCard presente all' interno
     * dell' oggetto CartItems
     *
     * @return oggetto GifCard presente nell' oggetto CartItems
     */
    public GiftCard getGiftCard() {
        return giftCard;
    }

    /** Il metodo <code>setGiftCard</code> consente di aggiungere un oggetto GiftCard
     * all' interno dell' oggetto CartItems
     *
     * @param giftCard oggetto GiftCard da aggiungere all'interno dell'oggetto CardItems
     */
    public void setGiftCard(GiftCard giftCard) {
        this.giftCard = giftCard;
    }

    /**Il metodo <code>getQuantita</code> consente di restituire la quantità di un oggetto GiftCard
     * presente nell' oggetto CartItems
     *
     * @return intero che rappresenta la quantità di un oggetto GiftCard presente nell' oggetto CartItems
     */
    public int getQuantita() {
        return quantita;
    }

    /**Il metodo <code>setQuantita</code> consente di impostare la quantità di un oggetto GiftCard
     * presente nell' oggetto CartItems
     *
     * @param quantita intero che rappresenta la quantità di un oggetto GiftCard da aggiungere nell' oggetto CartItems
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**Il metodo <code>totale</code> consente di restituire il prezzo totale di un oggetto GiftCard presente
     * all' interno dell' oggetto CartItems (prezzo totale= prezzo di base*quantità)
     *
     * @return valore double che rappresenta il costo totale di un oggetto GiftCard all'interno dell' oggetto CartItems
     */
    public double totale() {
        return giftCard.getPrezzo() * quantita;
    }


}

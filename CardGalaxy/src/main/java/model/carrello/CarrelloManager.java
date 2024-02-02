package model.carrello;

import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/** Un oggetto <code>CarrelloManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti Carrello all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class CarrelloManager {


    private static final CarrelloQuery QUERY = new CarrelloQuery("carrello");


    /** Il metodo <code>retriveCarrelloByUtente</code> viene usato per ricavare tutti gli oggetti
     * appartenti al carrello di un determinato utente
     *
     * @param idutente id dell' utente di cui si vuole ottenere la lista degli oggetti nel carrello
     * @return lista degli oggetti appartenenti al carrello dell' utente inserito come parametro
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre utente->exist(u|u.id_utente=idutente)
     * @post prodotti_carrello=carrello->select(c|c.id_utente=idutente)->asSet()
     */
    public List<CartItems> retrieveCarrelloByUtente(int idutente){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveCarrelloByUtente())) {
                ps.setInt(1, idutente);
                GiftCardManager giftCardManager=new GiftCardManager();
                List<CartItems> prodotti_carrello=new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    GiftCard gc=giftCardManager.retrieveGiftCardByID(rs.getInt("id_prodotto"));
                    int quant=rs.getInt("quantita");
                    CartItems item=new CartItems(gc,quant);
                    prodotti_carrello.add(item);
                }
                rs.close();
                return prodotti_carrello;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Il metodo <code>rimuoviCarrelloUtente</code> viene usato per rimuovere tutti gli oggetti
     * appartenti al carrello di un determinato utente
     *
     * @param id_utente id dell' utente di cui si vuole rimuovere la lista degli oggetti nel carrello
     * @return variabile booleana che conferma il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_utente!=null &amp;&amp; carrello->exist(u|u.id_utente=id_utente)
     * @post !(carrello->exist(u|u.id_utente=id_utente))
     */
    public boolean rimuoviCarrelloUtente(int id_utente){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.deleteCarrelloUtente())) {
                ps.setInt(1, id_utente);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Il metodo <code>saveToCart</code> serve a salvare una lista di prodotti nel carrello
     * di un utente
     *
     * @param id_utente id dell'utente cui si fa riferimento per il salvataggio della lista
     * @param prodotti lista dei prodotti CartItems che si vogliono salvare nel carrello
     * @return variabile booleana che conferma il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre utente->exist(u|u.id_utente=id_utente)&amp;&amp;prodotti!=null
     * @post carrello->exist(c|c.id_utente=id_utente&amp;&amp;c.id_prodotto=prodotti.giftCard.id_prodotto&amp;&amp;c.quantita=prodotti.quantita)
     */
    public boolean saveToCart(int id_utente,List<CartItems> prodotti){
        try (Connection con = Manager.getConnection()) {
            for(CartItems item:prodotti) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.addToCart())) {
                    ps.setInt(1, id_utente);
                    ps.setInt(2, item.getGiftCard().getId_prodotto());
                    ps.setInt(3, item.getQuantita());
                    ps.executeUpdate();
                }
            }
            return true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Il metodo <code>removeFromCart</code> serve a rimuovere un determinato prodotto dal carrello di un utente
     *
     * @param id_utente         id dell' utente di cui si vuole rimuovere un prodotto dal carrello
     * @param id_prodotto       id del prodotto che si vuole rimuovere dal carrello dell' utente
     * @return            variabile booleana che conferma il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_utente!=null&amp;&amp;id_prodotto!=null
     * @post !(carrello->exist(c|c.id_utente=id_utente&amp;&amp;c.id_prodotto=id_prodotto))
     */
    public boolean removeFromCart(int id_utente,int id_prodotto){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.removeFromCart())) {
                ps.setInt(1,id_utente);
                ps.setInt(2,id_prodotto);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

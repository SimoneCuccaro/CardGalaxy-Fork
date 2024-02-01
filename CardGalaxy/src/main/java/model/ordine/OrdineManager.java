package model.ordine;

import model.carrello.CartItems;
import model.prodotto.GiftCard;
import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**Un oggetto <code>OrdineManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti Ordine all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class OrdineManager extends Manager {
    private static final OrdineQuery QUERY = new OrdineQuery("ordine");

    /**Il metodo <code>retrieveOrdini</code> serve ad ottenere una lista di tutti gli ordini
     * presenti nel database
     *
     * @return lista contenente tutti gli ordini presenti nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public ArrayList<Ordine> retrieveOrdini() {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveAllOrdini())) {
                ResultSet set = ps.executeQuery();
                ArrayList<Ordine> ordini = new ArrayList<>();
                while (set.next()) {
                    Ordine o = new Ordine();
                    o.setId(set.getInt("id"));
                    o.setPrezzo_totale(set.getDouble("prezzo_totale"));
                    o.setData_acquisto(set.getString("data_acquisto"));
                    o.setId_utente(set.getInt("id_utente"));
                    ordini.add(o);
                }
                set.close();
                return ordini;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>retrieveOrdiniByUtente</code> serve ad ottenere tutti gli ordini
     * effettuati da uno specifico utente
     *
     * @param id id dell' utente di cui si vogliono ricavare gli ordini
     * @return lista degli ordini dell' utente passato in input
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public ArrayList<Ordine> retrieveOrdiniByUtente(int id) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveOrderByUser())) {
                ps.setInt(1, id);
                ResultSet set = ps.executeQuery();
                ArrayList<Ordine> ordini = new ArrayList<>();
                while (set.next()) {
                    Ordine o = new Ordine();
                    o.setId(set.getInt("id"));
                    o.setPrezzo_totale(set.getDouble("prezzo_totale"));
                    o.setData_acquisto(set.getString("data_acquisto"));
                    o.setId_utente(set.getInt("id_utente"));
                    ordini.add(o);
                }
                set.close();
                return ordini;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>retrieveOrdineById</code> serve ad ricavare un oggetto Ordine
     * partendo dal suo id presente nel database
     *
     * @param id id dell' ordine di cui si vogliono recuperare i dettagli
     * @return oggetto ordine riempito con i dati relativi all'ordine con id passato in input
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public Ordine retrieveOrdineById(int id) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveOrdineById())) {
                ps.setInt(1, id);
                ResultSet set = ps.executeQuery();
                Ordine o = new Ordine();
                if (set.next()) {
                    o.setId(set.getInt("id"));
                    o.setPrezzo_totale(set.getDouble("prezzo_totale"));
                    o.setData_acquisto(set.getString("data_acquisto"));
                    o.setId_utente(set.getInt("id_utente"));
                }
                set.close();
                return o;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>contaOrdini</code> serve a contare tutti gli ordini
     * presenti nel database
     *
     * @return intero rappresentante il numero di ordini presenti nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public int contaOrdini() {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.countAllOrdini())) {
                ResultSet set = ps.executeQuery();
                int count = 0;
                if (set.next()) {
                    count = set.getInt("ordiniTotali");
                }
                set.close();
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>creaOrdine</code> serve a salvare un oggetto ordine in un database
     *
     * @param ordine oggetto ordine da salvare nel database
     * @return booleano che conferma la riuscita dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean creaOrdine(Ordine ordine) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.creaOrdine(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, ordine.getPrezzo_totale());
                ps.setString(2, ordine.getData_acquisto());
                ps.setInt(3, ordine.getId_utente());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                ordine.setId(id);
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>lastOrder</code> serve ad ottenere l' id dell' ultimo
     * ordine salvato nel database
     *
     * @return id dell'ultimo ordine salavato nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public int lastOrder() {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.lastOrder())) {
                ResultSet set = ps.executeQuery();
                int lastId = 0;
                if (set.next()) {
                    lastId = set.getInt("lastorder");
                }
                set.close();
                return lastId;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>retriveProdotti</code> serve ad ottenere tutti i prodotti e la rispettiva quantità
     * appartenenti ad un determinato ordine
     *
     * @param codiceordine id dell'ordine di cui si voglio ottenere prodotti e rispettiva quantità
     * @return Hashtable di tipo GifCard,Integer con tutti i prodotti e la rispettiva quantità inerenti ad un ordine
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public Hashtable<GiftCard, Integer> retriveProdotti(int codiceordine) {
        Hashtable<GiftCard, Integer> prodotti = new Hashtable<>();
        int quantita;
        GiftCard giftCard;
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retriveProdotti(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, codiceordine);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    giftCard = new GiftCard();
                    giftCard.setId_prodotto(rs.getInt("id_prodotto"));
                    giftCard.setNome(rs.getString("nome"));
                    giftCard.setPiattaforma(rs.getString("piattaforma"));
                    giftCard.setDescrizione(rs.getString("descrizione"));
                    giftCard.setPrezzo(rs.getDouble("prezzo"));
                    giftCard.setFoto(rs.getString("foto"));
                    giftCard.setAvailable(rs.getBoolean("isAvailable"));
                    quantita = rs.getInt("quantita");
                    prodotti.put(giftCard, quantita);
                }
                rs.close();
                return prodotti;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>getGuadagno</code> serve ad ottenere la somma totale del prezzo degli ordini
     *
     * @return valore double rappresentante la somma totale del prezzo degli ordini
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public double getGuadagno() {
        double guadagno = 0.0;
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.getGuadagno())) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    guadagno += rs.getDouble(1);
                }
                return guadagno;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>updateContenuto</code> serve ad aumentare la quantità di un prodotto
     * presente all' interno di un ordine
     *
     * @param quantity intero che indica la nuova quantità di prodotto
     * @param idorder intero che rappresenta l' id dell' ordine
     * @param idproduct intero che rappresenta l'id del prodotto di cui si deve aggiornare la quantità
     * @return booleano che conferma la riuscita dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean updateContenuto(int quantity, int idorder, int idproduct) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.updateContenuto())){
                ps.setInt(1, quantity);
                ps.setInt(2, idorder);
                ps.setInt(3, idproduct);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>updateOrder</code> serve ad aggiornare il prezzo totale di un ordine
     *
     * @param totale valore double cui si desidera aggiornare il totale dell' ordine
     * @return booleano che conferma la riuscita dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean updateOrder(double totale) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.updateOrder())){
                ps.setDouble(1, totale);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>removeOrder</code> serve a rimuovere un ordine dal database
     *
     * @param id id dell'ordine che si desidera rimuovere dal database
     * @return booleano che conferma la riuscita dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean removeOrder(int id){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.removeOrder())){
                ps.setInt(1,id);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>saveContenuto</code> serve a mappare nella tabella Contenuto un ordine e
     *la lista di prodotti contenuti nell' ordine
     * @param ordine oggetto ordine a cui si vuole fare riferimento
     * @param items lista di oggetti CartItems che si vogliono salvare nell' ordine
     * @return booleano che conferma la riuscita dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean saveContenuto(Ordine ordine, List<CartItems> items) {
        try (Connection con = Manager.getConnection()) {
            for (CartItems prodotto : items) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.saveContenuto())) {
                    ps.setInt(1, ordine.getId());
                    ps.setInt(2, prodotto.getGiftCard().getId_prodotto());
                    ps.setInt(3, prodotto.getQuantita());
                    if (ps.executeUpdate() != 1) {
                        throw new RuntimeException("INSERT error.");
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

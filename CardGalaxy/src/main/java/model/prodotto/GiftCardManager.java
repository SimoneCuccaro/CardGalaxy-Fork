package model.prodotto;

import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;

/**
 * Un oggetto <code>GiftCardManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti GiftCard all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class GiftCardManager {
    private static final GiftCardQuery QUERY = new GiftCardQuery("prodotto");


    /**
     * Il metodo <code>retrieveTutteGiftCard</code> consente di ottenere tutti gli oggetti GiftCard
     * salvati nel database
     *
     * @return lista contenente gli oggetti GiftCard presenti nel database
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @post giftCards=prodotto->asSet()
     */
    public ArrayList<GiftCard> retrieveTutteGiftCard() {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.tutteGiftCard())) {
                ResultSet rs = ps.executeQuery();
                ArrayList<GiftCard> giftCards = new ArrayList<>();
                while (rs.next()) {
                    GiftCard gift = new GiftCard();
                    gift.setId_prodotto(rs.getInt("id_prodotto"));
                    gift.setNome(rs.getString("nome"));
                    gift.setPiattaforma(rs.getString("piattaforma"));
                    gift.setDescrizione(rs.getString("descrizione"));
                    gift.setPrezzo(rs.getDouble("prezzo"));
                    gift.setFoto(rs.getString("foto"));
                    gift.setAvailable(rs.getBoolean("isAvailable"));
                    giftCards.add(gift);
                }
                rs.close();
                return giftCards;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo <code>retrieveGiftCardByID</code> consente di ottenere un oggetto GiftCard con uno specifico id
     * salvato nel database
     *
     * @param id_prodotto id dell' oggetto GiftCard di cui si vogliono recuperare le informazioni
     * @return oggetto GiftCard con id fornito salvato nel database
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_prodotto!=null
     * @post gift=prodotto->select(p|p.id_prodotto=id_prodotto)
     */
    public GiftCard retrieveGiftCardByID(int id_prodotto) {
        GiftCard gift;
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveGiftCardByID())) {
                ps.setInt(1, id_prodotto);
                ResultSet rs = ps.executeQuery();
                gift = new GiftCard();
                if (rs.next()) {
                    gift.setId_prodotto(rs.getInt("id_prodotto"));
                    gift.setNome(rs.getString("nome"));
                    gift.setPiattaforma(rs.getString("piattaforma"));
                    gift.setDescrizione(rs.getString("descrizione"));
                    gift.setPrezzo(rs.getDouble("prezzo"));
                    gift.setFoto(rs.getString("foto"));
                    gift.setAvailable(rs.getBoolean("isAvailable"));
                }
                rs.close();
                return gift;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Il metodo <code>inserisciGiftCard</code> consente di inserire un oggetto GiftCard
     * all' interno del database
     *
     * @param gift oggetto GiftCard da salvare nel database
     * @return booleano che conferma il successo dell' operazione
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre gift.nome!=null&amp;&amp;gift.piattaforma!=null&amp;&amp;gift.descrizione!=null&amp;&amp;gift.prezzo!=null&amp;&amp;gift.foto!=null&amp;&amp;gift.isAvailable!=null&amp;&amp;!(prodotto->includes(gift))
     * @post prodotto->includes(gift)
     */
    public boolean inserisciGiftCard(GiftCard gift) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciGiftCard(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, gift.getNome());
                ps.setString(2, gift.getPiattaforma());
                ps.setString(3, gift.getDescrizione());
                ps.setDouble(4, gift.getPrezzo());
                ps.setString(5, gift.getFoto());
                ps.setBoolean(6, gift.checkisAvailable());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id_prodotto = rs.getInt(1);
                gift.setId_prodotto(id_prodotto);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo <code>aggiornaGiftCard</code> consente di aggiornare le informazioni relative ad un oggetto GiftCard
     * salvato all' interno del databse
     *
     * @param gift oggetto GiftCard con le informazioni aggiornate
     * @return booleano che indica il successo dell' operazione
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre gift.nome!=null&amp;&amp;gift.piattaforma!=null&amp;&amp;gift.descrizione!=null&amp;&amp;gift.prezzo!=null&amp;&amp;gift.foto!=null&amp;&amp;gift.isAvailable!=null&amp;&amp;prodotto->exist(p|p.id_prodotto=gift.id_prodotto)
     * @post prodotto->includes(gift)
     */
    public boolean aggiornaGiftCard(GiftCard gift) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.aggiornaGiftCard())) {
                ps.setString(1, gift.getNome());
                ps.setString(2, gift.getPiattaforma());
                ps.setString(3, gift.getDescrizione());
                ps.setDouble(4, gift.getPrezzo());
                ps.setString(5, gift.getFoto());
                ps.setBoolean(6, gift.checkisAvailable());
                ps.setInt(7, gift.getId_prodotto());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo <code>rimuoviGiftCard</code> consente di rendere non visibile nel sistema un oggetto GiftCard
     * presente nel database
     *
     * @param id_prodotto id dell' oggetto GiftCard che si desidera non rendere visibile
     * @return booleano che indica il successo dell' operazione
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_prodotto!=null&amp;&amp;prodotto->exist(p|p.id_prodotto=id_prodotto)
     * @post !(prodotto->exist(p|p.id_prodotto=id_prodotto))
     */
    public boolean rimuoviGiftCard(int id_prodotto) {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.rimuoviGiftCard())) {
                ps.setInt(1, id_prodotto);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Il metodo <code>countAllGiftCard</code> consente di contare quanti oggetti GiftCard sono salvati nel database
     *
     * @return intero che indica il numero di oggetti GiftCard salvati nel database
     * @throws RuntimeException genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @post size=prodotto->count->asSet()
     */
    public int countAllGiftCard() {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.countAllGiftCard())) {
                ResultSet resultSet = ps.executeQuery();
                int size = 0;
                if (resultSet.next()) {
                    size = resultSet.getInt("giftCardTotali");
                }
                return size;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

package model.prodotto;
import model.prodotto.GiftCard;
import model.storage.Manager;
import model.prodotto.GiftCardQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class GiftCardManager
{
    private static final GiftCardQuery QUERY = new GiftCardQuery("giftCard");


    public ArrayList<GiftCard> retrieveTutteGiftCard() throws SQLException{
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.tutteGiftCard())){
                ResultSet rs = ps.executeQuery();
                ArrayList<GiftCard> giftCards = new ArrayList<>();
                while(rs.next()){
                    GiftCard gift = new GiftCard();
                    gift.setIdProdotto(rs.getInt("id"));
                    gift.setNome(rs.getString("nome"));
                    gift.setPiattaforma(rs.getString("piattaforma"));
                    gift.setDescrizione(rs.getString("descrizione"));
                    gift.setPrezzo(rs.getDouble("prezzo"));
                    giftCards.add(gift);
                }
                rs.close();
                return giftCards;
            }
        }
    }

    public GiftCard retrieveGiftCardByID(int id) throws SQLException {
        GiftCard gift;
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveGiftCardByID())) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                gift = new GiftCard();
                if (rs.next()) {
                    gift.setIdProdotto(id);
                    gift.setNome(rs.getString("nome"));
                    gift.setPiattaforma(rs.getString("piattaforma"));
                    gift.setDescrizione(rs.getString("descrizione"));
                    gift.setPrezzo(rs.getDouble("prezzo"));
                }
                rs.close();
                return gift;
            }
        }
    }


        public boolean inserisciGiftCard (GiftCard gift) throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciGiftCard())) {
                    ps.setInt(1, gift.getIdProdotto());
                    ps.setString(2, gift.getNome());
                    ps.setString(3, gift.getPiattaforma());
                    ps.setString(4, gift.getDescrizione());
                    ps.setDouble(5, gift.getPrezzo());
                    ps.executeUpdate();
                    return true;
                }
            }
        }

        public boolean aggiornaScarpa (GiftCard gift) throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.aggiornaGiftCard())) {
                    ps.setInt(1, gift.getIdProdotto());
                    ps.setString(2, gift.getNome());
                    ps.setString(3, gift.getPiattaforma());
                    ps.setString(4, gift.getDescrizione());
                    ps.setDouble(5, gift.getPrezzo());
                    ps.executeUpdate();
                    return true;
                }
            }
        }

        public boolean rimuoviGiftCard(int idGiftCard) throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.rimuoviGiftCard())) {
                    ps.setInt(1, idGiftCard);
                    ps.executeUpdate();
                    return true;
                }
            }
        }

        public int countAllGiftCard () throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.countAllGiftCard())) {
                    ResultSet resultSet = ps.executeQuery();
                    int size = 0;
                    if (resultSet.next()) {
                        size = resultSet.getInt("giftCardTotali");
                    }
                    return size;
                }
            }
        }
    }

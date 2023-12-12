package model.prodotto;

import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
public class GiftCardManager
{
    private static final GiftCardQuery QUERY = new GiftCardQuery("giftCard");


    public ArrayList<GiftCard> retrieveTutteGiftCard(){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.tutteGiftCard())){
                ResultSet rs = ps.executeQuery();
                ArrayList<GiftCard> giftCards = new ArrayList<>();
                while(rs.next()){
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
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GiftCard retrieveGiftCardByID(int id_prodotto){
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
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        public boolean inserisciGiftCard (GiftCard gift){
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciGiftCard(), Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, gift.getNome());
                    ps.setString(2, gift.getPiattaforma());
                    ps.setString(3, gift.getDescrizione());
                    ps.setDouble(4, gift.getPrezzo());
                    ps.setString(5, gift.getFoto());
                    ps.setBoolean(6,gift.isAvailable());
                    if (ps.executeUpdate() != 1) {
                        throw new RuntimeException("INSERT error.");
                    }
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    int id_prodotto = rs.getInt(1);
                    gift.setId_prodotto(id_prodotto);
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean aggiornaGiftCard (GiftCard gift){
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.aggiornaGiftCard())) {
                    ps.setString(1, gift.getNome());
                    ps.setString(2, gift.getPiattaforma());
                    ps.setString(3, gift.getDescrizione());
                    ps.setDouble(4, gift.getPrezzo());
                    ps.setString(5, gift.getFoto());
                    ps.setBoolean(6,gift.isAvailable());
                    ps.setInt(7,gift.getId_prodotto());
                    ps.executeUpdate();
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean rimuoviGiftCard(int id_prodotto){
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.rimuoviGiftCard())) {
                    ps.setInt(1, id_prodotto);
                    ps.executeUpdate();
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public int countAllGiftCard (){
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.countAllGiftCard())) {
                    ResultSet resultSet = ps.executeQuery();
                    int size = 0;
                    if (resultSet.next()) {
                        size = resultSet.getInt("giftCardTotali");
                    }
                    return size;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean addToCart(int id_utente,int id_prodotto,int quantita){
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.addToCart())) {
                    ps.setInt(1,id_utente);
                    ps.setInt(2,id_prodotto);
                    ps.setInt(3,quantita);
                     ps.executeUpdate();
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

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

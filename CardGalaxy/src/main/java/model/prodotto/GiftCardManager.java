package model.prodotto;

import model.storage.Manager;


import java.sql.*;
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
                    gift.setId(rs.getInt("id"));
                    gift.setNome(rs.getString("nome"));
                    gift.setPiattaforma(rs.getString("piattaforma"));
                    gift.setDescrizione(rs.getString("descrizione"));
                    gift.setPrezzo(rs.getDouble("prezzo"));
                    gift.setFoto(rs.getString("foto"));
                    giftCards.add(gift);
                }
                rs.close();
                return giftCards;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
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
                    gift.setId(id);
                    gift.setNome(rs.getString("nome"));
                    gift.setPiattaforma(rs.getString("piattaforma"));
                    gift.setDescrizione(rs.getString("descrizione"));
                    gift.setPrezzo(rs.getDouble("prezzo"));
                    gift.setFoto(rs.getString("foto"));
                }
                rs.close();
                return gift;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        public boolean inserisciGiftCard (GiftCard gift) throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciGiftCard(), Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, gift.getNome());
                    ps.setString(2, gift.getPiattaforma());
                    ps.setString(3, gift.getDescrizione());
                    ps.setDouble(4, gift.getPrezzo());
                    ps.setString(5, gift.getFoto());
                    if (ps.executeUpdate() != 1) {
                        throw new RuntimeException("INSERT error.");
                    }
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    int id = rs.getInt(1);
                    gift.setId(id);
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean aggiornaGiftCard (GiftCard gift) throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.aggiornaGiftCard())) {
                    ps.setString(1, gift.getNome());
                    ps.setString(2, gift.getPiattaforma());
                    ps.setString(3, gift.getDescrizione());
                    ps.setDouble(4, gift.getPrezzo());
                    ps.setString(5, gift.getFoto());
                    ps.executeUpdate();
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean rimuoviGiftCard(int idGiftCard) throws SQLException {
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.rimuoviGiftCard())) {
                    ps.setInt(1, idGiftCard);
                    ps.executeUpdate();
                    return true;
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
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
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean addToOrder(int codiceordine,int idprodotto, int quantita) throws SQLException{
            try (Connection con = Manager.getConnection()) {
                try (PreparedStatement ps = con.prepareStatement(QUERY.addToOrder())) {
                    ps.setInt(1,codiceordine);
                    ps.setInt(2,idprodotto);
                    ps.setInt(3,quantita);
                    ResultSet resultSet = ps.executeQuery();
                    if(ps.executeUpdate()!=1){
                        throw new RuntimeException("INSERT error.");
                    }
                    return true;
                }
             }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

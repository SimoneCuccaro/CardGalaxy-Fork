package model.carrello;

import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;

public class CarrelloManager {


    private static final CarrelloQuery QUERY = new CarrelloQuery("carrello");



    public ArrayList<GiftCard> retrieveCarrelloByUtente(int idutente){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveCarrelloByUtente())) {
                ps.setInt(1, idutente);
                GiftCardManager giftCardManager=new GiftCardManager();
                ArrayList<GiftCard> prodotti_carrello=new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    GiftCard gc=giftCardManager.retrieveGiftCardByID(rs.getInt("id_prodotto"));
                    prodotti_carrello.add(gc);
                }
                rs.close();
                return prodotti_carrello;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


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
}

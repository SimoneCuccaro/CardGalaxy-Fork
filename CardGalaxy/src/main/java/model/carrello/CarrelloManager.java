package model.carrello;

import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class CarrelloManager {


    private static final CarrelloQuery QUERY = new CarrelloQuery("carrello");



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

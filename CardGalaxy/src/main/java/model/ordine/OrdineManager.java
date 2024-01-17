package model.ordine;

import model.carrello.CartItems;
import model.prodotto.GiftCard;
import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class OrdineManager extends Manager {
    private static final OrdineQuery QUERY = new OrdineQuery("ordine");

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

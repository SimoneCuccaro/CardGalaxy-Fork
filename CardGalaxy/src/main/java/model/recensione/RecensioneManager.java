package model.recensione;


import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
public class RecensioneManager{
    private static final RecensioneQuery QUERY = new RecensioneQuery("recensioni");


    public ArrayList<Recensione> retrieveRecensioni(){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.retriveRecensioni())){
                ResultSet rs = ps.executeQuery();
                ArrayList<Recensione> recensioni = new ArrayList<>();
                while(rs.next()){
                    Recensione rec=new Recensione();
                    rec.setId_utente(rs.getInt("id_utente"));
                    rec.setId_prodotto(rs.getInt("id_prodotto"));
                    rec.setDatarecensione(rs.getString("datarecensione"));
                    rec.setTesto(rs.getString("testo"));
                    recensioni.add(rec);
                }
                rs.close();
                return recensioni;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> retrieveRecensioniByProdotto(int id_prodotto){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveRecensioniByProdotto())) {
                ps.setInt(1, id_prodotto);
                ResultSet rs = ps.executeQuery();
                ArrayList<Recensione> recensioniProd = new ArrayList<>();
                if (rs.next()) {
                    Recensione rec=new Recensione();
                    rec.setId_utente(rs.getInt("id_utente"));
                    rec.setId_prodotto(rs.getInt("id_prodotto"));
                    rec.setDatarecensione(rs.getString("datarecensione"));
                    rec.setTesto(rs.getString("testo"));
                    recensioniProd.add(rec);
                }
                rs.close();
                return recensioniProd;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean inserisciRecensione (Recensione rec){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciRecensione())) {
                ps.setInt(1, rec.getId_utente());
                ps.setInt(2, rec.getId_prodotto());
                ps.setString(3, rec.getDatarecensione());
                ps.setString(4, rec.getTesto());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean aggiornaRecensione(Recensione rec){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.aggiornaRecensione())) {
                ps.setString(1, rec.getTesto());
                ps.setString(2, rec.getDatarecensione());
                ps.setInt(3, rec.getId_utente());
                ps.setInt(4, rec.getId_prodotto());
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rimuoviRecensione(int id_utente,int id_prodotto){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.rimuoviRecensione())) {
                ps.setInt(1, id_utente);
                ps.setInt(2, id_prodotto);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

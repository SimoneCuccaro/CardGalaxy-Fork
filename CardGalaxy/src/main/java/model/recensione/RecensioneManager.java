package model.recensione;


import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;
public class RecensioneManager{
    private static final RecensioneQuery QUERY = new RecensioneQuery("recensioni");


    public ArrayList<Recensione> retrieveRecensioni() throws SQLException{
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.retriveRecensioni())){
                ResultSet rs = ps.executeQuery();
                ArrayList<Recensione> recensioni = new ArrayList<>();
                while(rs.next()){
                    Recensione rec=new Recensione();
                    rec.setIdutente(rs.getInt("idutente"));
                    rec.setCodiceprodotto(rs.getInt("codiceprodotto"));
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

    public ArrayList<Recensione> retrieveRecensioniByProdotto(int codiceprodotto) throws SQLException {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveRecensioniByProdotto())) {
                ps.setInt(1, codiceprodotto);
                ResultSet rs = ps.executeQuery();
                ArrayList<Recensione> recensioniProd = new ArrayList<>();
                if (rs.next()) {
                    Recensione rec=new Recensione();
                    rec.setIdutente(rs.getInt("idutente"));
                    rec.setCodiceprodotto(rs.getInt("codiceprodotto"));
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


    public boolean inserisciRecensione (Recensione rec) throws SQLException {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciRecensione())) {
                ps.setInt(1, rec.getIdutente());
                ps.setInt(2, rec.getCodiceprodotto());
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

    public boolean aggiornaRecensione(Recensione rec) throws SQLException {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.aggiornaRecensione())) {
                ps.setInt(1, rec.getIdutente());
                ps.setInt(2, rec.getCodiceprodotto());
                ps.setString(3, rec.getDatarecensione());
                ps.setString(4, rec.getTesto());
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rimuoviRecensione(int idutente,int codiceProdotto) throws SQLException {
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.rimuoviRecensione())) {
                ps.setInt(1, idutente);
                ps.setInt(2, codiceProdotto);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

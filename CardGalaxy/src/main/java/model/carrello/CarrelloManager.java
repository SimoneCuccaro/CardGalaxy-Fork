package model.carrello;

import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;

public class CarrelloManager {


    private static final CarrelloQuery QUERY = new CarrelloQuery("carrello");


    public ArrayList<Carrello> retrieveAllCarrelli(){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.retrieveAllCarrelli())){
                ResultSet rs = ps.executeQuery();
                ArrayList<Carrello> carrelli = new ArrayList<>();
                while(rs.next()){
                    Carrello carrello=new Carrello();
                    carrello.setIdutente(rs.getInt("idutente"));
                    carrello.setCodiceordine(rs.getInt("codiceordine"));
                    carrelli.add(carrello);
                }
                rs.close();
                return carrelli;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Carrello retrieveCarrelloByUtente(int idutente){
        Carrello carrello;
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveCarrelloByUtente())) {
                ps.setInt(1, idutente);
                ResultSet rs = ps.executeQuery();
                carrello=new Carrello();
                if (rs.next()) {
                    carrello.setIdutente(rs.getInt("idutente"));
                    carrello.setCodiceordine(rs.getInt("codiceordine"));
                }
                rs.close();
                return carrello;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean rimuoviCarrelloUtente(int idutente){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.deleteCarrelloUtente())) {
                ps.setInt(1, idutente);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

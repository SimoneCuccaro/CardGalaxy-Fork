package model.richiestasupporto;

import model.prodotto.GiftCard;
import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;
public class RichiestaSupportoManager {
    private static final RichiestaSupportoQuery QUERY = new RichiestaSupportoQuery("richiestasupporto");


    public ArrayList<RichiestaSupporto> retrieveAllRequest(){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retriveRichiesteSupporto())) {
                ResultSet rs = ps.executeQuery();
                ArrayList<RichiestaSupporto> richieste = new ArrayList<>();
                while (rs.next()) {
                    RichiestaSupporto richiestaSupporto = new RichiestaSupporto();
                    richiestaSupporto.setId_richiesta(rs.getInt("id_richiesta"));
                    richiestaSupporto.setRichiesta(rs.getString("richiesta"));
                    richiestaSupporto.setOggetto_richiesta(rs.getString("oggetto_richiesta"));
                    richiestaSupporto.setId_utente(rs.getInt("id_utente"));
                    richieste.add(richiestaSupporto);
                }
                rs.close();
                return richieste;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RichiestaSupporto retrieveRichiestaSupportoByID(int id_richiesta){
        RichiestaSupporto request;
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retriveRichiesteSupportoById())) {
                ps.setInt(1, id_richiesta);
                ResultSet rs = ps.executeQuery();
                request=new RichiestaSupporto();
                if (rs.next()) {
                    request.setId_richiesta(rs.getInt("id_richiesta"));
                    request.setRichiesta(rs.getString("richiesta"));
                    request.setOggetto_richiesta(rs.getString("oggetto_richiesta"));
                    request.setId_utente(Integer.parseInt(rs.getString("id_utente")));
                }
                rs.close();
                return request;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean inserisciRichiestaSupporto(RichiestaSupporto richiesta){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciRichiestaSupporto(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, richiesta.getRichiesta());
                ps.setString(2, richiesta.getOggetto_richiesta());
                ps.setInt(3, richiesta.getId_utente());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                richiesta.setId_richiesta(id);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

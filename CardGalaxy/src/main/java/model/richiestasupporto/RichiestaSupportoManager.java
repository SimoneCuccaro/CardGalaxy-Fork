package model.richiestasupporto;

import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;
public class RichiestaSupportoManager {
    private static final RichiestaSupportoQuery QUERY = new RichiestaSupportoQuery("richiestasupporto");


    public ArrayList<RichiestaSupporto> retrieveTutteGiftCard(){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retriveRichiesteSupporto())) {
                ResultSet rs = ps.executeQuery();
                ArrayList<RichiestaSupporto> richieste = new ArrayList<>();
                while (rs.next()) {
                    RichiestaSupporto richiestaSupporto = new RichiestaSupporto();
                    richiestaSupporto.setId(rs.getInt("id"));
                    richiestaSupporto.setRichiesta(rs.getString("richiesta"));
                    richiestaSupporto.setOggettorichiesta(rs.getString("oggeettorichiesta"));
                    richiestaSupporto.setIdutente(rs.getInt("idutente"));
                    richieste.add(richiestaSupporto);
                }
                rs.close();
                return richieste;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean inserisciRichiestaSupporto(RichiestaSupporto richiesta){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciRichiestaSupporto(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, richiesta.getRichiesta());
                ps.setString(2, richiesta.getOggettorichiesta());
                ps.setInt(3, richiesta.getIdutente());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                richiesta.setId(id);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

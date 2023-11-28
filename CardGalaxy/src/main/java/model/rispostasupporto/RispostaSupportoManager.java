package model.rispostasupporto;


import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;
public class RispostaSupportoManager {
    private static final RispostaSupportoQuery QUERY = new RispostaSupportoQuery("rispostasupporto");


    public ArrayList<RispostaSupporto> retrieveTutteGiftCard(){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retriveRisposteSupporto())) {
                ResultSet rs = ps.executeQuery();
                ArrayList<RispostaSupporto> risposte = new ArrayList<>();
                while (rs.next()) {
                    RispostaSupporto rispostaSupporto = new RispostaSupporto();
                    rispostaSupporto.setId(rs.getInt("id"));
                    rispostaSupporto.setRisposta(rs.getString("risposta"));
                    rispostaSupporto.setRichiestasupporto(rs.getInt("idrichiestasupporto"));
                    risposte.add(rispostaSupporto);
                }
                rs.close();
                return risposte;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean inserisciRispostaSupporto(RispostaSupporto risposta){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciRispostaSupporto(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, risposta.getRisposta());
                ps.setInt(2, risposta.getRichiestasupporto());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                risposta.setId(id);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

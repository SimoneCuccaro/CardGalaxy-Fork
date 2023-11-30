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
                    rispostaSupporto.setId_utente(rs.getInt("id_utente"));
                    rispostaSupporto.setRisposta(rs.getString("risposta"));
                    rispostaSupporto.setId_richiesta_supporto(rs.getInt("id_richiesta_supporto"));
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
            try (PreparedStatement ps = con.prepareStatement(QUERY.inserisciRispostaSupporto())) {
                ps.setInt(1,risposta.getId_utente());
                ps.setString(2, risposta.getRisposta());
                ps.setInt(3, risposta.getId_richiesta_supporto());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

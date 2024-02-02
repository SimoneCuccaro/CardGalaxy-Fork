package model.rispostasupporto;


import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;

/** Un oggetto <code>RispostaSupportoManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti RispostaSupporto all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RispostaSupportoManager {
    private static final RispostaSupportoQuery QUERY = new RispostaSupportoQuery("rispostasupporto");


    /**Il metodo <code>retriveTutteRisposte</code> consente di ottenere tutti gli oggetti RipostaSupporto
     * salvati nel database
     *
     * @return lista contenente gli oggetti RispostaSupporto presenti nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @post risposte=rispostasupporto->asSet()
     */
    public ArrayList<RispostaSupporto> retrieveTutteRisposte(){
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

    /**Il metodo <code>inserisciRispostaSupporto</code> consente di inserire un oggetto RispostaSupporto
     * all' interno del database
     *
     * @param risposta oggetto RispostaSupporto da salvare nel database
     * @return booleano che conferma il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre risposta.id_utente!=null&amp;&amp;risposta.risposta!=null&amp;&amp;risposta.id_richiesta_supporto!0=null&amp;&amp;!(rispostasupporto->includes(risposta))
     * @post rispostasupporto->includes(risposta)
     */
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

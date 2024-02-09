package model.richiestasupporto;

import model.prodotto.GiftCard;
import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;

/** Un oggetto <code>RichiestaSupportoManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti RichiestaSupporto all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RichiestaSupportoManager {
    private static final RichiestaSupportoQuery QUERY = new RichiestaSupportoQuery("richiestasupporto");


    /**Il metodo <code>retriveAllRequest</code> consente di ottenere tutti gli oggetti RichiestaSupporto
     * salvati nel database
     *
     * @return lista contenente gli oggetti RichiestaSupporto presenti nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @post richieste=richestasupporto->asSet()
     */
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

    /**Il metodo <code>retriveTutteRisposte</code> consente di ottenere un oggetto RichiestaSupporto con uno specifico id
     * salvato nel database
     *
     * @param id_richiesta id dell' oggetto RichiestaSupporto di cui si vogliono recuperare le informazioni
     * @return oggetto RichiestaSupporto con id fornito salvato nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_richiesta!=null
     * @post request=richiestasupporto->select(r|r.id_richiesta=id_richiesta)
     */
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
                    request.setId_utente(rs.getInt("id_utente"));
                }
                rs.close();
                return request;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>inserisciRichiestaSupporto</code> consente di inserire un oggetto RichiestaSupporto
     * all' interno del database
     *
     * @param richiesta oggetto RichiestaSupporto da salvare nel database
     * @return booleano che conferma il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre richiesta.richiesta!=null&amp;&amp;richiesta.oggetto_richiesta!=null&amp;&amp;utente->exist(u|u.id=richiestasupporto.id_utente)&amp;&amp;!(richiestasupporto->includes(richiesta))
     * @post richiestasupporto->includes(richiesta)
     */
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

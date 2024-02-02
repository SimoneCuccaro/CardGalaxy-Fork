package model.recensione;


import model.storage.Manager;


import java.sql.*;
import java.util.ArrayList;

/** Un oggetto <code>RecensioneManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti Recensione all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RecensioneManager{
    private static final RecensioneQuery QUERY = new RecensioneQuery("recensioni");


    /**Il metodo <code>retriveRecensioni</code> consente di ottenere tutti gli oggetti Recensione
     * salvati nel database
     *
     * @return lista contenente gli oggetti Recensione presenti nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @post recensioni=recensioni->asSet()
     */
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

    /**Il metodo <code>retriveRecensioniByProdotto</code> consente di ottenere tutti gli oggetti Recensione
     * salvati nel database inerenti ad un singolo oggetto GiftCard
     *
     * @param id_prodotto id dell' oggetto GiftCard di cui si vogliono otteneree le recensioni
     * @return lista di oggetti Recensione inerenti all' oggetto GiftCard fornito
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_prodotto!=null
     * @post recensioniProd=recensioni->select(r|r.id_prodotto=id_prodotto)->asSet()
     */
    public ArrayList<Recensione> retrieveRecensioniByProdotto(int id_prodotto){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.retrieveRecensioniByProdotto())) {
                ps.setInt(1, id_prodotto);
                ResultSet rs = ps.executeQuery();
                ArrayList<Recensione> recensioniProd = new ArrayList<>();
                while (rs.next()) {
                    Recensione rec1=new Recensione();
                    rec1.setId_utente(rs.getInt("id_utente"));
                    rec1.setId_prodotto(rs.getInt("id_prodotto"));
                    rec1.setDatarecensione(rs.getString("datarecensione"));
                    rec1.setTesto(rs.getString("testo"));
                    recensioniProd.add(rec1);
                }
                rs.close();
                return recensioniProd;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>inserisciRecensione</code> serve ad inserire un oggetto Recensione
     * all' interno del database
     *
     * @param rec oggetto Recensione da salvare nel database
     * @return booleano che indica il risultato dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre rec.id_utente!=null&amp;&amp;rec.id_prodotto!=null&amp;&amp;rec.testo!=null&amp;&amp;!(recensioni->includes(rec))
     * @post recensioni->includes(rec)
     */
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

    /**Il metodo <code>aggiornaRecensione</code> serve ad aggiornare un oggetto Recensione
     * all' interno del database
     *
     * @param rec oggetto Recensione da aggiornare nel database
     * @return booleano che indica il risultato dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre @pre rec.id_utente!=null&amp;&amp;rec.id_prodotto!=null&amp;&amp;rec.testo!=null&amp;&amp;recensioni->exist(r|r.id_utente=rec.id_utente&amp;&amp;r.id_prodotto=rec.id_prodotto)
     * @post recensioni->includes(rec)
     */
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

    /**Il metodo <code>rimuoviRecensione</code> serve a rimuovere un oggetto Recensione
     * dal database
     *
     * @param id_utente id dell' account utente che ha effettuato la recensione
     * @param id_prodotto id del' oggetto GiftCard di cui si vuole rimuovere la recensione
     * @return booleano che indica il risultato dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     * @pre id_utente!=null&amp;&amp;id_prodotto!=null&amp;&amp;recensioni->exist(r|r.id_utente=id_utente&amp;&amp;r.id_prodotto=id_prodotto)
     * @post !(recensioni->exists(r|r.id_utente=id_utente&amp;&amp;&amp;&amp;r.id_prodotto=id_prodotto))
     */
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

package model.utente;

import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;

/** Un oggetto <code>UtenteManager</code> serve a gestire la memorizzazione delle
 * informazioni relative agli oggetti Utente all'interno del database
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class UtenteManager extends Manager
{
    private static final UtenteQuery QUERY = new UtenteQuery("utente");

    /**Il metodo <code>retrieveUtenti</code> viene usato per ricavare tutti gli oggetti di tipo Utente
     * salvati nel database
     *
     * @return lista di tipo Utente contente tutti gli oggetti Utente presenti nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public ArrayList<Utente> retrieveUtenti(){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.tuttiUtenti())) {
                ResultSet set = ps.executeQuery();
                ArrayList<Utente> utenti = new ArrayList<>();
                while (set.next()) {
                    Utente u = new Utente();
                    u.setId(set.getInt("id"));
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setNazione(set.getString("nazione"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setData_nascita(set.getString("data_nascita"));
                    u.setAdmin(set.getBoolean("is_admin"));
                    utenti.add(u);
                }
                set.close();
                return utenti;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**Il metodo <code>retrieveUtentePass</code> serve a ricavare le informazioni relative ad un utente
     * a partire dal suo username e dalla sua password
     *
     * @param username username associato all' account utente di cui si vogliono ricavare il restante delle informazioni
     * @param password password associata all' account utente di cui si vogliono ricavare il restante delle informazioni
     * @return oggetto Utente contenente tutte le informazioni relative all' account utente con i dati provvisti
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public Utente retrieveUtentePass(String username, String password){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.singoloUtenteConPass())) {
                ps.setString(1, username);
                ps.setString(2,password);
                ResultSet set = ps.executeQuery();
                Utente u = null;
                if (set.next()) {
                    u = new Utente();
                    u.setId(set.getInt("id"));
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setNazione(set.getString("nazione"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setData_nascita(set.getString("data_nascita"));
                    u.setAdmin(set.getBoolean("is_admin"));
                }
                set.close();
                return u;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>retrieveUtente</code> serve ad ottenere le informazioni relative ad un account utente
     * con un determinato id
     *
     * @param id id dell' account utente di cui si vogliono recuperare le informazioni
     * @return oggetto Utente contenente le informazioni relative all' account utente con l' id fornito
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public Utente retrieveUtente(int id){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.singoloUtente())) {
                ps.setInt(1, id);
                ResultSet set = ps.executeQuery();
                Utente u = new Utente();
                if (set.next()) {
                    u.setId(set.getInt("id"));
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setNazione(set.getString("nazione"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setData_nascita(set.getString("data_nascita"));
                    u.setAdmin(set.getBoolean("is_admin"));
                }
                set.close();
                return u;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>creaUtente</code> consente di salvare un oggetto Utente all' interno del database
     *
     * @param utente oggette Utente che si desidera salvare nel database
     * @return booleano che indica il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean creaUtente(Utente utente){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.inserisciUtente(),Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,utente.getEmail());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getUsername());
                ps.setString(5,utente.getPass());
                ps.setString(6,utente.getIndirizzo());
                ps.setString(7,utente.getNazione());
                ps.setString(8,utente.getCitta());
                ps.setInt(9,utente.getCap());
                ps.setString(10, utente.getData_nascita());
                ps.setBoolean(11,utente.is_admin());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                utente.setId(id);
                rs.close();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>aggiornaUtente</code> consente di aggiornare le informazioni relative ad un account utente
     * salvato all' interno del databse
     *
     * @param utente oggetto Utente con le informazioni aggiornate
     * @return booleano che indica il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean aggiornaUtente(Utente utente){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.aggiornaUtente())){
                ps.setString(1,utente.getEmail());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getUsername());
                ps.setString(5,utente.getIndirizzo());
                ps.setString(6,utente.getNazione());
                ps.setString(7,utente.getCitta());
                ps.setInt(8,utente.getCap());
                ps.setString(9, utente.getData_nascita());
                ps.setBoolean(10,utente.is_admin());
                ps.setString(11,utente.getPass());
                ps.setInt(12,utente.getId());
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>cancellaUtente</code> consente di rimuovere un oggetto Utente dal database
     *
     * @param id id dell' account utente di cui si desiderano cancellare le informazioni
     * @return booleano che indica il successo dell' operazione
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public boolean cancellaUtente(int id){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.eliminaUtente())){
                ps.setInt(1,id);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>countUsers</code> consente di contare quanti oggetti Utente sono salvati nel database
     *
     * @return intero che indica il numero di oggetti Utente salvati nel database
     * @throws RuntimeException  genera una RuntimeException con un messaggio e relativo ad errori SQL
     */
    public int countUsers (){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.contaUtenti())) {
                ResultSet resultSet = ps.executeQuery();
                int size = 0;
                while (resultSet.next()) {
                    if(!resultSet.getBoolean("is_admin")){
                        size++;
                    }
                }
                return size;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

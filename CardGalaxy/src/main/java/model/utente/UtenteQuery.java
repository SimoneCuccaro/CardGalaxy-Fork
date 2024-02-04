package model.utente;
import model.storage.TableQuery;

/** La classe <code>UtenteQuery</code> contiene i metodi rappresentanti
 * le query che vengono usate per la gestione degli utenti
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class UtenteQuery extends TableQuery{

    /** Costruttore dell' oggetto UtenteQuery
     *
     * @param table parametro che contiene il nome della tabella
     */
    protected UtenteQuery(String table) {
        super(table);
    }


    /** Il metodo <code>tuttiUtenti</code> contiene la query usata per
     * ottenere tutti gli oggetti Utente salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String tuttiUtenti() {
        return String.format("SELECT * FROM %s;",this.table);
    }


    /** Il metodo <code>singoloUtenteConPass</code> contiene la query usata per
     * ottenere un oggetto utente salvato nel database con determinati password e username
     *
     * @return  stringa contenente la query
     */
    public String singoloUtenteConPass(){
        return String.format("SELECT * FROM %s WHERE username = ? AND pass = ?;", this.table);
    }


    /** Il metodo <code>singoloUtente</code> contiene la query usata per
     * ottenere i dati relativi ad un singolo utente specificato tramite il suo id
     *
     * @return  stringa contenente la query
     */
    public String singoloUtente(){
        return String.format("SELECT * FROM %s WHERE id = ?;", this.table);
    }


    /** Il metodo <code>inserisciUtente</code> contiene la query usata per
     * inserire un oggetto Utente nel database
     *
     * @return  stringa contenente la query
     */
    public String inserisciUtente(){
        return String.format("INSERT INTO %s (email, nome, cognome, username, pass, indirizzo, nazione , citta, cap, data_nascita, is_admin) VALUES (?,?,?,?,?,?,?,?,?,?,?);", this.table);
    }


    /** Il metodo <code>aggiornaUtente</code> contiene la query usata per
     * aggiornare i dati di un oggetto Utente salvato nel database
     *
     * @return  stringa contenente la query
     */
    public String aggiornaUtente(){
        return String.format("UPDATE %s SET email = ?,nome = ?, cognome = ?, username= ?, indirizzo = ?,nazione = ?,citta = ?,cap = ?, data_nascita = ?, is_admin = ?, pass = ? WHERE id = ?;", this.table);
    }


    /** Il metodo <code>eliminaUtente</code> contiene la query usata per
     * eliminare un oggetto Utente salvato nel database
     *
     * @return  stringa contenente la query
     */
    public String eliminaUtente(){
        return String.format("DELETE FROM %s WHERE id=?;", this.table);
    }


    /** Il metodo <code>contaUtenti</code> contiene la query usata per
     * contare tutti gli oggetti Utente salvati nel database
     *
     * @return  stringa contenente la query
     */
    public String contaUtenti(){ return String.format("SELECT * FROM %s",this.table);}

    /** Il metodo <code>contaUtenti</code> contiene la query usata per
     * controllare se esiste già un oggetto Utente salvato nel database con uno specifico username
     *
     * @return  stringa contenente la query
     */
    public String checkUsername(){ return String.format("SELECT * FROM %s  order by username desc",this.table);}

    /** Il metodo <code>contaUtenti</code> contiene la query usata per
     * controllare se esiste già un oggetto Utente salvato nel database con una specifica email
     *
     * @return  stringa contenente la query
     */
    public String checkEmail(){ return String.format("SELECT * FROM %s order by email desc;",this.table);}


}




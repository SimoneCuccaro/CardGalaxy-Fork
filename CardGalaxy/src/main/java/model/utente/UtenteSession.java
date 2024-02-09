package model.utente;

/** La classe <code>UtenteSession</code> viene usata per gestire i dati di un utente
 * nello scope di sessione
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class UtenteSession {
    private final int id;
    private final boolean isAdmin;
    private final String nome,cognome,username,email;


    /**Costruttore dell' oggetto UtenteSession
     *
     * @param utente oggetto Utente da cui ricavare i dati per l' oggetto UtenteSession
     */
    public UtenteSession(Utente utente) {
        this.id = utente.getId();
        this.nome = utente.getNome();
        this.cognome = utente.getCognome();
        this.email=utente.getEmail();
        this.username=utente.getUsername();
        this.isAdmin = utente.is_admin();
    }

    /**Il metodo <code>getId</code> consente di ottenere l' id dell' utente di sessione
     *
     * @return stringa contenente l' id dell' utente di sessione
     */
    public int getId() {
        return id;
    }

    /**Il metodo <code>isAdmin</code> consente di verificare se l' utente di sessione è un admin
     *
     * @return booleano che indica se l' utente di sessione è un admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**Il metodo <code>getNome</code> consente di ottenere il nome dell' utente di sessione
     *
     * @return stringa contenente il nome dell' utente di sessione
     */
    public String getNome() {
        return nome;
    }

    /**Il metodo <code>getEmail</code> consente di ottenere l' email dell' utente di sessione
     *
     * @return stringa contenente l' email dell' utente di sessione
     */
    public String getEmail(){ return email;}

    /**Il metodo <code>getCognome</code> consente di ottenere il cognome dell' utente di sessione
     *
     * @return stringa contenente il cognome dell' utente di sessione
     */
    public String getCognome() {
        return cognome;
    }

    /**Il metodo <code>getUsername</code> consente di ottenere l' username dell' utente di sessione
     *
     * @return stringa contenente l' username dell' utente di sessione
     */
    public String getUsername() {
        return username;
    }
}

package model.utente;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Un oggetto <code>Utente</code> rappresenta un account di utente che ha effettuato il login nel sistema,
 * comprende un id univoco, una email,una password e vari dati personali inerenti all' utente.
 * Comprende anche un campo booleano isAdmin che sta ad indicare se questo account ha i permessi o meno di admin di sistema
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Utente
{
    private int id;
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private String pass;
    private String indirizzo;
    private String nazione;
    private String citta;
    private int cap;
    private String data_nascita;
    private boolean is_admin;


    /**Costruttore dell' oggetto Utente
     *
     * @param id id dell' account
     * @param email email associata all' account utente
     * @param nome nome associata all' account utente
     * @param cognome cognome associata all' account utente
     * @param username username associato all' account utente
     * @param pass password associata all' account utente
     * @param indirizzo indirizzo associato all' account utente
     * @param nazione nazione associata all' account utente
     * @param citta città associata all' account utente
     * @param cap codice postale associato all' account utente
     * @param data_nascita data di nascita dell' utente
     * @param is_admin booleano che indica se l' utente ha i permessi di admin
     */
    public Utente(int id, String email, String nome, String cognome, String username, String pass, String indirizzo,String nazione,String citta,int cap,String data_nascita,boolean is_admin) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.pass = pass;
        this.indirizzo=indirizzo;
        this.nazione=nazione;
        this.citta=citta;
        this.cap=cap;
        this.data_nascita = data_nascita;
        this.is_admin=is_admin;
    }

    /**Costruttore nullo dell' oggetto Utente
     *
     */
    public  Utente(){}

    /**Il metodo <code>getId</code> consente di ottenere
     * l' id dell' account utente
     * @return id univoco dell' account utente
     */
    public int getId() {
        return id;
    }

    /**Il metodo <code>setId</code> consente di impostare
     * l' id dell' account utente
     * @param id id univoco dell' account utente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Il metodo <code>getEmail</code> consente di ottenere
     * l' email dell' account utente
     * @return stringa contenente l'email dell' account
     */
    public String getEmail() {
        return email;
    }

    /**Il metodo <code>setEmail</code> consente di impostare
     * l' email dell' account utente
     * @param email stringa contente l' email dell' account
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**Il metodo <code>getNome</code> consente di ottenere
     * il nome associato all' account utente
     * @return stringa contenente il nome associato all' account utente
     */
    public String getNome() {
        return nome;
    }

    /**Il metodo <code>setNome</code> consente di impostare
     * il nome associato all' account utente
     * @param nome nome da associare all' account utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Il metodo <code>getCognome</code> consente di ottenere
     * il cognome associato all' account utente
     * @return stringa contenente il cognome associato all' account utente
     */
    public String getCognome() {
        return cognome;
    }

    /**Il metodo <code>setCognome</code> consente di impostare
     * il cognome associato all' account utente
     * @param cognome cognome da associare all' account utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**Il metodo <code>getUsername</code> consente di ottenere
     * l' username dell' account utente
     * @return stringa contenente l' username associato all' account utente
     */
    public String getUsername() {
        return username;
    }

    /**Il metodo <code>setUsername</code> consente di impostare
     * l' username dell' account utente
     * @param username username da associare all' account utente
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**Il metodo <code>getPass</code> consente di ottenere
     * la password dell' account utente
     * @return stringa contenente la password associata all' account utente
     */
    public String getPass() {
        return pass;
    }

    /**Il metodo <code>setPass</code> consente di impostare
     * la password dell' account utente e decifrarla attraverso un opportuno algoritmo(SHA-1)
     * @param pass password da associare all' account utente
     */
    public void setPass(String pass) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes(StandardCharsets.UTF_8));
            this.pass= String.format("%040x", new
                    BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**Il metodo <code>getIndirizzo</code> consente di ottenere
     * l' indirizzo associato all' account utente
     * @return stringa contenente l' indirizzo associato all' account utente
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**Il metodo <code>setIndirizzo</code> consente di impostare
     * l' indirizzo associato all' account utente
     * @param indirizzo indirizzo da associare all' account utente
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**Il metodo <code>getNazione</code> consente di ottenere
     * la nazione associata all' account utente
     * @return stringa contenente la nazione associata all' account utente
     */
    public String getNazione() {return nazione;}

    /**Il metodo <code>setNazione</code> consente di impostare
     * la nazione associata all' account utente
     * @param nazione nazione da associare all' account utente
     */
    public void setNazione(String nazione) {this.nazione = nazione;}

    /**Il metodo <code>getCitta</code> consente di ottenere
     * la città associata all' account utente
     * @return stringa contenente la città associata all' account utente
     */
    public String getCitta() {return citta;}

    /**Il metodo <code>setCitta</code> consente di impostare
     * la città associata all' account utente
     * @param citta città da associare all' account utente
     */
    public void setCitta(String citta) {this.citta = citta;}

    /**Il metodo <code>getCap</code> consente di ottenere
     * il codice postale associato all' account utente
     * @return intero contenente il codice postale dell' account utente
     */
    public int getCap() {return cap;}

    /**Il metodo <code>setCap</code> consente di impostare
     * il codice postale associato all' account utente
     * @param cap codice postale da associare all' account utente
     */
    public void setCap(int cap) {this.cap = cap;}

    /**Il metodo <code>getData_nascita</code> consente di ottenere
     * la data di nascita associata all' account utente
     * @return stringa contenente la data di nascita associata all' account utente
     */
    public String getData_nascita() {
        return data_nascita;
    }

    /**Il metodo <code>setData_nascita</code> consente di impostare
     * la data di nascita associata all' account utente
     * @param data_nascita data di nascita associata all' account utente
     */
    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
    }

    /**Il metodo <code>is_admin</code> consente di verificare
     * se l' account ha i permessi di admin
     * @return booleano che verifica se un account utente è admin(=1) o no(=0)
     */
    public boolean is_admin() {
        return is_admin;
    }

    /**Il metodo <code>setAdmin</code> consente di impostare
     * i permessi di admin per l' account utente
     * @param admin booleano che assume valore 1 se si vogliono concere i permessi di admin all' account utente, 0 altrimenti
     */
    public void setAdmin(boolean admin) {
        is_admin = admin;
    }
}

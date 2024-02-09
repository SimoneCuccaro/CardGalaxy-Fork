package model.prodotto;

/** Un oggetto <code>Giftcard</code> rappresenta un prodotto presente nel sistema,
 * contiene l' id univoco del prodotto, il suo nome, la sua piattaforma di riferimento,
 * la sua descrizione, il suo prezzo, una stringa contenente il nome del file della sua foto
 * e un booleano che indica se è disponibile o meno
 *
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class GiftCard {
    private int id_prodotto;
    private String nome;
    private String piattaforma;
    private String descrizione;
    private double prezzo;
    private String foto;
    private boolean isAvailable;

    /**Costruttore dell' oggetto GiftCard
     *
     * @param id_prodotto id univoco dell' oggetto GiftCard
     * @param nome stringa contenente il nome dell' oggetto GiftCard
     * @param piattaforma stringa contenente la piattaforma di riferimento dell' oggetto GiftCard
     * @param descrizione stringa contenente la descrizione dell' oggetto GiftCard
     * @param prezzo prezzo espresso in valore double dell' oggetto GiftCard
     * @param foto stringa contenente il nome del file della foto dell' oggetto GiftCard
     * @param isAvailable booleano che indica la visibilità dell' oggetto GiftCard
     */
    public GiftCard(int id_prodotto, String nome, String piattaforma, String descrizione, double prezzo,String foto,boolean isAvailable) {
        this.id_prodotto = id_prodotto;
        this.nome = nome;
        this.piattaforma = piattaforma;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.foto=foto;
        this.isAvailable=isAvailable;
    }

    /**Costruttore nullo dell' oggetto GiftCard
     *
     */
    public GiftCard() {}

    /**Il metodo <code>getId_prodotto</code> consente di ottenere
     * l' id dell' oggetto GiftCard
     * @return id univoco dell' oggetto GiftCard
     */
    public int getId_prodotto() {
        return id_prodotto;
    }

    /**Il metodo <code>setId_prodotto</code> consente di impostare
     * l' id dell' oggetto GiftCard
     * @param id_prodotto id univoco dell' oggetto GiftCard
     */
    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    /**Il metodo <code>getNome</code> consente di ottenere
     * il nome associato all' oggetto GiftCard
     * @return stringa contenente il nome associato all' oggetto GiftCard
     */
    public String getNome() {
        return nome;
    }

    /**Il metodo <code>setNome</code> consente di impostare
     * il nome associato all' oggetto GiftCard
     *
     * @param nome stringa contenente il nome da impostare all' oggetto GiftCard
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Il metodo <code>getPiattaforma</code> consente di ottenere
     * la piattaforma associata all' oggetto GiftCard
     * @return stringa contenente la piattaforma associata all' oggetto GiftCard
     */
    public String getPiattaforma() {
        return piattaforma;
    }

    /**Il metodo <code>setPiattaforma</code> consente di impostare
     * la piattaforma associata all' oggetto GiftCard
     *
     * @param piattaforma stringa contenente la piattaforma da impostare all' oggetto GiftCard
     */
    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    /**Il metodo <code>getDescrizione</code> consente di ottenere
     * la descrizione associata all' oggetto GiftCard
     * @return stringa contenente la descrizione associata all' oggetto GiftCard
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**Il metodo <code>setDescrizione</code> consente di impostare
     * la descrizione associata all' oggetto GiftCard
     *
     * @param descrizione stringa contenente la descrizione da impostare all' oggetto GiftCard
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**Il metodo <code>getPrezzo</code> consente di ottenere
     * il prezzo associato all' oggetto GiftCard
     * @return valore double contenente il prezzo associato all' oggetto GiftCard
     */
    public double getPrezzo() {
        return prezzo;
    }

    /**Il metodo <code>setPrezzo</code> consente di impostare
     * il prezzo associato all' oggetto GiftCard
     *
     * @param prezzo valore double contenente il prezzo da impostare all' oggetto GiftCard
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**Il metodo <code>getFoto</code> consente di ottenere
     * il nome del file della foto associato all' oggetto GiftCard
     *
     * @return stringa contenente il nome del file della foto associato all' oggetto GiftCard
     */
    public String getFoto() {
        return foto;
    }

    /**Il metodo <code>getFoto</code> consente di impostare
     * il nome del file della foto associato all' oggetto GiftCard
     *
     * @param foto stringa contenente il nome del file della foto da impostare all' oggetto GiftCard
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**Il metodo <code>checkisAvailable</code> consente di verificare se l' oggetto GiftCard
     * è visibile o meno
     *
     * @return booleano che indica se l' oggetto GiftCard è visibile o meno
     */
    public boolean checkisAvailable() {return isAvailable;}

    /**Il metodo <code>setAvailable</code> consente di impostare la visibilità dell' oggetto GiftCard
     *
     * @param available booleano che indica la visibilità dell' oggetto GiftCard
     */
    public void setAvailable(boolean available) {this.isAvailable = available;}
}

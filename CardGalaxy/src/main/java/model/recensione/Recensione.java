package model.recensione;
import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.Date;

public class Recensione
{
    private int id_utente;
    private int id_prodotto;
    private String datarecensione;
    private String testo;

    public Recensione(int id_utente,int id_prodotto,String datarecensione,String testo){
        this.id_utente=id_utente;
        this.id_prodotto=id_prodotto;
        this.datarecensione=datarecensione;
        this.testo=testo;
    }
    public Recensione() {}

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public String getDatarecensione() {
        return datarecensione;
    }

    public void setDatarecensione(String datarecensione) {
        this.datarecensione = datarecensione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}

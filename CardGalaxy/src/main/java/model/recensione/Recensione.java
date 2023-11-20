package model.recensione;
import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.Date;

public class Recensione
{
    private int idutente;
    private int codiceprodotto;
    private String datarecensione;
    private String testo;

    public Recensione(int idutente,int codiceprodotto,String datarecensione,String testo){
        this.idutente=idutente;
        this.codiceprodotto=codiceprodotto;
        this.datarecensione=datarecensione;
        this.testo=testo;
    }
    public Recensione() {}

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    public int getCodiceprodotto() {
        return codiceprodotto;
    }

    public void setCodiceprodotto(int codiceprodotto) {
        this.codiceprodotto = codiceprodotto;
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

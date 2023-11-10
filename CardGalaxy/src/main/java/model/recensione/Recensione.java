package model.recensione;
import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.Date;

public class Recensione
{
    private Utente utente;
    private GiftCard prodotto;
    private Date dataRecensione;
    private String testo;

    public Recensione() {}

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public GiftCard getProdotto() {
        return prodotto;
    }

    public void setProdotto(GiftCard prodotto) {
        this.prodotto = prodotto;
    }

    public Date getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(Date dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}

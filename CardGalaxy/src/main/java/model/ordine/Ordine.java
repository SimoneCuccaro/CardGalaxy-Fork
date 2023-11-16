package model.ordine;
import model.prodotto.GiftCard;

import java.util.Date;
import java.util.Hashtable;

public class Ordine {
    private int codice;
    private double prezzototale;
    private Date dataacquisto;
    private int idutente;

    private Hashtable<GiftCard,Integer> prodottoList;

    public Ordine(int codice, double prezzototale, Date dataacquisto, int idutente)
    {
        this.codice = codice;
        this.prezzototale = prezzototale;
        this.dataacquisto = dataacquisto;
        this.idutente = idutente;
    }

    public Ordine()
    {
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public double getPrezzototale() {
        return prezzototale;
    }

    public void setPrezzototale(double prezzototale) {
        this.prezzototale = prezzototale;
    }

    public Date getDataacquisto() {
        return dataacquisto;
    }

    public void setDataacquisto(Date dataAcquisto) {
        this.dataacquisto = dataacquisto;
    }

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    public Hashtable<GiftCard, Integer> getProdottoList() {
        return prodottoList;
    }

    public void setProdottoList(Hashtable<GiftCard, Integer> prodottoList) {
        this.prodottoList = prodottoList;
    }
}

package model.ordine;
import model.prodotto.GiftCard;


import java.util.Hashtable;

public class Ordine {
    private int id;
    private double prezzo_totale;
    private String data_acquisto;
    private int id_utente;

    private Hashtable<GiftCard,Integer> prodottoList;

    public Ordine(int id, double prezzo_totale, String data_acquisto, int id_utente)
    {
        this.id = id;
        this.prezzo_totale = prezzo_totale;
        this.data_acquisto = data_acquisto;
        this.id_utente = id_utente;
    }

    public Ordine()
    {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public double getPrezzo_totale() {return prezzo_totale;}

    public void setPrezzo_totale(double prezzo_totale) {this.prezzo_totale = prezzo_totale;}

    public String getData_acquisto() {return data_acquisto;}

    public void setData_acquisto(String data_acquisto) {this.data_acquisto = data_acquisto;}

    public int getId_utente() {return id_utente;}

    public void setId_utente(int id_utente) {this.id_utente = id_utente;}

    public Hashtable<GiftCard, Integer> getProdottoList() {
        return prodottoList;
    }

    public void setProdottoList(Hashtable<GiftCard, Integer> prodottoList) {
        this.prodottoList = prodottoList;
    }
}

package model.carrello;

import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.ArrayList;

public class Carrello
{
    private Utente utente;
    private ArrayList<GiftCard> prodotto;

    public Carrello(Utente utente, ArrayList<GiftCard> prodotto)
    {
        this.utente = utente;
        this.prodotto = prodotto;
    }

    public Carrello() {}

    public Utente getUtente()
    {
        return utente;
    }

    public void setUtente(Utente utente)
    {
        this.utente = utente;
    }

    public ArrayList<GiftCard> getProdotto()
    {
        return prodotto;
    }

    public void setProdotto(ArrayList<GiftCard> prodotto)
    {
        this.prodotto = prodotto;
    }
}

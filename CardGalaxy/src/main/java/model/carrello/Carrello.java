package model.carrello;

import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.ArrayList;

public class Carrello
{
    private int id_utente;
    private int id_prodotto;
    private int quantita;

    public Carrello(int id_utente,int id_prodotto,int quantita)
    {
        this.id_utente = id_utente;
        this.id_prodotto = id_prodotto;
        this.quantita=quantita;
    }

    public Carrello() {}

    public int getId_utente() {return id_utente;}

    public void setId_utente(int id_utente) {this.id_utente = id_utente;}

    public int getId_prodotto() {return id_prodotto;}

    public void setId_prodotto(int id_prodotto) {this.id_prodotto = id_prodotto;}

    public int getQuantita() {return quantita;}

    public void setQuantita(int quantita) {this.quantita = quantita;}
}

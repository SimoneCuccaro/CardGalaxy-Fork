package model.carrello;

import model.utente.Utente;
import model.prodotto.GiftCard;

import java.util.ArrayList;

public class Carrello
{
    private int idutente;
    private int codiceordine;

    public Carrello(int idutente,int codiceordine)
    {
        this.idutente = idutente;
        this.codiceordine = codiceordine;
    }

    public Carrello() {}

    public int getIdutente()
    {
        return idutente;
    }
    public void setIdutente(int idutente)
    {
        this.idutente = idutente;
    }
    public int getCodiceordine(){return codiceordine;}
    public void setCodiceordine(int codiceordine){this.codiceordine=codiceordine;}

}

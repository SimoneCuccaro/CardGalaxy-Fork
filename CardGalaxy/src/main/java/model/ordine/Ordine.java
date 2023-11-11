package model.ordine;
import model.utente.Utente;
import java.util.Date;

public class Ordine {
    private int codice;
    private double prezzoTotale;
    private Date dataAcquisto;
    private int utente;

    public Ordine(int codice, double prezzoTotale, Date dataAcquisto, int utente)
    {
        this.codice = codice;
        this.prezzoTotale = prezzoTotale;
        this.dataAcquisto = dataAcquisto;
        this.utente = utente;
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

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public int getUtente() {
        return utente;
    }

    public void setUtente(int utente) {
        this.utente = utente;
    }
}

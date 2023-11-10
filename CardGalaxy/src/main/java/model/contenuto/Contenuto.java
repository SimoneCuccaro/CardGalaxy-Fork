package model.contenuto;
import model.prodotto.GiftCard;
import model.ordine.Ordine;
public class Contenuto
{
    private GiftCard prodotto;
    private Ordine ordine;
    private  int quantità;

    public Contenuto(GiftCard prodotto, Ordine ordine, int quantità)
    {
        this.prodotto = prodotto;
        this.ordine = ordine;
        this.quantità = quantità;
    }

    public GiftCard getProdotto() {
        return prodotto;
    }

    public void setProdotto(GiftCard prodotto) {
        this.prodotto = prodotto;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }
}

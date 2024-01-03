package model.carrello;

import model.prodotto.GiftCard;

public class CartItems {

    private GiftCard giftCard;
    private int quantita;

    public CartItems(GiftCard giftCard, int quantita) {
        this.giftCard = giftCard;
        this.quantita = quantita;
    }

    public GiftCard getGiftCard() {
        return giftCard;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setGiftCard(GiftCard giftCard) {
        this.giftCard = giftCard;
    }

    public double totale() {
        return giftCard.getPrezzo() * quantita;
    }


}

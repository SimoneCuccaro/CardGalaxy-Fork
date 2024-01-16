package model.carrello;

import model.prodotto.GiftCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CarrelloSession {
    private List<CartItems> items;

    public CarrelloSession() {
    }
    public CarrelloSession(List<CartItems> items) {
        this.items = items;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }

    public int quantita() {
        int quantita = 0;
        for (CartItems item : items) {
            quantita += item.getQuantita();
        }
        return quantita;
    }

    public double totale() {
        double totale = 0.0;
        for (CartItems item : items) {
            totale += item.totale();
        }
        BigDecimal bd=new BigDecimal(totale).setScale(2, RoundingMode.HALF_UP);
        double tot=bd.doubleValue();
        return tot;
    }

    public boolean addProduct(GiftCard giftCard, int quantita) {
        for (CartItems product : items) {
            if (product.getGiftCard().getId_prodotto()== giftCard.getId_prodotto()) {
                product.setQuantita(product.getQuantita()+1);
                return true;
                }
            }
        return items.add(new CartItems(giftCard, quantita));
    }



    public boolean removeProduct(int id) {
        for (CartItems product : items) {
            if (product.getGiftCard().getId_prodotto() == id) {
                return items.remove(product);
            }
        }
        return false;
    }

    public void resetCart(){
        items.clear();
    }
}


package model.prodotto;

public class GiftCard {
    private int idProdotto;
    private String nome;
    private String piattaforma;
    private String descrizione;
    private double prezzo;

    public GiftCard(int idProdotto, String nome, String piattaforma, String descrizione, double prezzo) {
        this.idProdotto = idProdotto;
        this.nome = nome;
        this.piattaforma = piattaforma;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public GiftCard() {}

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}

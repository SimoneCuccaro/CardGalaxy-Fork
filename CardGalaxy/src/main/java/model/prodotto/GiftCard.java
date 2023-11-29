package model.prodotto;

public class GiftCard {
    private int id_prodotto;
    private String nome;
    private String piattaforma;
    private String descrizione;
    private double prezzo;
    private String foto;

    public GiftCard(int id_prodotto, String nome, String piattaforma, String descrizione, double prezzo,String foto) {
        this.id_prodotto = id_prodotto;
        this.nome = nome;
        this.piattaforma = piattaforma;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.foto=foto;
    }

    public GiftCard() {}

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

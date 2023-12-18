package model.prodotto;


import model.storage.TableQuery;

    public class GiftCardQuery extends TableQuery {

        protected GiftCardQuery(String table) {
            super(table);
        }

        public String tutteGiftCard() { return String.format("SELECT * FROM %s;",this.table);}

        public String retrieveGiftCardByID() { return String.format("SELECT * FROM %s WHERE id_prodotto=?;",this.table);}

        public String inserisciGiftCard() { return String.format("INSERT INTO %s (nome , piattaforma , descrizione , prezzo, foto, isAvailable) VALUES (?, ?, ?, ?, ?, ?);", this.table);}

        public String aggiornaGiftCard() { return String.format("UPDATE %s SET nome = ?, piattaforma = ?, descrizione = ?, prezzo = ?, foto = ?, isAvailable = ? WHERE id_prodotto = ?;", this.table);}

        public String rimuoviGiftCard() {return String.format("UPDATE %s SET isAvailable = false WHERE id_prodotto = ?;",this.table);}

        public String countAllGiftCard() { return String.format("SELECT count(*) AS giftCardTotali FROM %s WHERE isAvailable=1;",this.table);}

        public String addToCart(){return String.format("INSERT INTO carrello (id_utente, id_prodotto, quantita) VALUES (?,?,?);");}

        public String removeFromCart(){return String.format("DELETE FROM carrello WHERE id_utente = ? AND id_prodotto =?");}

    }


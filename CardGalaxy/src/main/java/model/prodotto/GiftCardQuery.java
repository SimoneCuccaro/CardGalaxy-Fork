package model.prodotto;


import model.storage.TableQuery;

    public class GiftCardQuery extends TableQuery {

        protected GiftCardQuery(String table) {
            super(table);
        }

        public String tutteGiftCard() { return String.format("SELECT * FROM %s ;",this.table);}

        public String retrieveGiftCardByID() { return String.format("SELECT * FROM %s WHERE id=?;",this.table);}

        public String inserisciGiftCard() { return String.format("INSERT INTO %s (nome , piattaforma , descrizione , prezzo, foto) VALUES (?, ?, ?, ?, ?);", this.table);}

        public String aggiornaGiftCard() { return String.format("UPDATE %s SET nome = ?, piattaforma = ?, descrizione = ?, prezzo = ?, foto = ?,  WHERE id = ?;", this.table);}

        public String rimuoviGiftCard() {return String.format("DELETE FROM %s WHERE id = ?;",this.table);}

        public String countAllGiftCard() { return String.format("SELECT count(*) AS giftCardTotali FROM %s;",this.table);}

        //public String addToCart();

        //public String removeFromCart();

    }


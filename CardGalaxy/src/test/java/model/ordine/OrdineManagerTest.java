package model.ordine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockStatic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.carrello.CartItems;
import model.prodotto.GiftCard;
import model.storage.Manager;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class OrdineManagerTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    private OrdineManager ordineManager;

    @Before
    public void  setup() throws SQLException {

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }


        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);


        ordineManager=new OrdineManager();
        //qui i dao

        when(Manager.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString(),anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
    }

    @Test
    public void  retrieveOrdini() throws SQLException {
        when(mockResultSet.next()).thenReturn(true,false);
        when(mockResultSet.getInt("id")).thenReturn(2);
        when(mockResultSet.getDouble("prezzo_totale")).thenReturn(50.00);
        when(mockResultSet.getString("data_acquisto")).thenReturn("2024/02/01");
        when(mockResultSet.getInt("id_utente")).thenReturn(5);

        List<Ordine> result = ordineManager.retrieveOrdini();
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getId());
        assertEquals(50.00, result.get(0).getPrezzo_totale(),0.01);
        assertEquals("2024/02/01", result.get(0).getData_acquisto());
        assertEquals(5, result.get(0).getId_utente());
    }

    @Test
    public void retrieveOrdiniByUtente() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getDouble("prezzo_totale")).thenReturn(100.0, 200.0);
        when(mockResultSet.getString("data_acquisto")).thenReturn("2024-02-07", "2024-02-08");
        when(mockResultSet.getInt("id_utente")).thenReturn(1);

        ArrayList<Ordine> result = ordineManager.retrieveOrdiniByUtente(1);


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(100.0, result.get(0).getPrezzo_totale(), 0.01);
        assertEquals("2024-02-07", result.get(0).getData_acquisto());
        assertEquals(1, result.get(0).getId_utente());
        assertEquals(2, result.get(1).getId());
        assertEquals(200.0, result.get(1).getPrezzo_totale(), 0.01);
        assertEquals("2024-02-08", result.get(1).getData_acquisto());
        assertEquals(1, result.get(1).getId_utente());
    }

    @Test
    public void retrieveOrdineById() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getDouble("prezzo_totale")).thenReturn(100.0, 200.0);
        when(mockResultSet.getString("data_acquisto")).thenReturn("2024-02-07", "2024-02-08");
        when(mockResultSet.getInt("id_utente")).thenReturn(1);

        Ordine result = ordineManager.retrieveOrdineById(2);


        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(100.0, result.getPrezzo_totale(), 0.01);
        assertEquals("2024-02-07", result.getData_acquisto());
        assertEquals(1, result.getId_utente());
    }

    @Test
    public void contaOrdini() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("ordiniTotali")).thenReturn(3);


        int result = ordineManager.contaOrdini();


        assertEquals(3, result);
    }

    @Test
    public void creaOrdine() throws SQLException {
        Ordine ordine = new Ordine();
        ordine.setPrezzo_totale(100.0);
        ordine.setData_acquisto("2024-02-07");
        ordine.setId_utente(1);


        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        Boolean result = ordineManager.creaOrdine(ordine);


        assertTrue(result);

    }

    @Test
    public void lastOrder() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("lastorder")).thenReturn(3);


        int result = ordineManager.lastOrder();


        assertEquals(3, result);
    }

    @Test
    public void retriveProdotti() throws SQLException {
        Hashtable<GiftCard, Integer> expectedProdotti = new Hashtable<>();
        GiftCard giftCard1 = new GiftCard();
        giftCard1.setId_prodotto(1);
        giftCard1.setNome("Gift Card 1");
        giftCard1.setPiattaforma("Platform 1");
        giftCard1.setDescrizione("Description 1");
        giftCard1.setPrezzo(50.0);
        giftCard1.setFoto("photo1.jpg");
        giftCard1.setAvailable(true);
        expectedProdotti.put(giftCard1, 2);


        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_prodotto")).thenReturn(1);
        when(mockResultSet.getString("nome")).thenReturn("Gift Card 1");
        when(mockResultSet.getString("piattaforma")).thenReturn("Platform 1");
        when(mockResultSet.getString("descrizione")).thenReturn("Description 1");
        when(mockResultSet.getDouble("prezzo")).thenReturn(50.0);
        when(mockResultSet.getString("foto")).thenReturn("photo1.jpg");
        when(mockResultSet.getBoolean("isAvailable")).thenReturn(true);
        when(mockResultSet.getInt("quantita")).thenReturn(2);

        Hashtable<GiftCard, Integer> result = ordineManager.retriveProdotti(123);

        assertNotNull(result);
        assertEquals(expectedProdotti.size(), result.size());
    }

    @Test
    public void getGuadagno() throws SQLException {
        double expectedGuadagno = 1000.0;
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getDouble(1)).thenReturn(expectedGuadagno);


        double result =ordineManager.getGuadagno();


        assertEquals(expectedGuadagno, result, 0.01);
    }

    @Test
    public void updateContenuto() throws SQLException {
        int quantity = 5;
        int idOrder = 1001;
        int idProduct = 2001;


        boolean result = ordineManager.updateContenuto(quantity, idOrder, idProduct);


        assertTrue(result);
    }

    @Test
    public void updateOrder() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        boolean result = ordineManager.updateOrder(30.00);


        Assertions.assertEquals(true, result);
    }

    @Test
    public void removeOrder() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        boolean result = ordineManager.removeOrder(99);


        Assertions.assertTrue(result);
    }

    @Test
    public void testSaveContenuto() throws SQLException {
        List<CartItems> cartItems=new ArrayList<>();
        GiftCard giftCard = new GiftCard();
        giftCard.setId_prodotto(99);
        giftCard.setNome("Test prod");
        giftCard.setPiattaforma("Test piatt");
        giftCard.setDescrizione("Test desc");
        giftCard.setPrezzo(19.99);
        giftCard.setFoto("Url of the photo");
        giftCard.setAvailable(true);
        CartItems cartItems1=new CartItems(giftCard,1);
        cartItems.add(cartItems1);
        Ordine ordine=new Ordine();
        ordine.setId(1);


        boolean result = ordineManager.saveContenuto(ordine,cartItems);


        assertTrue(result);
    }
}
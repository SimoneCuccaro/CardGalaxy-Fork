package model.prodotto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import model.storage.Manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;

public class GiftCardManagerTest {

    private GiftCardManager giftCardManager;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }


        giftCardManager = new GiftCardManager();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(Manager.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString(),anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
    }

    @Test
    public void testRetrieveTutteGiftCard() throws SQLException {

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id_prodotto")).thenReturn(99);
        when(mockResultSet.getString("nome")).thenReturn("Test prod");
        when(mockResultSet.getString("piattaforma")).thenReturn("Test piatt");
        when(mockResultSet.getString("descrizione")).thenReturn("Test desc");
        when(mockResultSet.getDouble("prezzo")).thenReturn(19.99);
        when(mockResultSet.getString("foto")).thenReturn("Url of the photo");
        when(mockResultSet.getBoolean("isAvailable")).thenReturn(true);


        ArrayList<GiftCard> result = giftCardManager.retrieveTutteGiftCard();


        assertEquals(1, result.size());
        assertEquals(99, result.get(0).getId_prodotto());
        assertEquals("Test prod", result.get(0).getNome());
        assertEquals("Test piatt", result.get(0).getPiattaforma());
        assertEquals("Test desc", result.get(0).getDescrizione());
        assertEquals(19.99, result.get(0).getPrezzo());
        assertEquals("Url of the photo", result.get(0).getFoto());
        assertTrue(result.get(0).checkisAvailable());
    }

    @Test
    public void testRetrieveGiftCardByID() throws SQLException {

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id_prodotto")).thenReturn(99);
        when(mockResultSet.getString("nome")).thenReturn("Test prod");
        when(mockResultSet.getString("piattaforma")).thenReturn("Test piatt");
        when(mockResultSet.getString("descrizione")).thenReturn("Test desc");
        when(mockResultSet.getDouble("prezzo")).thenReturn(19.99);
        when(mockResultSet.getString("foto")).thenReturn("Url of the photo");
        when(mockResultSet.getBoolean("isAvailable")).thenReturn(true);


        GiftCard result = giftCardManager.retrieveGiftCardByID(99);


        assertNotNull(result);
        assertEquals(99, result.getId_prodotto());
        assertEquals("Test prod", result.getNome());
        assertEquals("Test piatt", result.getPiattaforma());
        assertEquals("Test desc", result.getDescrizione());
        assertEquals(19.99, result.getPrezzo());
        assertEquals("Url of the photo", result.getFoto());
        assertTrue(result.checkisAvailable());
    }

    @Test
    public void testInserisciGiftCard() throws Exception {

        GiftCard giftCard = new GiftCard();
        giftCard.setNome("Test prod");
        giftCard.setPiattaforma("Test piatt");
        giftCard.setDescrizione("Test desc");
        giftCard.setPrezzo(19.99);
        giftCard.setFoto("Url of the photo");
        giftCard.setAvailable(true);


        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        Boolean result=giftCardManager.inserisciGiftCard(giftCard);
        assertEquals(true,result);
    }

    @Test
    public void testAggiornaGiftCard() throws SQLException {

        GiftCard giftCard = new GiftCard();
        giftCard.setId_prodotto(99);
        giftCard.setNome("Test prod");
        giftCard.setPiattaforma("Test piatt");
        giftCard.setDescrizione("Test desc");
        giftCard.setPrezzo(19.99);
        giftCard.setFoto("Url of the photo");
        giftCard.setAvailable(true);


        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        boolean result = giftCardManager.aggiornaGiftCard(giftCard);


        assertTrue(result);
    }

    @Test
    public void testRimuoviGiftCard() throws SQLException {

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        boolean result = giftCardManager.rimuoviGiftCard(99);


        assertTrue(result);
    }

    @Test
    public void testCountAllGiftCard() throws SQLException {

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("giftCardTotali")).thenReturn(10);


        int result = giftCardManager.countAllGiftCard();


        assertEquals(10, result);
    }


}

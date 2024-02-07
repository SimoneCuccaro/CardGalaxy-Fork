package model.carrello;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import model.prodotto.GiftCard;
import model.prodotto.GiftCardManager;
import model.storage.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloManagerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    private CarrelloManager carrelloManager;



    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        carrelloManager = new CarrelloManager();

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }


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
    public void testRetrieveCarrelloByUtente() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id_prodotto")).thenReturn(99);
        when(mockResultSet.getInt("quantita")).thenReturn(2);

        GiftCard mockGiftCard = new GiftCard();
        mockGiftCard.setId_prodotto(99);
        GiftCardManager giftCardManager = mock(GiftCardManager.class);
        when(giftCardManager.retrieveGiftCardByID(anyInt())).thenReturn(mockGiftCard);


        List<CartItems> result = carrelloManager.retrieveCarrelloByUtente(1);


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(99, result.get(0).getGiftCard().getId_prodotto());
        assertEquals(2, result.get(0).getQuantita());
    }




    @Test
    public void testRimuoviCarrelloUtente() throws SQLException {

        boolean result = carrelloManager.rimuoviCarrelloUtente(1);


        assertTrue(result);

    }

    @Test
    public void testSaveToCart() throws SQLException {

        List<CartItems> prodotti = new ArrayList<>();
        prodotti.add(new CartItems(new GiftCard(), 1));
        prodotti.add(new CartItems(new GiftCard(), 2));


        boolean result = carrelloManager.saveToCart(1, prodotti);


        assertTrue(result);

    }

    @Test
    public void testRemoveFromCart() throws SQLException {

        boolean result = carrelloManager.removeFromCart(1, 1);


        assertTrue(result);

    }
}
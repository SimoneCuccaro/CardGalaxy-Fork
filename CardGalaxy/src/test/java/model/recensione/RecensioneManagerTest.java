package model.recensione;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import model.storage.Manager;

import model.utente.UtenteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;


class RecensioneManagerTest {

    private RecensioneManager recensioneManager;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }



        //utenteManager=mock(UtenteManager.class);
        recensioneManager=new RecensioneManager();

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
    public void retrieveRecensioni() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id_utente")).thenReturn(1, 2);
        when(mockResultSet.getInt("id_prodotto")).thenReturn(100);
        when(mockResultSet.getString("datarecensione")).thenReturn("2024-02-07");
        when(mockResultSet.getString("testo")).thenReturn("First review", "Second review");

        ArrayList<Recensione> result = recensioneManager.retrieveRecensioniByProdotto(100);


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId_utente());
        assertEquals(100, result.get(0).getId_prodotto());
        assertEquals("2024-02-07", result.get(0).getDatarecensione());
        assertEquals("First review", result.get(0).getTesto());
        assertEquals(2, result.get(1).getId_utente());
        assertEquals(100, result.get(1).getId_prodotto());
        assertEquals("2024-02-07", result.get(1).getDatarecensione());
        assertEquals("Second review", result.get(1).getTesto());
    }

    @Test
    public void retrieveRecensioniByProdotto() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id_utente")).thenReturn(1, 2);
        when(mockResultSet.getInt("id_prodotto")).thenReturn(100);
        when(mockResultSet.getString("datarecensione")).thenReturn("2024-02-07", "2024-02-08");
        when(mockResultSet.getString("testo")).thenReturn("First review", "Second review");

        // Call the method to be tested
        ArrayList<Recensione> result = recensioneManager.retrieveRecensioniByProdotto(100);

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId_utente());
        assertEquals(100, result.get(0).getId_prodotto());
        assertEquals("2024-02-07", result.get(0).getDatarecensione());
        assertEquals("First review", result.get(0).getTesto());
        assertEquals(2, result.get(1).getId_utente());
        assertEquals(100, result.get(1).getId_prodotto());
        assertEquals("2024-02-08", result.get(1).getDatarecensione());
        assertEquals("Second review", result.get(1).getTesto());
    }

    @Test
    public void inserisciRecensione() throws SQLException {
        Recensione recensione = new Recensione();
        recensione.setId_utente(1);
        recensione.setId_prodotto(100);
        recensione.setTesto("Test text");
        recensione.setDatarecensione("2024-02-07");


        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        boolean result = recensioneManager.inserisciRecensione(recensione);


        assertTrue(result);
    }

    @Test
    public void aggiornaRecensione() throws SQLException {
        Recensione recensione = new Recensione();
        recensione.setId_utente(1);
        recensione.setId_prodotto(100);
        recensione.setTesto("Updated test text");
        recensione.setDatarecensione("2024-02-07");

        // Call the method to be tested
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        boolean result = recensioneManager.aggiornaRecensione(recensione);


        assertEquals(true, result);
    }

    @Test
    public void rimuoviRecensione() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        boolean result = recensioneManager.rimuoviRecensione(99,99);


        assertTrue(result);
    }
}
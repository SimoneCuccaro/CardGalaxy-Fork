package model.richiestasupporto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import model.rispostasupporto.RispostaSupporto;
import model.storage.Manager;

import model.utente.UtenteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;

class RichiestaSupportoManagerTest {

    private RichiestaSupportoManager richiestaSupportoManager;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }



        //utenteManager=mock(UtenteManager.class);
        richiestaSupportoManager=new RichiestaSupportoManager();

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
    public void retrieveAllRequest() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id_richiesta")).thenReturn(1, 2);
        when(mockResultSet.getString("richiesta")).thenReturn("First Request", "Second Request");
        when(mockResultSet.getString("oggetto_richiesta")).thenReturn("First Request", "Second Request");
        when(mockResultSet.getInt("id_utente")).thenReturn(100, 101);

        // Call the method to be tested
        ArrayList<RichiestaSupporto> result = richiestaSupportoManager.retrieveAllRequest();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId_richiesta());
        assertEquals("First Request", result.get(0).getRichiesta());
        assertEquals("First Request", result.get(0).getOggetto_richiesta());
        assertEquals(100, result.get(0).getId_utente());
        assertEquals(2, result.get(1).getId_richiesta());
        assertEquals("Second Request", result.get(1).getRichiesta());
        assertEquals("Second Request", result.get(1).getOggetto_richiesta());
        assertEquals(101, result.get(1).getId_utente());
    }

    @Test
    public void retrieveRichiestaSupportoByID() throws SQLException {
        when(mockResultSet.next()).thenReturn(true,false);
        when(mockResultSet.getInt("id_richiesta")).thenReturn(123);
        when(mockResultSet.getString("richiesta")).thenReturn("Test richiesta");
        when(mockResultSet.getString("oggetto_richiesta")).thenReturn("Test oggetto");
        when(mockResultSet.getInt("id_utente")).thenReturn(456);

        RichiestaSupporto result=richiestaSupportoManager.retrieveRichiestaSupportoByID(123);

        assertNotNull(result);
        assertEquals(123, result.getId_richiesta());
        assertEquals("Test richiesta", result.getRichiesta());
        assertEquals("Test oggetto", result.getOggetto_richiesta());
        assertEquals(456, result.getId_utente());
    }

    @Test
    public void inserisciRichiestaSupporto() throws SQLException {
        RichiestaSupporto richiesta = new RichiestaSupporto();
        richiesta.setId_richiesta(1);
        richiesta.setRichiesta("First request");
        richiesta.setOggetto_richiesta("First request");
        richiesta.setId_utente(100);

        // Call the method to be tested
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        boolean result = richiestaSupportoManager.inserisciRichiestaSupporto(richiesta);


        assertEquals(true, result);
    }
}
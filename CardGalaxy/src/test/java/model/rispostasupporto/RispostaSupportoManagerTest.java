package model.rispostasupporto;

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

class RispostaSupportoManagerTest {

    private RispostaSupportoManager rispostaSupportoManager;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }




        rispostaSupportoManager=new RispostaSupportoManager();

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
    public void testRetrieveTutteRisposte() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id_utente")).thenReturn(1, 2);
        when(mockResultSet.getString("risposta")).thenReturn("First response", "Second response");
        when(mockResultSet.getInt("id_richiesta_supporto")).thenReturn(100, 101);


        ArrayList<RispostaSupporto> result = rispostaSupportoManager.retrieveTutteRisposte();


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId_utente());
        assertEquals("First response", result.get(0).getRisposta());
        assertEquals(100, result.get(0).getId_richiesta_supporto());
        assertEquals(2, result.get(1).getId_utente());
        assertEquals("Second response", result.get(1).getRisposta());
        assertEquals(101, result.get(1).getId_richiesta_supporto());
    }

    @Test
    public void inserisciRispostaSupporto() throws SQLException {
        RispostaSupporto risposta = new RispostaSupporto();
        risposta.setId_utente(1);
        risposta.setRisposta("Test response");
        risposta.setId_richiesta_supporto(100);

        // Call the method to be tested
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        boolean result = rispostaSupportoManager.inserisciRispostaSupporto(risposta);


        assertEquals(true, result);
    }
}
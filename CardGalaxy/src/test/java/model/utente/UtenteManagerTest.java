package model.utente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.storage.Manager;

import org.mockito.Mockito;

public class UtenteManagerTest {

    private UtenteManager utenteManager;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {

        if (!Mockito.mockingDetails(Manager.class).isMock()) {
            mockStatic(Manager.class);
        }



        //utenteManager=mock(UtenteManager.class);
        utenteManager=new UtenteManager();

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
    public void testRetrieveUtenti() throws SQLException {

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("email")).thenReturn("test@example.com");
        when(mockResultSet.getString("nome")).thenReturn("Test");
        when(mockResultSet.getString("cognome")).thenReturn("User");
        when(mockResultSet.getString("username")).thenReturn("testuser");
        when(mockResultSet.getString("pass")).thenReturn("testpass");
        when(mockResultSet.getString("indirizzo")).thenReturn("Test Address");
        when(mockResultSet.getString("nazione")).thenReturn("Test Nation");
        when(mockResultSet.getString("citta")).thenReturn("Test City");
        when(mockResultSet.getInt("cap")).thenReturn(12345);
        when(mockResultSet.getString("data_nascita")).thenReturn("2000-01-01");
        when(mockResultSet.getBoolean("is_admin")).thenReturn(false);


        ArrayList<Utente> result = utenteManager.retrieveUtenti();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("test@example.com", result.get(0).getEmail());
        assertEquals("Test", result.get(0).getNome());
        assertEquals("User", result.get(0).getCognome());
        assertEquals("testuser", result.get(0).getUsername());
        assertEquals("206c80413b9a96c1312cc346b7d2517b84463edd", result.get(0).getPass());
        assertEquals("Test Address", result.get(0).getIndirizzo());
        assertEquals("Test Nation", result.get(0).getNazione());
        assertEquals("Test City", result.get(0).getCitta());
        assertEquals(12345, result.get(0).getCap());
        assertEquals("2000-01-01", result.get(0).getData_nascita());
        assertFalse(result.get(0).is_admin());
    }

    @Test
    public void testRetrieveUtentePass() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("email")).thenReturn("test@example.com");
        when(mockResultSet.getString("nome")).thenReturn("Test");
        when(mockResultSet.getString("cognome")).thenReturn("User");
        when(mockResultSet.getString("username")).thenReturn("testuser");
        when(mockResultSet.getString("pass")).thenReturn("testPassword");
        when(mockResultSet.getString("indirizzo")).thenReturn("Test Address");
        when(mockResultSet.getString("nazione")).thenReturn("Test Nation");
        when(mockResultSet.getString("citta")).thenReturn("Test City");
        when(mockResultSet.getInt("cap")).thenReturn(12345);
        when(mockResultSet.getString("data_nascita")).thenReturn("2000-01-01");
        when(mockResultSet.getBoolean("is_admin")).thenReturn(false);


        Utente result = utenteManager.retrieveUtentePass("testUser", "82f8809f42d911d1bd5199021d69d15ea91d1fad");


        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test", result.getNome());
        assertEquals("User", result.getCognome());
        assertEquals("testuser", result.getUsername());
        assertEquals("82f8809f42d911d1bd5199021d69d15ea91d1fad", result.getPass());
        assertEquals("Test Address", result.getIndirizzo());
        assertEquals("Test Nation", result.getNazione());
        assertEquals("Test City", result.getCitta());
        assertEquals(12345, result.getCap());
        assertEquals("2000-01-01", result.getData_nascita());
        assertFalse(result.is_admin());
    }


    @Test
    public void retrieveUtente() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("email")).thenReturn("test@example.com");
        when(mockResultSet.getString("nome")).thenReturn("Test");
        when(mockResultSet.getString("cognome")).thenReturn("User");
        when(mockResultSet.getString("username")).thenReturn("testuser");
        when(mockResultSet.getString("pass")).thenReturn("testPassword");
        when(mockResultSet.getString("indirizzo")).thenReturn("Test Address");
        when(mockResultSet.getString("nazione")).thenReturn("Test Nation");
        when(mockResultSet.getString("citta")).thenReturn("Test City");
        when(mockResultSet.getInt("cap")).thenReturn(12345);
        when(mockResultSet.getString("data_nascita")).thenReturn("2000-01-01");
        when(mockResultSet.getBoolean("is_admin")).thenReturn(false);


        Utente result = utenteManager.retrieveUtente(1);


        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test", result.getNome());
        assertEquals("User", result.getCognome());
        assertEquals("testuser", result.getUsername());
        assertEquals("82f8809f42d911d1bd5199021d69d15ea91d1fad", result.getPass());
        assertEquals("Test Address", result.getIndirizzo());
        assertEquals("Test Nation", result.getNazione());
        assertEquals("Test City", result.getCitta());
        assertEquals(12345, result.getCap());
        assertEquals("2000-01-01", result.getData_nascita());
        assertFalse(result.is_admin());
    }

    @Test
    public void creaUtente() throws SQLException {
        Utente testUtente = new Utente();
        testUtente.setEmail("test@example.com");
        testUtente.setNome("Test");
        testUtente.setCognome("User");
        testUtente.setUsername("testuser");
        testUtente.setPass("password123");
        testUtente.setIndirizzo("123 Test Street");
        testUtente.setNazione("Testland");
        testUtente.setCitta("Testville");
        testUtente.setCap(12345);
        testUtente.setData_nascita("2000-01-01");
        testUtente.setAdmin(false);

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        boolean result = utenteManager.creaUtente(testUtente);


        assertEquals(true, result);

    }

    @Test
    public void aggiornaUtente() throws SQLException {
        Utente testUtente = new Utente();
        testUtente.setEmail("test@example.com");
        testUtente.setNome("Test");
        testUtente.setCognome("User");
        testUtente.setUsername("testuser");
        testUtente.setPass("password123");
        testUtente.setIndirizzo("123 Test Street");
        testUtente.setNazione("Testland");
        testUtente.setCitta("Testville");
        testUtente.setCap(12345);
        testUtente.setData_nascita("2000-01-01");
        testUtente.setAdmin(false);

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        boolean result = utenteManager.aggiornaUtente(testUtente);


        assertEquals(true, result);
    }

    @Test
    public void cancellaUtente() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        boolean result = utenteManager.cancellaUtente(99);


        assertTrue(result);
    }

    @Test
    public void countUsers() throws SQLException {

        when(mockResultSet.next()).thenReturn(true,true,true,false);



        int result = utenteManager.countUsers();


        assertEquals(3, result);
    }

    @Test
    public void checkUsername() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("username")).thenReturn("username");


        boolean result = utenteManager.checkUsername("username");


        assertEquals(true, result);
    }

    @Test
    public void checkEmail() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("email")).thenReturn("test@example.it");


        boolean result = utenteManager.checkEmail("test@example.it");


        assertEquals(true, result);
    }
}
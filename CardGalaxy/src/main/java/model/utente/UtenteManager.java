package model.utente;

import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;

public class UtenteManager extends Manager
{
    private static final UtenteQuery QUERY = new UtenteQuery("utente");

    public ArrayList<Utente> retrieveUtenti() throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.tuttiUtenti())){
                ResultSet set = ps.executeQuery();
                ArrayList<Utente> utenti = new ArrayList<>();
                while(set.next()){
                    Utente u = new Utente();
                    u.setId(set.getInt("id"));
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPassword(set.getString("pass"));
                    u.setDataNascita(set.getDate("dataNascita"));
                    u.setVia(set.getString("via"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setNazione(set.getString("nazione"));
                    utenti.add(u);
                }
                set.close();
                return utenti;
            }
        }
    }


    public Utente retrieveUtentePass(String email, String password) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.singoloUtenteConPass())) {
                ps.setString(1, email);
                ps.setString(2,password);
                ResultSet set = ps.executeQuery();
                Utente u = new Utente();
                if (set.next()) {
                    u.setId(set.getInt("id"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setEmail(set.getString("email"));
                    u.setPassword(set.getString("pass"));
                    u.setDataNascita(set.getDate("dataNascita"));
                    u.setVia(set.getString("via"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setNazione(set.getString("nazione"));
                }
                set.close();
                return u;
            }
        }
    }

    public Utente retrieveUtente(String email) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.singoloUtente())) {
                ps.setString(1, email);
                ResultSet set = ps.executeQuery();
                Utente u = new Utente();
                if (set.next()) {
                    u.setId(set.getInt("id"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setEmail(set.getString("email"));
                    u.setPassword(set.getString("pass"));
                    u.setDataNascita(set.getDate("dataNascita"));
                    u.setVia(set.getString("via"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));

                    u.setNazione(set.getString("nazione"));
                }
                set.close();
                return u;
            }
        }
    }

    public boolean creaUtente(Utente utente) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.inserisciUtente())){
                ps.setInt(1,utente.getId());
                ps.setString(3,utente.getNome());
                ps.setString(4,utente.getCognome());
                ps.setString(2,utente.getEmail());
                ps.setString(5,utente.getUsername());
                ps.setString(6,utente.getPassword());
                ps.setDate(11, utente.getDataNascita());
                ps.setString(7,utente.getVia());
                ps.setString(9,utente.getCitta());
                ps.setInt(8,utente.getCap());
                ps.setString(10,utente.getNazione());
                ps.executeUpdate();
                return true;
            }
        }
    }

    public boolean aggiornaUtente(Utente utente) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.aggiornaUtente())){
                ps.setString(1,utente.getNome());
                ps.setString(2,utente.getCognome());
                ps.setString(3,utente.getEmail());
                ps.setString(5, String.valueOf((Date) utente.getDataNascita()));
                ps.setString(8,utente.getVia());
                ps.setString(9,utente.getCitta());
                ps.setInt(10,utente.getCap());
                ps.setString(12,utente.getNazione());
                ps.setString(13,utente.getEmail());
                ps.executeUpdate();
                return true;
            }
        }
    }

    public boolean cancellaUtente(String email) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.eliminaUtente())){
                ps.setString(1,email);
                ps.executeUpdate();
                return true;
            }
        }
    }
}

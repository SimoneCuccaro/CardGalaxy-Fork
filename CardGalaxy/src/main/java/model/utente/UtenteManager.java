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
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setDataNascita(set.getString("dataNascita"));
                    u.setAdmin(set.getBoolean("isAdmin"));
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
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setDataNascita(set.getString("dataNascita"));
                    u.setAdmin(set.getBoolean("isAdmin"));
                }
                set.close();
                return u;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
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
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setDataNascita(set.getString("dataNascita"));
                    u.setAdmin(set.getBoolean("isAdmin"));
                }
                set.close();
                return u;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean creaUtente(Utente utente) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.inserisciUtente(),Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,utente.getNome());
                ps.setString(2,utente.getCognome());
                ps.setString(3,utente.getEmail());
                ps.setString(4,utente.getUsername());
                ps.setString(5,utente.getPass());
                ps.setString(6,utente.getIndirizzo());
                ps.setString(7, utente.getDataNascita());
                ps.setBoolean(8,utente.isAdmin());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                utente.setId(id);
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean aggiornaUtente(Utente utente) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.aggiornaUtente())){
                ps.setString(1,utente.getNome());
                ps.setString(2,utente.getCognome());
                ps.setString(3,utente.getEmail());
                ps.setString(4,utente.getUsername());
                ps.setString(5,utente.getIndirizzo());
                ps.setString(6,utente.getDataNascita());
                ps.setBoolean(7,utente.isAdmin());
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cancellaUtente(String email) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.eliminaUtente())){
                ps.setString(1,email);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

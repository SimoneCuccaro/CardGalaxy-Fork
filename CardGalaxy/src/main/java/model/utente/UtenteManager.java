package model.utente;

import model.storage.Manager;

import java.sql.*;
import java.util.ArrayList;

public class UtenteManager extends Manager
{
    private static final UtenteQuery QUERY = new UtenteQuery("utente");

    public ArrayList<Utente> retrieveUtenti(){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.tuttiUtenti())) {
                ResultSet set = ps.executeQuery();
                ArrayList<Utente> utenti = new ArrayList<>();
                while (set.next()) {
                    Utente u = new Utente();
                    u.setId(set.getInt("id"));
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setNazione(set.getString("nazione"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setData_nascita(set.getString("data_nascita"));
                    u.setAdmin(set.getBoolean("is_admin"));
                    utenti.add(u);
                }
                set.close();
                return utenti;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Utente retrieveUtentePass(String username, String password){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.singoloUtenteConPass())) {
                ps.setString(1, username);
                ps.setString(2,password);
                ResultSet set = ps.executeQuery();
                Utente u = null;
                if (set.next()) {
                    u = new Utente();
                    u.setId(set.getInt("id"));
                    u.setEmail(set.getString("email"));
                    u.setNome(set.getString("nome"));
                    u.setCognome(set.getString("cognome"));
                    u.setUsername(set.getString("username"));
                    u.setPass(set.getString("pass"));
                    u.setIndirizzo(set.getString("indirizzo"));
                    u.setNazione(set.getString("nazione"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setData_nascita(set.getString("data_nascita"));
                    u.setAdmin(set.getBoolean("is_admin"));
                }
                set.close();
                return u;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente retrieveUtente(int id){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.singoloUtente())) {
                ps.setInt(1, id);
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
                    u.setNazione(set.getString("nazione"));
                    u.setCitta(set.getString("citta"));
                    u.setCap(set.getInt("cap"));
                    u.setData_nascita(set.getString("data_nascita"));
                    u.setAdmin(set.getBoolean("is_admin"));
                }
                set.close();
                return u;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean creaUtente(Utente utente){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.inserisciUtente(),Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,utente.getEmail());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getUsername());
                ps.setString(5,utente.getPass());
                ps.setString(6,utente.getIndirizzo());
                ps.setString(7,utente.getNazione());
                ps.setString(8,utente.getCitta());
                ps.setInt(9,utente.getCap());
                ps.setString(10, utente.getData_nascita());
                ps.setBoolean(11,utente.is_admin());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                utente.setId(id);
                rs.close();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean aggiornaUtente(Utente utente){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.aggiornaUtente())){
                ps.setString(1,utente.getEmail());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getUsername());
                ps.setString(5,utente.getIndirizzo());
                ps.setString(6,utente.getNazione());
                ps.setString(7,utente.getCitta());
                ps.setInt(8,utente.getCap());
                ps.setString(9, utente.getData_nascita());
                ps.setBoolean(10,utente.is_admin());
                ps.setString(11,utente.getPass());
                ps.setInt(12,utente.getId());
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cancellaUtente(int id){
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.eliminaUtente())){
                ps.setInt(1,id);
                ps.executeUpdate();
                return true;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countUsers (){
        try (Connection con = Manager.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(QUERY.contaUtenti())) {
                ResultSet resultSet = ps.executeQuery();
                int size = 0;
                while (resultSet.next()) {
                    if(!resultSet.getBoolean("is_admin")){
                        size++;
                    }
                }
                return size;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

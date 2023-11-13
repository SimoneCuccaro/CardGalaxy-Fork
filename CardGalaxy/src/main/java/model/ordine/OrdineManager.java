package model.ordine;

import model.storage.Manager;
import model.utente.Utente;
import model.utente.UtenteQuery;

import java.sql.*;
import java.util.ArrayList;
public class OrdineManager extends Manager{
    private static final OrdineQuery QUERY = new OrdineQuery("ordine");

    public ArrayList<Ordine> retrieveOrdini() throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.retrieveAllOrdini())){
                ResultSet set = ps.executeQuery();
                ArrayList<Ordine> ordini = new ArrayList<>();
                while(set.next()){
                    Ordine o=new Ordine();
                    o.setCodice(set.getInt("codice"));
                    o.setPrezzoTotale(set.getDouble("prezzoTotale"));
                    o.setDataAcquisto(set.getDate("dataAcquisto"));
                    o.setUtente(set.getInt("utente"));
                    ordini.add(o);
                }
                set.close();
                return ordini;
            }
        }
    }

    public int contaOrdini() throws SQLException{
        try(Connection con=Manager.getConnection()){
            try(PreparedStatement ps=con.prepareStatement(QUERY.countAllOrdini())){
                ResultSet set= ps.executeQuery();
                int count=0;
                if(set.next()){
                    count=set.getInt("ordiniTotali");
                }
                return count;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Ordine retrieveOrdineById(int id) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.retrieveOrdineById())) {
                ps.setInt(1, id);
                ResultSet set = ps.executeQuery();
                Ordine o=new Ordine();
                if (set.next()) {
                    o.setCodice(set.getInt("codice"));
                    o.setPrezzoTotale(set.getDouble("prezzoTotale"));
                    o.setDataAcquisto(set.getDate("dataAcquisto"));
                    o.setUtente(set.getInt("utente"));
                }
                set.close();
                return o;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean creaOrdine(Ordine ordine) throws SQLException {
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.creaOrdine())){
                ps.setInt(1,ordine.getCodice());
                ps.setDouble(2,ordine.getPrezzoTotale());
                ps.setDate(3, (Date) ordine.getDataAcquisto());
                ps.setInt(2,ordine.getUtente());
                ps.executeUpdate();
                return true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int lastOrder() throws SQLException{
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.maxID())){
                ResultSet set= ps.executeQuery();
                int lastId=0;
                if(set.next()){
                    lastId=set.getInt("lastorder");
                }
                return lastId;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

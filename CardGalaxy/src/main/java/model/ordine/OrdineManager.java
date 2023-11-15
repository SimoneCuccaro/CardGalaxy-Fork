package model.ordine;

import model.storage.Manager;


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
                    o.setPrezzototale(set.getDouble("prezzoTotale"));
                    o.setDataacquisto(set.getDate("dataAcquisto"));
                    o.setIdutente(set.getInt("utente"));
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
                    o.setPrezzototale(set.getDouble("prezzoTotale"));
                    o.setDataacquisto(set.getDate("dataAcquisto"));
                    o.setIdutente(set.getInt("utente"));
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
            try(PreparedStatement ps = con.prepareStatement(QUERY.creaOrdine(),Statement.RETURN_GENERATED_KEYS)){
                ps.setDouble(1,ordine.getPrezzototale());
                ps.setDate(2, (Date) ordine.getDataacquisto());
                ps.setInt(3,ordine.getIdutente());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int codice = rs.getInt(1);
                ordine.setCodice(codice);
                return true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int lastOrder() throws SQLException{
        try(Connection con = Manager.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(QUERY.lastOrder())){
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

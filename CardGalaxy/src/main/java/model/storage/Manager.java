package model.storage;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**La classe <code>Manager</code> si occupa di effettuare la connessione con il database
 *
 */
public abstract class Manager
{
    private static org.apache.tomcat.jdbc.pool.DataSource datasource;

    /**Il metodo <code>getConnection</code> si occupa di effettuare la connessione con il database
     *
     * @return oggetto di tipo Connection che rappresenta la connessione con il database
     * @throws SQLException eccezione inerente agli errori per l' accesso al database
     */
    public static Connection getConnection() throws SQLException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/progettois?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("Palladino512");
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}

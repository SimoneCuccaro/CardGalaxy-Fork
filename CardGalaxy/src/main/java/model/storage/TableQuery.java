package model.storage;

/**La classe <code>TableQuery</code> serve a rappresentare un' astrazione di una tabella presente nel database
 *
 */
public  abstract class TableQuery
{
    /**Stringa che contiene il nome della tabella
     *
     */
    protected final String table;

    /**Costuttore dell' oggetto TableQuery
     *
     * @param table nome della tabella presente nel database da voler utilizzare
     */
    protected TableQuery(String table) {
        this.table = table;
    }
}

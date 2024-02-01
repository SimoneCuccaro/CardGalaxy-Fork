package model.errors;

import java.util.List;

/**La classe <code>Alert</code> serve a gestire i messaggi di errore,
 * è costituita da una lista di messaggi di errore e da un tipo che rappresenta la categoria di errore
 *
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Alert {
    private final List<String> messages;
    private final String type;

    /** Costruttore della classe Alert
     *
     * @param messages lista dei messaggi di errore
     * @param type     categoria dei messaggi di errore
     */
    public Alert(List<String> messages, String type) {
        this.messages=messages;
        this.type=type;
    }


    /**Il metodo <code>getMessages</code> consente di ricavare la lista dei messaggi
     * di errore da un oggetto Alert
     *
     * @return lista dei messaggi di errore da un oggetto Alert
     */
    public List<String> getMessages() {
        return messages;
    }


    /**Il metodo <code>getType</code> consente di ricavare la categoria dei
     * messaggi di errore di un oggetto Alert
     *
     * @return stringa contenente la categoria dei messaggi di errore
     */
    public String getType() {
        return type;
    }
}

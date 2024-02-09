package model;


import model.carrello.Carrello;
import model.carrello.CarrelloSession;
import model.errors.ErrorHandler;
import model.errors.InvalidRequestException;
import model.utente.UtenteSession;
import model.validator.Validator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**La classe <code>Controller</code> implementa alcune funzioni di uso generale
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Controller extends HttpServlet implements ErrorHandler {

    /**Il metodo <code>validate</code> serve a lanciare un' eccezione nel caso si verifichi un errore di validazione
     *
     * @param validator oggetto validator di cui si deve controllare la lista degli errori
     * @throws InvalidRequestException eccezione usata quando viene invocato un metodo non valido per alcune ragioni
     */
    public  void validate(Validator validator) throws InvalidRequestException {
        if(validator.hasErrors()){
            throw new InvalidRequestException("Validation Error",validator.getErrors(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    /**Il metodo <code>getUtenteSession</code> serve ad ottenere l'attributo utenteSession,
     *che contiene i dati inerenti all'utente di sessione
     *
     * @param session parametro che rappresenta la sessione corrente
     * @return oggetto di tipo UtenteSession
     */
    protected UtenteSession getUtenteSession(HttpSession session){
        return (UtenteSession) session.getAttribute("utenteSession");
    }

    /**Il metodo <code>getSessionCart</code> serve ad ottenere l'attributo cart,
     *che contiene i dati inerenti al carrello di sessione
     *
     * @param session parametro che rappresenta la sessione corrente
     * @return oggetto di tipo CarrelloSession
     */
    protected CarrelloSession getSessionCart(HttpSession session){
        return (CarrelloSession) session.getAttribute("cart");
    }

}

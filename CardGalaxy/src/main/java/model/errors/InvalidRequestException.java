package model.errors;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**La classe <code>InvalidRequestException</code> viene utilizzata per la gestione delle eccezioni,
 * contiene una lista di errori un intero che  rappresenta il codice dell' errore
 *
 */
public class InvalidRequestException extends Exception{

    /**Intero che rappresenta il codice di errore
     *
     */
    private final int errorCode;

    /**Lista contenente stringhe con i messaggi di errore
     *
     */
    private final List<String> errors;

    /**Costruttore della classe InvalidRequestException
     *
     * @param message stringa contenente il messaggio da visualizzare inerente all' errrore
     * @param errors lista degli errori
     * @param errorCode intero che rappresenta il codice dell' errore
     */
    public InvalidRequestException(String message, List<String> errors, int errorCode){
        super(message);
        this.errors=errors;
        this.errorCode=errorCode;
    }

    /**Il metodo <code>handle</code> consente, in caso di errore, di ritornare alla pagina in cui esso si è verificato
     * e mostra un oggetto Alert contenente i relativi messaggi di errore
     *
     * @param request oggetto request
     * @param response oggetto response
     * @throws IOException eccezione inerente a problemi di input/output
     * @throws ServletException eccezione inerente alle servlet
     */
    public void handle(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String backPath =(String) request.getAttribute("back");
        switch(errorCode){
            case HttpServletResponse.SC_BAD_REQUEST:
                request.setAttribute("alert",new Alert(errors,"danger"));
                response.setStatus(errorCode);
                request.getRequestDispatcher(backPath).forward(request,response);
                break;
            default:
                response.sendError(errorCode,errors.get(0));
        }
    }

    /**Il metodo <code>getErrors</code> consente di ottenere la lista di errori all' interno
     * dell' oggetto InvalidRequestException
     *
     * @return lista degli errori presenti nell' oggetto InvalidRequestException
     */
    public List<String> getErrors() {
        return errors;
    }
}

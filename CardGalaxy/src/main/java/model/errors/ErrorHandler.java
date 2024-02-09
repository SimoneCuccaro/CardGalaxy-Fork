package model.errors;

import model.utente.UtenteSession;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**L'interfaccia <code>ErrorHandler</code> viene usata per definire dei metodi per la gestione di alcuni errori specifici
 * e il reindirizzamento verso le opportune pagine di errore
 *
 *  @author Giulio Palladino
 *  @author Simone Cuccaro
 *  @author Gianluca Trani
 *  @author Francesco Venuto
 */
public interface ErrorHandler {


    /**Il metodo <code>authenticate</code> consente di controllare a livello di sessione se un utente ha effettuato il login
     * in caso contrario mostra la pagina di errore con codice 401
     *
     * @param session parametro che rappresenta la sessione corrente
     * @throws InvalidRequestException eccezione usata quando viene invocato un metodo non valido per alcune ragioni
     */
    //401
    default void authenticate(HttpSession session) throws InvalidRequestException {
        if (session == null || session.getAttribute("utenteSession") == null) {
            throw new InvalidRequestException("Errore autenicazione",List.of("Non sei autenticato"),HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    /**Il metodo <code>authorize</code> consente di controllare a livello di sessione se un utente ha effettuato il login
     * ed inoltre se è un admin, in caso contrario mostra la pagina di errore con codice 403
     *
     * @param session parametro che rappresenta la sessione corrente
     * @throws InvalidRequestException eccezione usata quando viene invocato un metodo non valido per alcune ragioni
     */
    //403
    default void authorize(HttpSession session) throws InvalidRequestException {
        authenticate(session);
        UtenteSession utenteSession = (UtenteSession) session.getAttribute("utenteSession");
        if (!utenteSession.isAdmin()) {
            throw new InvalidRequestException("Errore autorizzazione",List.of("Non sei autorizzato"),HttpServletResponse.SC_FORBIDDEN);
        }
    }

    /**Il metodo <code>internalError</code> consente di reindirizzare l'utente ad una pagina di errore di tipo 500 in caso di errori
     * imprevisti
     *
     * @throws InvalidRequestException eccezione usata quando viene invocato un metodo non valido per alcune ragioni
     */
    //500
    default void internalError() throws InvalidRequestException {
        List<String> errors=List.of("Un errore imprevisto è accaduto","Riprova più tardi");
        throw new InvalidRequestException("Errore interno",errors,HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    /**Il metodo <code>notFound</code> consente di reindirizzare l'utente verso una pagina di errore 404 in caso di
     * una richiesta di una pagina non presente all'interno del sito
     *
     * @throws InvalidRequestException eccezione usata quando viene invocato un metodo non valido per alcune ragioni
     */
    //404
    default void notFound() throws InvalidRequestException {
        throw new InvalidRequestException("Errore interno",List.of("Risorsa non trovata"),HttpServletResponse.SC_NOT_FOUND);
    }

    /**Il metodo <code>notAllowed</code> consente di reindirizzare l'utente verso una pagina di errore di tipo 405 nel caso
     * abbia provato ad effettuare una operazione ad esso non consentita
     *
     * @throws InvalidRequestException eccezione usata quando viene invocato un metodo non valido per alcune ragioni
     */
    //405
    default void notAllowed() throws InvalidRequestException {
        throw new InvalidRequestException("Operazione non consentita",List.of("Operazione non permessa"),HttpServletResponse.SC_METHOD_NOT_ALLOWED);

    }
}

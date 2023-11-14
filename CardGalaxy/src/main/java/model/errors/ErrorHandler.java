package model.errors;

import model.utente.UtenteSession;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface ErrorHandler {


    //401
    default void authenticate(HttpSession session) throws InvalidRequestException {
        if (session == null || session.getAttribute("utenteSession") == null) {
            throw new InvalidRequestException("Errore autenicazione",List.of("Non sei autenticato"),HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    //403
    default void authorize(HttpSession session) throws InvalidRequestException {
        authenticate(session);
        UtenteSession utenteSession = (UtenteSession) session.getAttribute("utenteSession");
        if (!utenteSession.isAdmin()) {
            throw new InvalidRequestException("Errore autorizzazione",List.of("Non sei autorizzato"),HttpServletResponse.SC_FORBIDDEN);
        }
    }

    //500
    default void internalError() throws InvalidRequestException {
        List<String> errors=List.of("Un errore imprevisto è accaduto","Riprova più tardi");
        throw new InvalidRequestException("Errore interno",errors,HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    //404
    default void notFound() throws InvalidRequestException {
        throw new InvalidRequestException("Errore interno",List.of("Risorsa non trovata"),HttpServletResponse.SC_NOT_FOUND);
    }

    //405
    default void notAllowed() throws InvalidRequestException {
        throw new InvalidRequestException("Operazione non consentita",List.of("Operazione non permessa"),HttpServletResponse.SC_METHOD_NOT_ALLOWED);

    }
}

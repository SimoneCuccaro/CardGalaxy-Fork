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

public class Controller extends HttpServlet implements ErrorHandler {

    public  void validate(Validator validator) throws InvalidRequestException {
        if(validator.hasErrors()){
            throw new InvalidRequestException("Validation Error",validator.getErrors(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected UtenteSession getUtenteSession(HttpSession session){
        return (UtenteSession) session.getAttribute("utenteSession");
    }

    protected CarrelloSession getSessionCart(HttpSession session){
        return (CarrelloSession) session.getAttribute("cart");
    }

}

package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class UpdateUtenteValidator {
    public static Validator validateUpdate(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertEmail("email","Inserisci email in formato x@x.x");
        requestValidator.assertMatch("nome", Pattern.compile("^[\\s\\S]{5,20}$"),"nome compreso fra 5 e 20 caratteri");
        requestValidator.assertMatch("cognome",Pattern.compile("^[\\s\\S]{5,20}$"),"cognome compreso fra 5 e 20 caratteri");
        requestValidator.assertMatch("username", Pattern.compile("^[\\s\\S]{5,20}$"),"username compreso fra 5 e 20 caratteri");
        requestValidator.assertIndirizzo("indirizzo","L' indirizzo deve essere in formato citta',nomevia numerocivico");
        requestValidator.assertData("dataNascita","La data deve essere in formato DD/MM/YYYY");
        return requestValidator;
    }
}

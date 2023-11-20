package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegisterValidator {
    public static Validator validateRegister(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertEmail("email","Inserisci email in formato x@x.x");
        requestValidator.assertMatch("nome", Pattern.compile("^[\\s\\S]{5,20}$"),"nome compreso fra 5 e 20 caratteri");
        requestValidator.assertMatch("cognome",Pattern.compile("^[\\s\\S]{5,20}$"),"cognome compreso fra 5 e 20 caratteri");
        requestValidator.assertMatch("username", Pattern.compile("^[\\s\\S]{5,20}$"),"username compreso fra 5 e 20 caratteri");
        requestValidator.assertPassword("password","La password deve contenere almeno 6 caratteri di cui alemeno 1 in uppercase,1 in lowercase e 1 numero senza spazi");
        requestValidator.assertMatch("nazione",Pattern.compile("^[\\s\\S]{5,20}$"),"nazione compreso fra 2 e 25 caratteri");
        requestValidator.assertMatch("citta",Pattern.compile("^[\\s\\S]{5,20}$"),"citta compreso fra 2 e 25 caratteri");
        requestValidator.assertMatch("via",Pattern.compile("^[\\s\\S]{5,20}$"),"via compresa fra 2 e 30 caratteri");
        requestValidator.assertInt("cap","inserire un valore numerico");
        requestValidator.assertData("dataNascita","La data deve essere in formato DD/MM/YYYY");
        return requestValidator;
    }
}


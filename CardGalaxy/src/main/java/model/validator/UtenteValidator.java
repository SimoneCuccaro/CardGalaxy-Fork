package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class UtenteValidator {
    public static Validator validateUtente(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("nome", Pattern.compile("^[A-Za-zà\\s]{2,20}$"),"nome compreso fra 2 e 20 caratteri");
        requestValidator.assertMatch("cognome",Pattern.compile("^[A-Za-zà\\s]{2,20}$"),"cognome compreso fra 2 e 20 caratteri");
        requestValidator.assertMatch("username", Pattern.compile("^[\\s\\S]{2,30}$"),"username compreso fra 2 e 20 caratteri");
        requestValidator.assertPassword("password","La password deve essere compresa tra 8 e 15 caratteri di cui alemeno 1 in uppercase,1 in lowercase e 1 numero senza spazi");
        requestValidator.assertMatch("nazione",Pattern.compile("^[A-Za-zà\\s]{2,30}$"),"nazione compresa fra 2 e 30 caratteri");
        requestValidator.assertMatch("citta",Pattern.compile("^[A-Za-zà\\s]{2,30}$"),"citta compresa fra 2 e 30 caratteri");
        requestValidator.assertMatch("via",Pattern.compile("^[\\s\\S]{2,30}$"),"via compresa fra 2 e 30 caratteri");
        requestValidator.assertMatch("cap",Pattern.compile("^[\\s\\S]{5,5}$"),"il cap deve essere di 5 caratteri interi");
        return requestValidator;
    }
}


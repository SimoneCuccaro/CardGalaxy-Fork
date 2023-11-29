package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RispostaValidator {
    public static Validator validateRisposta(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("risposta", Pattern.compile("^[\\s\\S]{5,150}$"),"il testo deve essere minimo 5 caratteri e massimo 150");
        return requestValidator;
    }
}

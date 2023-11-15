package model.validator;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class RecensioneValidator {
    public static Validator validateRecensione(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("testo",Pattern.compile("^[\\s\\S]{5,150}$"),"il testo deve essere minimo 5 caratteri e massimo 150");
        return requestValidator;
    }
}

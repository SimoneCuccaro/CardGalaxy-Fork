package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RichiestaValidator {
    public static Validator validateRichiesta(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("richiesta",Pattern.compile("^[\\s\\S]{5,100}$"),"il testo deve essere minimo 5 caratteri e massimo 100");
        requestValidator.assertMatch("oggettorichiesta",Pattern.compile("^[\\s\\S]{5,50}$"),"il testo deve essere minimo 5 caratteri e massimo 50");
        return requestValidator;
    }
}

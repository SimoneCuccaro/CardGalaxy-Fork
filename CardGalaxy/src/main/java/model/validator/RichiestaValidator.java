package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RichiestaValidator {
    public static Validator validateRichiesta(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("richiesta",Pattern.compile("^[\\s\\S]{5,150}$"),"il testo deve essere minimo 5 caratteri e massimo 150");
        requestValidator.assertMatch("oggettorichiesta",Pattern.compile("^[\\s\\S]{5,30}$"),"il testo deve essere minimo 5 caratteri e massimo 30");
        return requestValidator;
    }
}

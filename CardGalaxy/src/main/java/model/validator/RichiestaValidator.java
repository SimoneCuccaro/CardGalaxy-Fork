package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RichiestaValidator {
    public static Validator validateRichiesta(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("request",Pattern.compile("^[\\s\\S]{5,150}$"),"the text has to be between 5 and 150 characters");
        requestValidator.assertMatch("object",Pattern.compile("^[\\s\\S]{5,30}$"),"the text has to be between 5 and 30 characters");
        return requestValidator;
    }
}

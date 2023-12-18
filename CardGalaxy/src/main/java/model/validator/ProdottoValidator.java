package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProdottoValidator {
    public static Validator validateProdotto(HttpServletRequest request) {
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("name", Pattern.compile("^[\\s\\S]{3,20}$"),"name between 2 and 30 characters");
        requestValidator.assertMatch("platform",Pattern.compile("^[\\s\\S]{2,20}$"),"platform between 2 and 30 characters");
        requestValidator.assertMatch("description",Pattern.compile("^[\\s\\S]{10,100}$"),"description between 10 and 100 characters");
        return requestValidator;
    }
}

package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProdottoValidator {
    public static Validator validateProdotto(HttpServletRequest request) {
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("nome", Pattern.compile("^[\\s\\S]{3,20}$"),"nome compreso fra 3 e 20 caratteri");
        requestValidator.assertMatch("piattaforma",Pattern.compile("^[\\s\\S]{2,20}$"),"piattaforma compresa fra 2 e 2 caratteri");
        requestValidator.assertMatch("descrizione",Pattern.compile("^[\\s\\S]{10,150}$"),"descrizione compresa fra 10 e 150 caratteri");
        return requestValidator;
    }
}

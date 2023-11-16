package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class ProdottoValidator {
    public static Validator validateProdotto(HttpServletRequest request) {
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("nome", Pattern.compile("^[\\s\\S]{5,20}$"),"nome compreso fra 5 e 20 caratteri");
        requestValidator.assertMatch("piattaforma",Pattern.compile("^[\\s\\S]{5,20}$"),"piattaforma compresa fra 5 e 2 caratteri");
        requestValidator.assertMatch("descrizione",Pattern.compile("^[\\s\\S]{5,100}$"),"descrizione compresa fra 5 e 100 caratteri");
        requestValidator.assertDouble("prezzo","Inserire prezzo in formato 'x.x'");
        return requestValidator;
    }
}

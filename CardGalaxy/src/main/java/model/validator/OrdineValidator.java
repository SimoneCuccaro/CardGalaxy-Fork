package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class OrdineValidator {
    public static Validator validateOrdine(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertDestinazione("indirizzo","La destinazione deve essere in formato citta',nomevia numerocivico");
        return requestValidator;
    }
}

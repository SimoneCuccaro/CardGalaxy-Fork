package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**La classe <code>RichiestaValidator</code> si occupa di validare i dati in input relativi ai form di gestione delle Richieste
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RichiestaValidator {

    /**Il metodo <code>validateRichiesta</code> si occupa di validare i dati in input relativi ai form di gestione delle Richieste
     *
     * @param request oggetto richiesta contenente i parametri da validare
     * @return oggetto Validator contenente gli eventuali errori di matching
     */
    public static Validator validateRichiesta(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("request",Pattern.compile("^[\\s\\S]{5,150}$"),"the text has to be between 5 and 150 characters");
        requestValidator.assertMatch("object",Pattern.compile("^[\\s\\S]{5,30}$"),"the text has to be between 5 and 30 characters");
        return requestValidator;
    }
}

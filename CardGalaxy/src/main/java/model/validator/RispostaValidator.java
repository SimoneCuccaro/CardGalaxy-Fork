package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**La classe <code>RispostaValidator</code> si occupa di validare i dati in input relativi ai form di gestione delle Risposte
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RispostaValidator {

    /**Il metodo <code>validateRisposta</code> si occupa di validare i dati in input relativi ai form di gestione delle Risposte
     *
     * @param request oggetto richiesta contenente i parametri da validare
     * @return oggetto Validator contenente gli eventuali errori di matching
     */
    public static Validator validateRisposta(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("response", Pattern.compile("^[\\s\\S]{5,150}$"),"il testo deve essere minimo 5 caratteri e massimo 150");
        return requestValidator;
    }
}

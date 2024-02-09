package model.validator;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**La classe <code>RecensioneValidator</code> si occupa di validare i dati in input relativi ai form di gestione delle Recensioni
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class RecensioneValidator {

    /**Il metodo <code>validateRecensione</code> si occupa di validare i dati in input relativi ai form di gestione delle Recensioni
     *
     * @param request oggetto richiesta contenente i parametri da validare
     * @return oggetto Validator contenente gli eventuali errori di matching
     */
    public static Validator validateRecensione(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("testo",Pattern.compile("^[\\s\\S]{10,150}$"),"il testo deve essere minimo 10 caratteri e massimo 150");
        return requestValidator;
    }
}

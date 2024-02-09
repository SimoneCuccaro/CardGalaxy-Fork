package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**La classe <code>ProdottoValidator</code> si occupa di validare i dati in input relativi ai form di gestione delle GiftCard
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class ProdottoValidator {

    /**Il metodo <code>validateProdotto</code> si occupa di validare i dati in input relativi ai form di gestione delle GiftCard
     *
     * @param request oggetto richiesta contenente i parametri da validare
     * @return oggetto Validator contenente gli eventuali errori di matching
     */
    public static Validator validateProdotto(HttpServletRequest request) {
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("name", Pattern.compile("^[\\s\\S]{3,20}$"),"name between 2 and 30 characters");
        requestValidator.assertMatch("platform",Pattern.compile("^[\\s\\S]{2,20}$"),"platform between 2 and 30 characters");
        requestValidator.assertMatch("description",Pattern.compile("^[\\s\\S]{10,100}$"),"description between 10 and 100 characters");
        return requestValidator;
    }
}

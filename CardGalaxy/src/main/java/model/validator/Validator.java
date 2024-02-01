package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**La classe <code>Validator</code> serve validare i dati in input delle form con determinate expression language e
 * indicare eventuali errori.
 * Un oggetto Validator è costituito da una lista di eventuali errori e dall' oggetto richiesta in cui vi sono i parametri che deve validare
 *
 * @author Giulio Palladino
 * @author Simone Cuccaro
 * @author Gianluca Trani
 * @author Francesco Venuto
 */
public class Validator {
    private final List<String> errors;
    private final HttpServletRequest request;
    private static final Pattern INT_PATTERN=Pattern.compile("^\\d+$");
    private static final Pattern DOUBLE_PATTERN=Pattern.compile("^\\d+(?:[.]\\d{1,2}|$)$");

    /**Costruttore dell' oggetto Validator
     *
     * @param request oggetto richiesta contenente i parametri da validare
     */
    public Validator(HttpServletRequest request){
        this.errors=new ArrayList<>();
        this.request=request;
    }

    /**Il metodo <code>hasErrors</code> verifica se la lista di errori di un oggetto Validator è vuota
     *
     * @return booleano che indica se la lista di errori ha errori(=1) o è vuota(=0)
     */
    public boolean hasErrors(){return !errors.isEmpty();}

    /**Il metodo <code>getErrors</code> restituisce la lista di errori di un oggetto Validator
     *
     * @return lista di errori dell' oggetto Validator
     */
    public List<String> getErrors(){
        return errors;
    }

    /**Il metodo <code>gatherError</code> consente di aggiungere errori nella lista
     *
     * @param condition valore booleano, se =1 non ci sono errori, se=0 si aggiunge un errore alla lista
     * @param message messaggio di errore da aggiungere alla lista
     * @return booleano che indica se è stato aggiunto o no un messaggio
     */
    public boolean gatherError(boolean condition,String message){
        if(condition){
            return true;
        }else{
            errors.add(message);
            return false;
        }
    }

    /**Il metodo <code>required</code> controlla se una stringa non è vuota e non nulla
     *
     * @param value stringa di cui si vuole verificare il contenuto
     * @return booleano che indica se la stringa è vuota e non nulla oppure no
     */
    private boolean required(String value){return value!=null && !value.isBlank();}


    /**Il metodo <code>assertMatch</code> matcha una stringa ed una regular expression e in caso di mismatch
     * aggiunge un messaggio di errore nella lista
     *
     * @param value stringa che si vuole validare
     * @param regexp regular expression da verificare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertMatch(String value, Pattern regexp,String msg){
        String param=request.getParameter(value);
        boolean condition=required(param)&&regexp.matcher(param).matches();
        return gatherError(condition,msg);
    }

    /**Il metodo <code>assertInt</code> valida se la stringa è costituita da un solo carattere intero
     *
     * @param value stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertInt(String value,String msg){
        return assertMatch(value, INT_PATTERN,msg);
    }

    /**Il metodo <code>assertDouble</code> valida se la stringa è costituita da un carattere double
     *
     * @param value stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertDouble(String value,String msg){
        return assertMatch(value,DOUBLE_PATTERN,msg);
    }

    /**Il metodo <code>assertEmail</code> valida se la stringa è una email
     *
     * @param value stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertEmail(String value,String msg){
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9.!#$%&’*+\\=?^_`{|}~-]{1,29}+@[a-zA-Z0-9-]{1,29}+(?:\\.[a-zA-Z0-9-]+){1,10}$");
        return assertMatch(value,pattern,msg);
    }

    /**Il metodo <code>assertInts</code> valida se la stringa è costituita da soli caratteri interi
     *
     * @param values stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertInts(String values,String msg){
        String[] params=request.getParameterValues(values);
        boolean allInt= Arrays.stream(params).allMatch(param->INT_PATTERN.matcher(param).matches());
        return gatherError(allInt,msg);
    }

    /**Il metodo <code>assertSize</code> valida se la lunghezza di due stringhe è uguale
     *
     * @param first prima stringa da validare
     * @param second seconda stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertSize(String first,String second,String msg){
        String[] firstList=request.getParameterValues(first);
        String[] secondList=request.getParameterValues(second);
        return gatherError(firstList.length==secondList.length,msg);
    }

    /**Il metodo <code>assertPassword</code> valida se la stringa ha i requisiti per essere usata come password
     *
     * @param value stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertPassword(String value,String msg){
        Pattern pattern=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}$");
        return assertMatch(value,pattern,msg);
    }

    /**Il metodo <code>assertIndirizzo</code> valida se la stringa ha i requisiti per essere usata come un indirizzo
     *
     * @param destinazione stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertIndirizzo(String destinazione, String msg) {
        Pattern pattern=Pattern.compile("^[a-zA-Z ]+[,][a-zA-Z ]*[0-9]{1,3}$");
        return assertMatch(destinazione,pattern,msg);
    }

    /**Il metodo <code>assertData</code> valida se la stringa ha i requisiti per essere usata come una data
     *
     * @param data stringa da validare
     * @param msg messaggio di errore da aggiungere alla lista in caso di mismatch
     * @return booleano che indica se un errore è stato aggiunto alla lista o meno
     */
    public boolean assertData(String data, String msg){
        Pattern pattern=Pattern.compile("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
        return assertMatch(data,pattern,msg);
    }
}

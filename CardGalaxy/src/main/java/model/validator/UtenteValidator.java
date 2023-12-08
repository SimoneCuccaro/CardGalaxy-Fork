package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class UtenteValidator {
    public static Validator validateUtente(HttpServletRequest request){
        Validator requestValidator=new Validator(request);
        requestValidator.assertMatch("name", Pattern.compile("^[A-Za-zà\\s]{2,20}$"),"name between 2 and 20 characters");
        requestValidator.assertMatch("surname",Pattern.compile("^[A-Za-zà\\s]{2,20}$"),"surname between 2 and 20 characters");
        requestValidator.assertMatch("user", Pattern.compile("^[\\s\\S]{2,20}$"),"surname between 2 and 20 characters");
        requestValidator.assertPassword("password","The password must be between 8 and 15 characters, including at least 1 uppercase letter, 1 lowercase letter, and 1 number, with no spaces. ");
        requestValidator.assertMatch("nation",Pattern.compile("^[A-Za-zà\\s]{2,30}$"),"country between 2 and 30 characters");
        requestValidator.assertMatch("city",Pattern.compile("^[A-Za-zà\\s]{2,30}$"),"city between 2 and 30 characters");
        requestValidator.assertMatch("address",Pattern.compile("^[\\s\\S]{2,30}$"),"street between 2 and 30 characters");
        requestValidator.assertMatch("cap",Pattern.compile("^[\\d]{5}$"),"the postal code must be 5 integers");
        return requestValidator;
    }
}


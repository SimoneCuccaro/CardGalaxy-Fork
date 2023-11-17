package model.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private final List<String> errors;
    private final HttpServletRequest request;
    private static final Pattern INT_PATTERN=Pattern.compile("^\\d+$");
    private static final Pattern DOUBLE_PATTERN=Pattern.compile("^\\d+(?:[.]\\d{1,2}|$)$");
    public Validator(HttpServletRequest request){
        this.errors=new ArrayList<>();
        this.request=request;
    }

    public boolean hasErrors(){return !errors.isEmpty();}

    public List<String> getErrors(){
        return errors;
    }

    public boolean gatherError(boolean condition,String message){
        if(condition){
            return true;
        }else{
            errors.add(message);
            return false;
        }
    }

    private boolean required(String value){return value!=null && !value.isBlank();}

    public boolean assertMatch(String value, Pattern regexp,String msg){
        String param=request.getParameter(value);
        boolean condition=required(param)&&regexp.matcher(param).matches();
        return gatherError(condition,msg);
    }

    public boolean assertInt(String value,String msg){
        return assertMatch(value, INT_PATTERN,msg);
    }

    public boolean assertDouble(String value,String msg){
        return assertMatch(value,DOUBLE_PATTERN,msg);
    }

    public boolean assertEmail(String value,String msg){
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        return assertMatch(value,pattern,msg);
    }

    public boolean assertInts(String values,String msg){
        String[] params=request.getParameterValues(values);
        boolean allInt= Arrays.stream(params).allMatch(param->INT_PATTERN.matcher(param).matches());
        return gatherError(allInt,msg);
    }

    public boolean assertSize(String first,String second,String msg){
        String[] firstList=request.getParameterValues(first);
        String[] secondList=request.getParameterValues(second);
        return gatherError(firstList.length==secondList.length,msg);
    }

    public boolean assertPassword(String value,String msg){
        Pattern pattern=Pattern.compile("^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$");
        return assertMatch(value,pattern,msg);
    }

    public boolean assertIndirizzo(String destinazione, String msg) {
        Pattern pattern=Pattern.compile("^[a-zA-Z ]+[,][a-zA-Z ]*[0-9]{1,3}$");
        return assertMatch(destinazione,pattern,msg);
    }

    public boolean assertData(String data, String msg){
        Pattern pattern=Pattern.compile("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
        return assertMatch(data,pattern,msg);
    }
}

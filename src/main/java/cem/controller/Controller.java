package cem.controller;

import cem.exceptions.AdditionException;
import cem.exceptions.CorredoresException;
import cem.model.Corredor;
import cem.model.Marxa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Controller {
    private static Controller controller;
    private Map<Integer, Marxa> marxes;
    private Map<String, Corredor> corredores;
    private final String REG_TLF = "^6[0-9]{8}$";
    private final String REG_MAIL = ".+@.+\\..+$";

    private Controller(){
        this.marxes = new HashMap<Integer, Marxa>();
        this.corredores = new HashMap<String,Corredor>();

    }

    public static Controller getInstance(){
        if (controller == null) {
            controller = new Controller();
        }
        return controller;

    }

    public Map<Integer,Marxa> getMarxes(){
        return marxes;
    }

    public void addMarxa(Integer i){
        marxes.put(i, new Marxa(i));
    }

    public boolean validateNif(String nif) {    //comprovación de nif, tien una formula para saver si es real o no
        Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
        String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};
        return Arrays.binarySearch(INVALIDOS, nif) < 0 // <1>
                && REGEXP.matcher(nif).matches() // <2>
                && nif.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(nif.substring(0, 8)) % 23);
    }

    public boolean validateEmail(String str){
        if (str.matches(REG_MAIL)){
            return true;
        }
        return false;

    }

    public boolean validateTlf(String str){
        if (str.matches(REG_TLF)){
            return true;
        }
        return false;
    }

    public void addCorredor(Corredor corredor) throws AdditionException{
        if (!corredores.containsKey(corredor.getNif())) {
            corredores.put(corredor.getNif(), corredor);
        }else{
            throw new AdditionException("Ya existeix aquest corredor");
        }
    }


}

package cem.controller;

import cem.exceptions.AdditionException;
import cem.exceptions.CorredoresException;
import cem.model.Participant;
import cem.model.Marxa;
import cem.model.TO.ParticipantEditionTO;
import cem.persistence.cemDAO;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Controller {
    private cemDAO dao;
    private static Controller controller;
    private Map<Integer, Marxa> marxes;
    private Map<String, Participant> corredores;
    private final String REG_TLF = "^6[0-9]{8}$";
    private final String REG_MAIL = ".+@.+\\..+$";

    private Controller(){
        this.marxes = new HashMap<Integer, Marxa>();
        this.corredores = new HashMap<String,Participant>();
        dao = new cemDAO();

    }

    public static Controller getInstance(){
        if (controller == null) {
            controller = new Controller();
        }
        return controller;

    }

    public ArrayList<ParticipantEditionTO> getMarxes() throws SQLException{
        return dao.getMarxes();
    }

    // añade una marxa al hashmap
    public void addMarxa(Marxa m) throws SQLException, AdditionException{
        if (dao.existMarxa(m)) {
            throw new AdditionException("Ja existeix una marxa amb aquesta edició.");
        }
        dao.insertMarxa(m);
    }
    
    public boolean existMarxa(Marxa m) throws SQLException, AdditionException{
        if (dao.existMarxa(m)) {
            return true;
        }
        return false;
    }


    //metodo que valida que lo que ponga el usuario es un nif valido
    public boolean validateNif(String nif) {    //comprovación de nif, tien una formula para saver si es real o no
        Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
        String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};
        return Arrays.binarySearch(INVALIDOS, nif) < 0 // <1>
                && REGEXP.matcher(nif).matches() // <2>
                && nif.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(nif.substring(0, 8)) % 23);
    }

    //metodo que valida que lo que ponga el usuario es un email valido
    public boolean validateEmail(String str){
        if (str.matches(REG_MAIL)){
            return true;
        }
        return false;

    }

    //metodo que valida que lo que ponga el usuario es un numero de telefono valido
    public boolean validateTlf(String str){
        if (str.matches(REG_TLF)){
            return true;
        }
        return false;
    }

    // añade un corredor al hashmap
    public void addParticipant(Participant corredor) throws AdditionException{
        if (!corredores.containsKey(corredor.getNif())) {
            corredores.put(corredor.getNif(), corredor);
        }else{
            throw new AdditionException("Ya existeix aquest participant");
        }
    }


}

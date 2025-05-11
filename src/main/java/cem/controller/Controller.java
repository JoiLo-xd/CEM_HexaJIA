package cem.controller;

import cem.exceptions.AdditionException;
import cem.exceptions.CorredoresException;
import cem.model.Inscripcio;
import cem.model.Participant;
import cem.model.Marxa;
import cem.model.TO.InscripcionsRanking;
import cem.model.TO.ParticipantEditionTO;
import cem.model.TO.StatsMarxesTO;
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
    private final String REG_TLF = "^6[0-9]{8}$";
    private final String REG_MAIL = ".+@.+\\..+$";

    private Controller(){
        dao = new cemDAO();

    }

    public static Controller getInstance(){
        if (controller == null) {
            controller = new Controller();
        }
        return controller;

    }
    
    public Participant getParticipant(String dni) throws AdditionException{
        try {
            if (existParticipantforDNI(dni)) {
                return dao.getParticipant(dni);
            } else {
                throw new AdditionException("Aquesta persona no existeix");
            }
        } catch (SQLException e) {
            System.out.println("Este error no se deberia dar");
        }
        return null; //nunca llega aqui btw
    }
    public Inscripcio getInscripcio(String dni,  String edicio) throws AdditionException{
        try {
            if (dao.existParticipantinInscripcio(dni, Integer.parseInt(edicio))) {
                return dao.getInscripcio(dni, edicio);
            } else {
                throw new AdditionException("Aquesta persona no està registrada en aquesta marxa");
            }
        } catch (SQLException e) {
            System.out.println("Este error no se deberia dar");
        }
        return null; //nunca llega aqui btw
    }
    

    public ArrayList<ParticipantEditionTO> getMarxes() throws SQLException{
        return dao.getMarxes();
    }
    
    public ArrayList<InscripcionsRanking> getInscripcions(int edicio) throws SQLException{
        return dao.getInscripcions(edicio);
    }
    
    public ArrayList<StatsMarxesTO> getStatsMarxes() throws SQLException{
        return dao.getStatsMarxes();
    }

    public void addMarxa(Marxa m) throws SQLException, AdditionException{
        if (dao.existMarxa(m)) {
            throw new AdditionException("Ja existeix una marxa amb aquesta edició.");
        }
        dao.insertMarxa(m);
    }
    
    public boolean existMarxa(Marxa m) throws SQLException{
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
        return str.matches(REG_TLF);
    }
    
    public void addParticipant(Participant corredor) throws SQLException,AdditionException{
        if (!dao.existParticipant(corredor)){
            dao.insertParticipant(corredor);
        }else{
             throw new AdditionException("Ja existeix un participant amb aquest nie");
        }
    }

    
    public void addInscripcio(Inscripcio inscripcio) throws SQLException,AdditionException{
        if (!dao.existInscripcio(inscripcio)){
            dao.insertInscripcio(inscripcio);
        }else{
             throw new AdditionException("Ja existeix una inscripcio amb aquest dorsal");
        }
    }
    
    public boolean existParticipantinInscripcio(String dni, int edicio) throws SQLException{
        if(dao.existParticipantinInscripcio(dni, edicio)){
            return true;
        } else { return false;}
    }
    public boolean existParticipantforDNI(String dni) throws SQLException{
        if(dao.existParticipantforDNI(dni)){
            return true;
        } else { return false;}
    }
        
        public boolean existDorsal(int dorsal, int edicio) throws SQLException{
        if(dao.existDorsal(dorsal, edicio)){
            return true;
        } else { return false;}
    }
        
        
    
    public void modifiParticipant(Participant as){
        try{
        dao.modifiParticipant(as);
        }catch (SQLException e){
            System.out.println("No se deberia dar este error" + e.getMessage());
        }
    }
    
    public void modifiInscripcio(Inscripcio in){
        try{
        dao.modifiInscripcio(in);
        }catch (SQLException e){
            System.out.println("No se deberia dar este error" + e.getMessage());
        }
    }




}

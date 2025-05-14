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
import java.time.LocalTime;
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

    //metodo para poder usar los metodos del fichero cemDAO      
    private Controller() {
        dao = new cemDAO();
    }

    //para poder usar el controlador y siempre haya uno
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    //metodo que recoge un string para saber si existe un participante con ese DNI, si existe llama a un metodo del DAO
    public Participant getParticipant(String dni) throws AdditionException {
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

    //metodo que recive dos stringpara pasarlos a un metodo del DAO
    public void putTimes(String dorsal, String edicio) {
        try {
            LocalTime temps_actual = LocalTime.now();
            dao.setTimesInscripcio(Integer.parseInt(dorsal), Integer.parseInt(edicio), temps_actual);
        } catch (SQLException e) {
            System.out.println("Aquest error no es hauria de donar " + e.getMessage());
        }
    }

    //metodo que recive dos stringpara pasarlos a un metodo del DAO
    public void putTimesa(String dorsal, String edicio) {
        try {
            LocalTime temps_actual = LocalTime.now();
            dao.setTimeaIncripcio(Integer.parseInt(dorsal), Integer.parseInt(edicio), temps_actual);

        } catch (SQLException e) {
            System.out.println("Aquest error no es hauria de donar " + e.getMessage());
        }
    }

    //metodo que devuelve un LocalTime (o null) si el dorsal y la edicion que recive esta bien
    public LocalTime gettimesbyDorsal(String dorsal, String edicio) {
        try {
            return dao.getTimebyDorsal(Integer.parseInt(dorsal), Integer.parseInt(edicio));

        } catch (SQLException e) {
            System.out.println("Este error no deberia salir: " + e.getMessage());
        }
        return null;
    }

    //metodo que devuelve una inscripcion a traves de dos string, sirve para saber si existe o no una inscripcion
    public Inscripcio getInscripcio(String dni, String edicio) throws AdditionException {
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

    //devuelve el getMarxes del cemDAO
    public ArrayList<ParticipantEditionTO> getMarxes() throws SQLException {
        return dao.getMarxes();
    }

    //devuelve el getInscripcions del cemDAO
    public ArrayList<InscripcionsRanking> getInscripcions(int edicio, int opcio) throws SQLException {
        return dao.getInscripcions(edicio, opcio);
    }

    //devuelve el getSTatsMarxes del cemDAO
    public ArrayList<StatsMarxesTO> getStatsMarxes() throws SQLException {
        return dao.getStatsMarxes();
    }

    // sirve para añadir una marxa y valida si existe o no
    public void addMarxa(Marxa m) throws SQLException, AdditionException {
        if (dao.existMarxa(m)) {
            throw new AdditionException("Ja existeix una marxa amb aquesta edició.");
        }
        dao.insertMarxa(m);
    }

    //metodo para saber si una marxa existe o no
    public boolean existMarxa(Marxa m) {
        try {

            if (dao.existMarxa(m)) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Este error no se deberia dar: " + e.getMessage());
        }
        return false;
    }

    //metodo que valida si existe un dorsal de una edicion o no
    public boolean isInscripcio(String dorsal, String edicio) {
        try {

            return dao.existDorsal(Integer.parseInt(dorsal), Integer.parseInt(edicio));
        } catch (SQLException e) {
            System.out.println("Este error no deberia ocurrir: " + e.getMessage());
        }
        return false;
    }

    // metofo que devuelvebun dni a traves de recivir un STring de dorsal y otro de edicion
    public String getDNIInscripcioByDorsal(String dorsal, String edicio) {
        try {
            return dao.getDNIIns(Integer.parseInt(dorsal), Integer.parseInt(edicio));
        } catch (SQLException e) {
            System.out.println("Este error no deberia ocurrir " + e.getMessage());
        }
        return "";
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
    public boolean validateEmail(String str) {
        if (str.matches(REG_MAIL)) {
            return true;
        }
        return false;

    }

    //metodo que valida que lo que ponga el usuario es un numero de telefono valido
    public boolean validateTlf(String str) {
        return str.matches(REG_TLF);
    }

    //metodo que añade un participante en el caso de que no estuviera registrado
    public void addParticipant(Participant corredor) throws SQLException, AdditionException {
        if (!dao.existParticipant(corredor)) {
            dao.insertParticipant(corredor);
        } else {
            throw new AdditionException("Ja existeix un participant amb aquest nie");
        }
    }

    //metodo que añade una inscripcion en el caso de que no estuviera registrada
    public void addInscripcio(Inscripcio inscripcio) throws SQLException, AdditionException {
        if (!dao.existInscripcio(inscripcio)) {
            dao.insertInscripcio(inscripcio);
        } else {
            throw new AdditionException("Ja existeix una inscripcio amb aquest dorsal");
        }
    }

    //metodo que valida si existe un participante en una edicion en concretoi
    public boolean existParticipantinInscripcio(String dni, int edicio) throws SQLException {
        if (dao.existParticipantinInscripcio(dni, edicio)) {
            return true;
        } else {
            return false;
        }
    }

    //metodo que valida si existe un participante a traves de recivir un String (dni)
    public boolean existParticipantforDNI(String dni) throws SQLException {
        if (dao.existParticipantforDNI(dni)) {
            return true;
        } else {
            return false;
        }
    }

    //metodo que valida si existe el dorsal que se le pasa como parametro en una edicion en concreto
    public boolean existDorsal(int dorsal, int edicio) throws SQLException {
        if (dao.existDorsal(dorsal, edicio)) {
            return true;
        } else {
            return false;
        }
    }

    //metodo que llama al modificarParticipant del cemDAO
    public void modifiParticipant(Participant as) {
        try {
            dao.modifiParticipant(as);
        } catch (SQLException e) {
            System.out.println("No se deberia dar este error" + e.getMessage());
        }
    }

    //metodo que llama al modifiInscripcio del cemDAO
    public void modifiInscripcio(Inscripcio in) {
        try {
            dao.modifiInscripcio(in);
        } catch (SQLException e) {
            System.out.println("No se deberia dar este error" + e.getMessage());
        }    
    }
    
}

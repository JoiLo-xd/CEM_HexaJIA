/*
 * Clase auxiliar para pedir datos al usuario
 */
package cem.view;

import cem.enums.Asistencia;
import cem.enums.Sexe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.time.LocalDate;

/**
 * @author HexaJIA
 */
//AAAAAAAAAAAAAAAAA

public class AskDataCEM {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String REG_TLF = "^6[0-9]{8}$";
    private static final String REG_MAIL = ".+@.+\\..+$";

    public static String askString(String msg) {
        System.out.print(msg);
        String answer = "";
        try {
            answer = br.readLine();
            while (answer.isEmpty()) {
                System.out.println("\nNo puedes dejar la respuesta en blanco\n");
                System.out.print(msg);
                answer = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error inesperado.");
        }
        return answer;
    }

    public static String askString(String msg, int size) {
        String answer;
        do {
            answer = askString(msg);
            if (answer.length() != size) {
                System.out.println("The length of the string must be " + size);
            }
        }while(answer.length() != size);
        return answer;
    }

    public static String askString(String msg, ArrayList<String> stringsAccepted) {
        String answer;
        boolean ok = false;
        do {
            answer = askString(msg);
            ok = stringsAccepted.contains(answer.toLowerCase());
            if (!ok) {
                System.out.println("Wrong answer");
                System.out.println("Your answer must be: ");
                for (String s: stringsAccepted) {
                    System.out.println(s);
                }
            }
        }while(!ok);
        return answer;
    }

    public static Asistencia askAsistencia(String msg, String errorMsg) {
        Asistencia a = null;
        try {
            String asistencia = askString(msg).toUpperCase();
            a = Asistencia.valueOf(asistencia);
        }catch (IllegalArgumentException e) {
            System.out.println(errorMsg);
        }
        return a;
    }

    public static LocalDate askFecha(String msg, String formato) {
        boolean error = false;
        LocalDate date = null;
        do {
            try {
                String fecha = askString(msg);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
                date = LocalDate.parse(fecha, formatter);
                error = false;
            }catch (DateTimeParseException e) {
                System.out.println("Fecha incorrecta.");
                error = true;
            }
        } while (error);
        return date;
    }

    public static Sexe askSexe(String msg) {
        Sexe s = null;
        boolean error = false;
        do {
            error = false;
            try {
                String sexe = askString(msg).toUpperCase();
                s = Sexe.valueOf(sexe);
            } catch (IllegalArgumentException e) {
                System.out.println("Valor incorrecte.");
                error = true;
            }
        } while (error);
        return s;
    }


    public static String askTelf(String str) {
        String valor;
        do{
            valor = askString(str);
            if (!valor.matches(REG_TLF)){
                System.out.println("Aquest numero de telefon no es valid");
            }
        }while(!valor.matches(REG_TLF));
        return valor;
    }

    public static String askEmail(String str) {
        String valor;
        do{
            valor = askString(str);
            if (!valor.matches(REG_MAIL)){
                System.out.println("Aquest email no es valid");
            }
        }while(!valor.matches(REG_MAIL));
        return valor;
    }

    public static int askInt(String msg) {
        int n = 0;
        boolean error = false;
        do {
            try {
                System.out.print(msg);
                n = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Se ha producido un error no esperado!!");
                error = true; // Este no se dará nunca.
            } catch (NumberFormatException e) {
                System.out.println("Tienes que poner un número entero");
                error = true;
            }
        } while (error);
        return n;
    }

    public static int askInt(String msg, String errorMsg, int min) {
        int n = askInt(msg);
        while (n < min) {
            System.out.println(errorMsg);
            n = askInt(msg);
        }
        return n;
    }

    public static int askInt(String msg, String errorMsg, int min, int max) {
        int n = askInt(msg);
        while (n < min || n > max) {
            System.out.println(errorMsg);
            n = askInt(msg);
        }
        return n;
    }

    public static double askDouble(String msg) {
        double n = 0;
        boolean error = false;
        do {
            try {
                System.out.print(msg);
                n = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Se ha producido un error no esperado!!");
                error = true; // Este no se dará nunca.
            } catch (NumberFormatException e) {
                System.out.println("Tienes que poner un número entero");
                error = true;
            }
        } while (error);
        return n;
    }

    public static double askDouble(String msg, String errorMsg, double min) {
        double n = askDouble(msg);
        while (n < min) {
            System.out.println(errorMsg);
            n = askDouble(msg);
        }
        return n;
    }

    public static double askDouble(String msg, String errorMsg, double min, double max) {
        double n = askDouble(msg);
        while (n < min || n > max) {
            System.out.println(errorMsg);
            n = askDouble(msg);
        }
        return n;
    }

    public static boolean askBoolean(String msg, String errorMsg, String stringTrue, String stringFalse) {
        String answer;
        do {
            answer = askString(msg);
            if (!answer.equalsIgnoreCase(stringTrue) && !answer.equalsIgnoreCase(stringFalse)) {
                System.out.println(errorMsg);
            }
        } while (!answer.equalsIgnoreCase(stringTrue) && !answer.equalsIgnoreCase(stringFalse));
        return answer.equalsIgnoreCase(stringTrue);
    }

    public static String askNif(String msg) {       //ask nif method to check if it's correct
        String answer;
        boolean ok = false;
        do {
            answer = askString(msg);
            ok = validateNif(answer.toUpperCase());
            if (!ok) {
                System.out.println("Tienes que introducir un Nif correcto.");
            }
        } while (!ok);
        return answer;
    }

    public String askNifUnaVez() {
        String nif = "";
        boolean correcto = false;
        while (!correcto) { //correcto == false
            nif = askString("NIF del propietari de la casa: ").toUpperCase();
            correcto = validateNif(nif);
            if (!correcto) {
                System.out.println("Debes poner un nif");
            }
        }
        return nif;
    }

    private static boolean validateNif(String nif) {    //comprovación de nif, tien una formula para saver si es real o no
        Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
        String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};
        return Arrays.binarySearch(INVALIDOS, nif) < 0 // <1>
                && REGEXP.matcher(nif).matches() // <2>
                && nif.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(nif.substring(0, 8)) % 23);
    }
}

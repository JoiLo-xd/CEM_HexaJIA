package cem.view;

import cem.enums.Sexe;
import cem.model.Corredor;
import cem.model.Inscripcio;
import cem.model.Marxa;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Marxa> marxes;
    private static ArrayList<Corredor> corredores;
    private static final String MENUMARXES = "\n<> MENU MARXES <>\n" +
            "1. Afegir participant\n" +
            "2. Editar participant\n" +
            "3. Sortida\n" +
            "4. Arribada\n" +
            "5. Corredors de la marxa\n" +
            "6. Sortir";
    private static final String MENUINICI = "\n-- MENU PRINCIPAL --\n" +
            "1. Marxes\n" +
            "2. Crear Marxa\n" +
            "3. Alta esportista\n" +
            "4. Modificar esportista\n" +
            "5. Mostrar estadístiques\n" +
            "6. Sortir";

    public static void main(String[] args) {
        marxes = new ArrayList<>();
        int option;
        do {
            System.out.println(MENUINICI);;
            option = AskDataCEM.askInt("Que vols fer?", "Posa una opció correcta.", 1, 6);

            // NOTA IMPORTANTE:
            // NO me parece buena idea que por ejemplo para que un corredor empiece a correr tengamos que poner la edicion
            // de la maraxa to el rato, ya que no es eficiente, deberia ser que tu estas dentro de una edicion y vas idicando los jugadores
            // que salen que obv ya se han inscrito.

            // Imaginate que por cada persona que sale tienes que indicar de que año es (la edicion) like:
            // si sale la inscripcion con dorsal 1200120 de la edicion 2021
            // si sale la inscripcion con dorsal 1200121 de la edicion 2021
            // si sale la inscripcion con dorsal 1200122 de la edicion 2021
            // como que hay un patron comun no? jeje

            switch (option) {
                case 1 -> {     //entrar en marxa
                    if (!marxes.isEmpty()) {
                        showMarxes();
                        int chosen;
                        if ((chosen = escollidor(marxes.size())) != 0 ){
                            entrarMarxa(marxes.get(chosen));
                        }

                    }else{
                        System.out.println("No hi han curses disponibles, crea una.");
                    }

                }
                case 2 -> {     //crear marxa
                    darAltaMarxa();
                    break;
                }
                case 3 -> {     //crear corredor
                    darAltaCorredor();
                    break;
                }
                case 4 -> {     //Modificar corredor
                    break;
                }
                case 5 -> {     //mostrar stats
                    break;
                }
                case 6 -> {     //salir
                    System.out.println("Fins avia't!!");
                    break;
                }
            }
        }while (option != 6);

    }

    public static int escollidor(int num){
        int chosen = AskDataCEM.askInt("Escull (0) per sortir: ", "Aquest no esta disponible", 0, num);
        if (chosen > 0){
            return chosen -1;
        }else{
            return 0;
        }
    }
    public static void showMarxes(){
        int cont = 1;
        for (Marxa element : marxes){
            System.out.println(cont +"  " + element.getEdicio());
        }
    }

    private static void entrarMarxa(Marxa escollida) {
        int option;
        do {

            System.out.println(MENUMARXES);
            option = AskDataCEM.askInt("Que vols fer?", "Posa una opció correcta.", 1, 6);
            switch (option) {
                case 1 -> {     //añadir inscripción
                    break;
                }
                case 2 -> {     //Modificar inscripción
                    //todo hay que preguntar que inscripcion es y preguntar que quiere modificar :)
                    break;
                }
                case 3 -> {     //Hora salida inscripción
                    //todo hay que preguntar que inscripcion es y solo llamar a la funcion de askTime en AskDataCEM
                    break;
                }
                case 4 -> {     //Hora llegada inscripción
                    //todo hay que preguntar que inscripcion es y solo llamar a la funcion de askTime en AskDataCEM
                    break;
                }
                case 5 -> {     //Mostrar corredores de la marxa
                    break;
                }
                case 6 -> {     //salir
                    break;
                }
            }
        } while (option != 6);
    }

    public static void crearInscripcion(Marxa escollida) {
        //int dorsal, bool modalitat, corredor
        Boolean modalitat = AskDataCEM.askBoolean("Modalitat de la cursa (Llarga - Curta):", "Selecciona una opció correcte.", "Llarga", "Curta");
        //pedrir nif    (no lo he puesto porque en el otro me peta)       //todo :)
        Corredor corredor = convertirAcorredor(nif);
        escollida.addCorrInsc(new Inscripcio(escollida.getInscripcionsMarxa().size()+1, modalitat, corredor));
    }

    public static void darAltaCorredor() {
        String nif;
        if (corredores.contains(new Corredor(nif = AskDataCEM.askNif("DNI/NIF: ")))){
            System.out.println("Aquesta persona ja esta reistrada");
        }else {
            String nom = AskDataCEM.askString("Nom: ");
            String cognoms = AskDataCEM.askString("Cognoms: ");
            LocalDate dataNaix = AskDataCEM.askFecha("Data naixement: ", "dd-MMM-YYYY");
            Sexe sexe = AskDataCEM.askSexe("Sexe: ");
            String poblacio = AskDataCEM.askString("Població: ");
            String telf = AskDataCEM.askTelf();
            String email = AskDataCEM.askEmail();
            String entitat = AskDataCEM.askString("Entitat: ");
            Boolean federat = AskDataCEM.askBoolean("Federat: ", "Valor incorrecte", "Si", "No");
            corredores.add(new Corredor(nif, nom, cognoms, dataNaix, sexe, poblacio, telf, email, entitat, federat));
        }
    }

    public static void darAltaMarxa() {
        int edicion = AskDataCEM.askInt("Digues l'edició de la cursa: ", "Aquesta edició no es valida", 2000);
        if (marxes.contains(new Marxa(edicion))) {
            System.out.println("Aquesta edició ja estava registrada.");
        } else {
            marxes.add(new Marxa(edicion));

        }
        System.out.println();
    }



    private static Corredor convertirAcorredor(String n){
        for (Corredor c : corredores){
            if (c.getNif().equalsIgnoreCase(n)){
                return c;
            }
        } return null;
    }

}
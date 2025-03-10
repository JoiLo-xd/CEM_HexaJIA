package cem.view;

import cem.enums.Sexe;
import cem.model.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import cem.persistence.Persis;

public class Main {

    private static ArrayList<Marxa> marxes;
    private static ArrayList<Corredor> corredores;
    private static Persis persis;

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

    private static final String MENUMODCORR = "\n-- MODIFICAR CORREDOR --\n"+
            "Quina dada vols modificar:\n" +
            "1. Nom\n" +
            "2. Cognoms\n" +
            "3. Sexe\n" +
            "4. Població\n" +
            "5. Numero Telefon\n" +
            "6. Mail\n" +
            "7. Entitat\n" +
            "8. Federat\n" +
            "9. Cancelar\n";

    public static void main(String[] args){
        marxes = new ArrayList<>();
        corredores = new ArrayList<>();
        try {
            persis = new Persis();
            corredores = persis.readCorredor();
            marxes = persis.readMarxa(corredores);

            int option;
            do {
                System.out.println(MENUINICI);
                ;
                option = AskDataCEM.askInt("Que vols fer?: ", "Posa una opció correcta.", 1, 6);

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
                            if ((chosen = escollidor(marxes.size())) != 0) {
                                entrarMarxa(marxes.get(chosen - 1));
                            }

                        } else {
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
                        mdfCorredor();
                    }
                    case 5 -> {     //mostrar stats
                        mostrarStats();

                    }
                    case 6 -> {     //salir
                        System.out.println("Fins avia't!!");

                    }
                }
            } while (option != 6);
        }catch (IOException e){
            System.out.println("Hi ha agut un problema fatal, l'error es: " + e.getMessage());
        }

    }

    public static int escollidor(int num){
        int chosen = AskDataCEM.askInt("Escull (0) per sortir: ", "Aquest no esta disponible", 0, num);
        if (chosen > 0){
            return chosen;
        }else{
            return 0;
        }
    }
    public static void showMarxes(){
        int cont = 0;
        for (Marxa element : marxes){
            cont++;
            System.out.println(cont +"  " + element.getEdicio());
        }
    }

    private static void entrarMarxa(Marxa escollida) throws IOException {
        int option;
        do {

            System.out.println(MENUMARXES);
            option = AskDataCEM.askInt("Que vols fer?: ", "Posa una opció correcta.", 1, 6);
            switch (option) {
                case 1 -> {     //añadir inscripción
                    crearInscripcion(escollida);

                }
                case 2 -> {     //Modificar inscripción
                    mdfInscrip(escollida);

                }
                case 3 -> {     //Hora salida inscripción
                    horaSortidaInscrip(escollida);

                }
                case 4 -> {     //Hora llegada inscripción
                    horaArribadaInscrip(escollida);

                }
                case 5 -> {     //Mostrar corredores de la marxa
                    showRunners(escollida);

                }
                case 6 -> {     //salir
                    System.out.println("Adeu!!!");;
                }
            }
        } while (option != 6);
    }

    public static void showRunners(Marxa escollida) {
        if (escollida.getInscripcionsMarxa().isEmpty()) {
            System.out.println("No hi ha cap inscripció.");
        } else {
            System.out.println("EDICIÓ " + escollida.getEdicio());
            for (Inscripcio i: escollida.getInscripcionsMarxa()) {
                System.out.println(i);
            }
        }
    }

    public static void horaSortidaInscrip(Marxa escollida) {        //todo revisar
        if (escollida.getInscripcionsMarxa().isEmpty()) {
            System.out.println("Aquesta marxa no té inscripcions");
        } else {
            int choosen = AskDataCEM.askInt("Indica el dorsal del corredor", "Aquest dorsal no és vàlid", 0, escollida.getInscripcionsMarxa().size());
            Inscripcio inscripcioElegida = null;
            for (Inscripcio inscripcio : escollida.getInscripcionsMarxa()) {
                if (inscripcio.getDorsal() == choosen) {
                    inscripcioElegida = inscripcio;
                    break;
                }
            }
            if (inscripcioElegida != null) {
                if (inscripcioElegida.getHoraSortida() == null) {
                    inscripcioElegida.setHoraSortida(LocalTime.now());
                } else {
                    boolean i = AskDataCEM.askBoolean("Ja hi ha valor per a la sortida, vols sobrescriure'l? ", "Digues sí o no.", "SI", "NO");
                    if (i) {
                        inscripcioElegida.setHoraSortida(LocalTime.now());
                    }
                }
            } else {
                System.out.println("Aquest corredor no existeix");
            }
        }
    }

    public static void horaArribadaInscrip(Marxa escollida) {        //todo revisar
        if(escollida.getInscripcionsMarxa().isEmpty()){
            System.out.println("Aquesta marxa no te incscripcion");
        }else {
            int choosen;
            ArrayList<Inscripcio> inscrchoosen = escollida.getInscripcionsMarxa();
            if (inscrchoosen.contains(new Inscripcio(choosen = AskDataCEM.askInt("Indica el dorsal del corredor", "Aquest dorsal no es valid", 0, inscrchoosen.size())))){
                if (inscrchoosen.get(inscrchoosen.indexOf(choosen)).getHoraArribada() == null) {
                    inscrchoosen.get(inscrchoosen.indexOf(choosen)).setHoraArribada(LocalTime.now());
                } else {
                    boolean i = AskDataCEM.askBoolean("Ja hi ha valor per a la sortida, vols sobrescriure'l?", "digues si o no.", "SI", "NO");
                    if (i) {
                        inscrchoosen.get(inscrchoosen.indexOf(choosen)).setHoraArribada(LocalTime.now());
                    }
                }
            }else{
                System.out.println("Aquest corredor no existeix");

            }
        }
    }

    public static void mdfInscrip(Marxa escollida){
        if(escollida.getInscripcionsMarxa().isEmpty()){
            System.out.println("Aquesta marxa no te incscripcion");
        }else{
            int choosen;
            ArrayList<Inscripcio> inscrchoosen = escollida.getInscripcionsMarxa();
            if (inscrchoosen.contains(new Inscripcio(choosen = AskDataCEM.askInt("Indica el dorsal del corredor: ", "Aquest dorsal no es valid", 0,  inscrchoosen.size())))){
                int opcio = AskDataCEM.askInt("Que vols cambiar:\n" +
                        "1. Modalitat\n" +
                        "2. Asistencia\n"+
                        "3. Cancelar\n","posa una opció valida",1,3);

                switch (opcio){
                    case 1 ->{
                        inscrchoosen.get(choosen -1).setModalitat(AskDataCEM.askBoolean("Nova modalitat de la cursa que realitza el corredor (Llarga - Curta):", "Selecciona una opció correcte.", "Llarga", "Curta"));

                    }
                    case 2 ->{
                        inscrchoosen.get(choosen -1).setAsistencia(AskDataCEM.askAsistencia("Asistencia del corredor (asistencia - noAsistencia - abandona - desqualificat): ", "Valor incorrecte."));
                    }
                    case 3 -> {
                        System.out.println("No s'ha modificat cap data");
                    }
                }

            }else{
                System.out.println("Este corredor no esta registrado");
            }

        }

    }

    public static void crearInscripcion(Marxa escollida) throws IOException {
        if (corredores.isEmpty()) {
            System.out.println("No hi han corredors per inscribir, crea algun");
        } else {
            //Preguntar si es mejor asi o con la funcion
            String nif = AskDataCEM.askNif("Indica el nif del corredor: ");
            if (corredores.contains(new Corredor(nif))) {
                int index = corredores.indexOf(new Corredor(nif));
                if (index >= 0) {
                    Corredor c = corredores.get(index);
                    if (comprCorredor(escollida,c)) {
                        Boolean modalitat = AskDataCEM.askBoolean("Modalitat de la cursa que fara el corredor (Llarga - Curta):", "Selecciona una opció correcte.", "Llarga", "Curta");
                        Inscripcio novaInscr = new Inscripcio(escollida.getInscripcionsMarxa().size() + 1, modalitat, c);
                        escollida.addCorrInsc(novaInscr);
                        persis.writerInscripcioInFile(novaInscr, escollida.getEdicio());
                    }else{
                        System.out.println("Aquest corredor ja esta en la marxa");
                    }
                }
            } else {
                System.out.println("No hi han corredors amb aquest nif");
            }
        }
    }

    public static boolean comprCorredor(Marxa m, Corredor c){
        for (Inscripcio i : m.getInscripcionsMarxa()){
            Corredor r = i.getCorredor();
            if (r.equals(c)) return false;
        }
        return true;
    }

    public static void darAltaCorredor() throws IOException {
        if (corredores == null) {
            corredores = new ArrayList<>();
        }
        String nif;
        if (corredores.contains(new Corredor(nif = AskDataCEM.askNif("DNI/NIF: ")))){
            System.out.println("Aquesta persona ja esta reistrada");
        }else {
            String nom = AskDataCEM.askString("Nom: ");
            String cognoms = AskDataCEM.askString("Cognoms: ");
            LocalDate dataNaix = AskDataCEM.askFecha("Data naixement (dia-mes-any): ", "dd-MM-yyyy");
            Sexe sexe = AskDataCEM.askSexe("Sexe (home - dona - altre): ");
            String poblacio = AskDataCEM.askString("Població: ");
            String telf = AskDataCEM.askTelf("Telefon: ");
            String email = AskDataCEM.askEmail("Mail: ");
            String entitat = AskDataCEM.askString("Entitat: ");
            Boolean federat = AskDataCEM.askBoolean("Federat (si - no): ", "Valor incorrecte", "Si", "No");
            Corredor c = new Corredor(nif, nom, cognoms, dataNaix, sexe, poblacio, telf, email, entitat, federat);
            corredores.add(c);
            persis.writerCorredorInFile(c);        }
    }

    public static void darAltaMarxa() throws IOException {
        int edicion = AskDataCEM.askInt("Digues l'edició de la cursa: ", "Aquesta edició no es valida", 2000);
        if (marxes.contains(new Marxa(edicion))) {
            System.out.println("Aquesta edició ja estava registrada.");
        } else {
            Marxa m = new Marxa(edicion);
            marxes.add(m);
            persis.writerMarxaInFile(m);
        }
        System.out.println();
    }

    private static void mdfCorredor() throws IOException {
        if (corredores.isEmpty()) {
            System.out.println("No hi han corredors per inscribir, crea algun");
        } else {
            String nif = AskDataCEM.askNif("Indica el nif del corredor: ");
            if (corredores.contains(new Corredor(nif))) {
                //Corredor runner = corredores.get(corredores.indexOf(nif));
                int index = corredores.indexOf(new Corredor(nif));
                if (index >= 0) {
                    Corredor runner = corredores.get(index);
                    System.out.println(MENUMODCORR);
                    int option = AskDataCEM.askInt("Que vols fer?", "Posa una opció correcta.", 1, 9);
                    switch (option) {
                        case 1 -> {
                            runner.setNom(AskDataCEM.askString("Indica el nou nom del corredor: "));

                        }
                        case 2 -> {
                            runner.setCognoms(AskDataCEM.askString("Indica el nou cognom del corredor: "));

                        }
                        case 3 -> {
                            runner.setSexe(AskDataCEM.askSexe("Indica el nou 'sexe' del corredor: "));

                        }
                        case 4 -> {
                            runner.setPoblacio(AskDataCEM.askString("Indica la nova població del corredor: "));

                        }
                        case 5 -> {
                            runner.setNumTelefon(AskDataCEM.askTelf("Indica el nou telefon del corredor: "));

                        }
                        case 6 -> {
                            runner.setEmail(AskDataCEM.askEmail("Indica el nou Email del corredor: "));

                        }
                        case 7 -> {
                            runner.setEntitat(AskDataCEM.askString("Indica la nova entitat del corredor: "));

                        }
                        case 8 -> {
                            runner.setFederat(AskDataCEM.askBoolean("Indica si ets federat:\n1. Federat\n2. No federat\n", "Aquesta opcio no esta disponible", "1", "2"));

                        }
                        case 9 -> {
                            System.out.println("No has modificat ninguna data");

                        }
                    }
                    if (option != 9) persis.ReescribirCorredor(corredores);

                } else {
                    System.out.println("Aquest corredor no existeix");
                }
            }

        }
    }
    private static void mostrarStats(){
        if (corredores.isEmpty()){
            System.out.println("No hi han corredors per inscribir, crea un.");
        } else {
            for (Corredor c : corredores){
                System.out.println(c +"\n");
            }
        }
    }

}
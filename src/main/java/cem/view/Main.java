package cem.view;

import cem.model.Marxa;

import java.util.ArrayList;

public class Main {

    private static ArrayList<Marxa> marxes;

    public static void main(String[] args) {
        marxes = new ArrayList<>();
        int option;
        do {
            menuInicio();
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
                    entrarMarxa();
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

    private static void entrarMarxa() {
        int option;
        do {
            //hay que mostrar las marxas que hay creadas y si no hay ninguna pues decir que no hay ninguna aún creada
            if (marxes.isEmpty()) {
                System.out.println("No hi ha cap marxa registrada.");
            } else {
                for (int i = 0; i < marxes.size(); i++) {
                    System.out.println(i);
                }
            }
            menuMarxa();
            option = AskDataCEM.askInt("Que vols fer?", "Posa una opció correcta.", 1, 6);
            switch (option) {
                case 1 -> {     //añadir inscripción
                    break;
                }
                case 2 -> {     //Modificar inscripción
                    break;
                }
                case 3 -> {     //Hora salida inscripción
                    break;
                }
                case 4 -> {     //Hora llegada inscripción
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

    public static void darAltaCorredor() {
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

    private static void menuInicio() {    //menu Inici output
        System.out.println("\n-- MENU PRINCIPAL --\n" +
                "1. Marxes\n" +
                "2. Crear Marxa\n" +
                "3. Alta esportista\n" +
                "4. Modificar esportista\n" +
                "5. Mostrar estadístiques\n" +
                "6. Sortir");
    }

    private static void menuMarxa() {    //menu Marxes output
        System.out.println("\n<> MENU MARXES <>\n" +
                "1. Afegir participant\n" +
                "2. Editar participant\n" +
                "3. Sortida\n" +
                "4. Arribada\n" +
                "5. Corredors de la marxa\n" +
                "6. Sortir");
    }

}
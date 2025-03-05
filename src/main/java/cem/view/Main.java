package cem.view;

import cem.model.Marxa;

import java.util.ArrayList;

public class Main {

    private static ArrayList<Marxa> marxes;

    public static void main(String[] args) {
        marxes = new ArrayList<>();
        int option;
        do {
            menu();
            option = AskDataCEM.askInt("Que vols fer?", "Posa una opció correcta.", 1, 6);

            // NOTA IMPORTANTE:
            // NO me parece buena idea que por ejemplo para que un corredor empiece a correr tengamos que poner la edicion de la maraxa to el rato, ya que no es eficiente, deberia ser que tu estas dentro de una edicion y vas idicando los jugadores
            // que salen que obv ya se han incirito.
            // Imaginate que por cada persona que sale tienes que indicar de que año es (la edicion) like si sale l aiscripcion con dorsal 1200120 de la edicion 2021
                                                                                                        //si sale la incripcion con dorsal 1200121 de la edicion 2021
                                                                                                        //si sale la incripcion con dorsal 1200122 de la edicion 2021
            // como que hay un patron comun no? jeje

            switch (option) {   //NO
                case 1 -> {     //dar de alta marxa?
                    darAltaMarxa();
                }
                case 2 -> {     //dar de alta corredor?
                    break;
                }
                case 3 -> {   //dar de alta cursa?
                    break;
                }
                case 4 -> {     //modificar?
                    break;
                }
                case 5 -> {     //mostrar?
                    break;
                }
                case 6 -> {     //salir?
                    System.out.println("Fins avia't!!");
                    break;
                }
            }
        }while (option != 6);   //de momento creo que esto es lo que tenemos que hacer para la primera entrega

    }

    public static void darAltaMarxa(){
        marxes.add(new Marxa(AskDataCEM.askInt("Digues la edició de la cursa", "Aquesta edicio no es valida", 2000))); // minimo del año 2000 yokse, sin excepciones
    }

    private static void menu() {    //menu output
        System.out.println("\n## MENU ##\n" +
                "...\n");
    }

}
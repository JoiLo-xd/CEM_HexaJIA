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
            switch (option) {   //ya pondremos cada caso en su sitio esto es solo la base del menu
                case 1:     //dar de alta marxa?
                    break;
                case 2:     //dar de alta corredor?
                    break;
                case 3:     //dar de alta cursa?
                    break;
                case 4:     //modificar?
                    break;
                case 5:     //mostrar?
                    break;
                case 6:     //salir?
                    System.out.println("Fins avia't!!");
                    break;
            }
        }while (option != 6);   //de momento creo que esto es lo que tenemos que hacer para la primera entrega

    }

    private static void menu() {    //menu output
        System.out.println("\n## MENU ##\n" +
                "...\n");
    }

}
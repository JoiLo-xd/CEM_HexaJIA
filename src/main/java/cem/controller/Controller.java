package cem.controller;

import cem.model.Corredor;
import cem.model.Marxa;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private static Controller controller;
    private Map<Integer, Marxa> marxas;
    private Map<String, Corredor> corredores;

    private Controller(){
        this.marxas = new HashMap<Integer, Marxa>();
        this.corredores = new HashMap<String,Corredor>();

    }

    public static Controller getInstance(){
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
        }

}

package cem.controller;

import cem.model.Corredor;
import cem.model.Marxa;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private static Controller controller;
    private Map<Integer, Marxa> marxes;
    private Map<String, Corredor> corredores;

    private Controller(){
        this.marxes = new HashMap<Integer, Marxa>();
        this.corredores = new HashMap<String,Corredor>();

    }

    public static Controller getInstance(){
        if (controller == null) {
            controller = new Controller();
        }
        return controller;

    }

    public Map<Integer,Marxa> getMarxes(){
        return marxes;
    }

    public void addMarxa(Integer i){
        marxes.put(i, new Marxa(i));
    }


}

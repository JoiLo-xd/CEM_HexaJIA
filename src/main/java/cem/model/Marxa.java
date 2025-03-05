package cem.model;

import java.util.ArrayList;

public class Marxa {
    private int edicio;
    private ArrayList<Inscripcio> corredors; //MANIN DIRIA QUE SE GUARDA LA INCRPICION

    public Marxa(int edicio) {
        this.edicio = edicio;
        corredors = new ArrayList<>();
    }

    public void addCorrInsc(Inscripcio insc){
        corredors.add(insc);
    }

    public ArrayList<Inscripcio> getCorredors(){
        return new ArrayList<Inscripcio>(corredors);
    }
}


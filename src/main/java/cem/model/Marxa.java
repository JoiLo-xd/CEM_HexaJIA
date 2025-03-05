package cem.model;

import java.util.ArrayList;

public class Marxa {
    private int edicio;
    private ArrayList<Inscripcio> corredors; //MANIN DIRIA QUE SE GUARDA LA INCRPICION

    public Marxa(int edicio) {
        this.edicio = edicio;
        corredors = new ArrayList<>();
    }


    @Override
    public boolean equals(Object obj) {
        Marxa m = (Marxa) obj;
        return this.edicio==(m.getEdicio());
    }
    
    public void addCorrInsc(Inscripcio insc){
        corredors.add(insc);
    }

    //GETTERS
    public int getEdicio() {
        return edicio;
    }
    public ArrayList<Inscripcio> getCorredors(){
        return new ArrayList<Inscripcio>(corredors);
    }
}


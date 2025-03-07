package cem.model;

import java.util.ArrayList;

public class Marxa {
    private int edicio;
    private ArrayList<Inscripcio> inscripcionsMarxa; //MANIN DIRIA QUE SE GUARDA LA INCRPICION

    public Marxa(int edicio) {
        this.edicio = edicio;
        inscripcionsMarxa = new ArrayList<>();
    }


    @Override
    public boolean equals(Object obj) {
        Marxa m = (Marxa) obj;
        return this.edicio==(m.getEdicio());
    }
    
    public void addCorrInsc(Inscripcio insc){
        inscripcionsMarxa.add(insc);
    }

    //GETTERS
    public int getEdicio() {
        return edicio;
    }
    public ArrayList<Inscripcio> getInscripcionsMarxa(){
        return new ArrayList<Inscripcio>(inscripcionsMarxa);
    }

    @Override
    public String toString() {
        String u = "Cursa de la " + edicio + " edició.";
        return u;
    }
}


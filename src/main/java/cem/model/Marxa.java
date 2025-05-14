package cem.model;

import java.util.ArrayList;

public class Marxa {

    //atributos
    private int edicio;
    private ArrayList<Inscripcio> inscripcionsMarxa;

    //constructores
    public Marxa(int edicio) {
        this.edicio = edicio;
        inscripcionsMarxa = new ArrayList<>();
    }

    public Marxa(int edicio, ArrayList<Inscripcio> inscripcionsMarxa) {
        this.edicio = edicio;
        this.inscripcionsMarxa = inscripcionsMarxa;
    }


    //equals para saber si existe
    @Override
    public boolean equals(Object obj) {
        Marxa m = (Marxa) obj;
        return this.edicio==(m.getEdicio());
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


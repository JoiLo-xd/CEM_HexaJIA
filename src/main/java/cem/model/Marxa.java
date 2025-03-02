package cem.model;

import java.util.ArrayList;

public class Marxa {
    private int edicio;
    private ArrayList<Corredor> corredors;

    public Marxa(int edicio) {
        this.edicio = edicio;
        corredors = new ArrayList<>();
    }
}

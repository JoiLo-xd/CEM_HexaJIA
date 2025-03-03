package cem.model;

import java.util.ArrayList;

public class Corredor {
    private final String nif;
    private String nom;
    private String cognoms;
    private final String dataNaixement;
    private String sexe; //pongo String porque me comentasteis que habria tres opciones
    private String poblacio;
    private int numTelefon;
    private String email;
    private String entitat;
    private boolean federat;
    private ArrayList<Inscripcio> curses;

    public Corredor(String nif, String nom, String cognoms, String dataNaixement, String sexe, String poblacio, int numTelefon, String email, String entitat, boolean federat){
        this.nif = nif;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.sexe = sexe;
        this.poblacio = poblacio;
        this.numTelefon = numTelefon;
        this.email = email;
        this.entitat = entitat;
        this.federat = federat;
        curses = new ArrayList<>();
    }
}

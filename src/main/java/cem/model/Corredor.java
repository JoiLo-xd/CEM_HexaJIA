package cem.model;

import cem.enums.Sexe;

import java.util.ArrayList;

public class Corredor {
    private final String nif;
    private String nom;
    private String cognoms;
    private final String dataNaixement;
    private Sexe sexe;
    private String poblacio;
    private int numTelefon;
    private String email;
    private String entitat;
    private boolean federat;
    private ArrayList<Inscripcio> inscripsions = new ArrayList<>(); //Preguntar realmente esta para ver si es simplemente un HasMap para no tener objetos repetidos

    public Corredor(String nif, String nom, String cognoms, String dataNaixement, Sexe sexe, String poblacio, int numTelefon, String email, String entitat, boolean federat){
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
    }

    //Para añadir cosas a la Arraylist
    public ArrayList<Inscripcio> getInscr(){
        return new ArrayList<Inscripcio>(inscripsions); //Preguntar si seria otra direccion de memoria
    }


    public void addIncrip(Inscripcio inscr){
        inscripsions.add(inscr);
    }

    //GETTERS Y SETTERS VARIADOS, solo hay seters de cosas que se puedan cambiar (si he contado la gente que se cambia de genero)
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    } //validar sexe

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public void setNumTelefon(int numTelefon) {
        this.numTelefon = numTelefon;
    } // Validar numero de telefono

    public void setEmail(String email) {
        this.email = email;
    } // Validar Email con el @

    public void setEntitat(String entitat) {
        this.entitat = entitat;
    }

    public void setFederat(boolean federat) {
        this.federat = federat;
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public String getSexe() {
        return sexe;
    }

    public int getNumTelefon() {
        return numTelefon;
    }

    public String getEmail() {
        return email;
    }

    public String getEntitat() {
        return entitat;
    }

    public boolean isFederat() {
        return federat;
    }
}

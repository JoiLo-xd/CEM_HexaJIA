package cem.model;

import cem.enums.Sexe;

import java.time.LocalDate;
import java.util.ArrayList;

public class Corredor {
    private final String nif;
    private String nom;
    private String cognoms;
    private LocalDate dataNaixement;
    private Sexe sexe;
    private String poblacio;
    private String numTelefon;
    private String email;
    private String entitat;
    private boolean federat;
    private ArrayList<Inscripcio> inscripcions = new ArrayList<>(); //Preguntar realmente esta para ver si es simplemente un HasMap para no tener objetos repetidos

    public Corredor(String nif, String nom, String cognoms, LocalDate dataNaixement, Sexe sexe, String poblacio, String numTelefon, String email, String entitat, boolean federat){
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

    public Corredor(String nif){
        this.nif = nif;
    }

    //Para añadir cosas a la Arraylist // ESTO NO ES LO QUE HACE ESTE CODIGO...
    public ArrayList<Inscripcio> getInscr(){
        return new ArrayList<Inscripcio>(inscripcions);
    }


    public void addIncrip(Inscripcio inscr){
        inscripcions.add(inscr);
    }

    //GETTERS Y SETTERS VARIADOS, solo hay seters de cosas que se puedan cambiar (si he contado la gente que se cambia de genero)
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    } //validar sexe //REVISAR

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public void setNumTelefon(String numTelefon) {
        this.numTelefon = numTelefon;
    } // Validar numero de telefono

    public void setEmail(String email) {
        this.email = email;
    }

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

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public String getNumTelefon() {
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


    @Override
    public String toString() {
        String esfederat = federat ? "Si" : "No";
        String returned = "NIF: " + getNif() + "    " + getNom() + " " + getCognoms() +"\n"
                + "   Data naixement: " + getDataNaixement() + "   Sexe: " + getSexe() +"\n"
                + "    Poblacio: " + getPoblacio() + "    NumTelèfon: " + getNumTelefon() + "   Email: " + getEmail() +"\n"
                + "    Entitat: " + getEntitat() + "    Federat: " + esfederat;
         // ESTO SOLO INDICA SI ES FEDERADO FALTAN LOS OTROS DATOS
        return returned;
    }

    @Override
    public boolean equals(Object o) {
        Corredor obj = (Corredor) o;
        if (obj.getNif().equalsIgnoreCase(getNif())) return true;
        return false;
    }

}

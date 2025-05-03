package cem.model;

import cem.enums.Sexe;
import cem.exceptions.CorredoresException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Participant {
    private final String nif;
    private String nom;
    private String cognoms;
    private LocalDate dataNaixement;
    private String sexe;
    private String poblacio;
    private String numTelefon;
    private String email;
    private String entitat;
    private boolean federat;
    private String observacions;
    private ArrayList<Inscripcio> inscripcions = new ArrayList<>(); //Preguntar realmente esta para ver si es simplemente un HasMap para no tener objetos repetidos

    // constructor de corredor, se valida que no deje un campo vacio
    public Participant(String nif, String nom, String cognoms, LocalDate dataNaixement, String sexe, String poblacio, String numTelefon, String email, String entitat, boolean federat) throws CorredoresException{
        if ( nom.equals("") ||poblacio.equals("") ||cognoms.equals("") || entitat.equals("")){
            throw new CorredoresException("Faltan campos");
        }else {
            this.nif = nif;
            this.nom = nom;
            this.cognoms = cognoms;
            this.dataNaixement = dataNaixement;
            this.sexe = sexe;
            this.poblacio = poblacio;
            this.numTelefon = numTelefon;
            this.email = email;
            this.entitat = federat ? entitat : "no federat";
            this.federat = federat;
        }
    }

    public Participant(String nif){
        this.nif = nif;
    }

    public ArrayList<Inscripcio> getInscr(){
        return new ArrayList<Inscripcio>(inscripcions);
    }

    //añade una inscripcion al arrayList
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

    public void setSexe(String sexe) {
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

    public String getSexe() {
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

    public String getObservacions() {
        return observacions;
    }



    @Override
    public String toString() {
        String esfederat = federat ? "Si" : "No";
        String returned = "NIF: " + getNif() + "    " + getNom() + " " + getCognoms() +"\n"
                + "   Data naixement: " + getDataNaixement() + "   Sexe: " + getSexe()+"\n"
                + "    Poblacio: " + getPoblacio() + "    NumTelèfon: " + getNumTelefon() + "   Email: " + getEmail() +"\n"
                + "    Entitat: " + getEntitat() + "    Federat: " + esfederat;
         // ESTO SOLO INDICA SI ES FEDERADO FALTAN LOS OTROS DATOS
        return returned;
    }

    //equals para comparar si ya esxiste el corredor
    @Override
    public boolean equals(Object o) {
        Participant obj = (Participant) o;
        if (obj.getNif().equalsIgnoreCase(getNif())) return true;
        return false;
    }

}

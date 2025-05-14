/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cem.model.TO;

import java.time.LocalTime;

/**
 *
 * @author admin
 */
public class InscripcionsRanking {
    private String nom;
    private String temps;
    private String assistencia;
    private int Dorsal

    public InscripcionsRanking(String nom, String temps, String assistencia) {
        this.nom = nom;  
        this.temps = temps;
        this.assistencia = assistencia;
    }

    public String getNom() {
        return nom;
    }

    public String getTemps() {
        return temps;
    }

    public String getAssistencia() {
        return assistencia;
    }
    public void setDorsal(int dorsal){
        this.dorsal = dorsal;
    }
    
    public int getDorsal(){
        return dorsal;
    }
}

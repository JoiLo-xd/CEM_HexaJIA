/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cem.model.TO;

/**
 *
 * @author admin
 */

    //atributos
public class ParticipantEditionTO {
    private int edicio;
    private int numParticipants;

    //constructor
    public ParticipantEditionTO(int edicio, int numParticipants) {
        this.edicio = edicio;
        this.numParticipants = numParticipants;
    }

    
    //GETTERS Y SETTERS
    public int getEdicio() {
        return edicio;
    }

    public void setEdicio(int edicio) {
        this.edicio = edicio;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }
    
    

}

package cem.model;

import cem.enums.Asistencia;
import cem.view.AskDataCEM;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Inscripcio {

    //atributos
    private int dorsal; //Creo que esto tendria que ser codi de barres no voy NGL
    private boolean modalitat; // pongo boolean porque solo hay dos opciones, se puedde canmbiar a String -> Nota: Entoces deberiamos poner una constante para la simpleza del codigo
    private LocalTime horaSortida;
    private LocalTime horaArribada;
    private Duration tempsTotal;
    private Asistencia asistencia;
    private Participant corredor;
    private long codi;

    //constructor
    public Inscripcio(int dorsal, boolean modalitat, Participant corredor) {
        this.dorsal = dorsal;
        this.modalitat = modalitat;
        this.corredor = corredor;
    }

    //constructor
    public Inscripcio(int dorsal){
        this.dorsal = dorsal;
    }

    //constructor
    public Inscripcio(int dorsal, boolean modalitat, LocalTime horaSortida, LocalTime horaArribada,Participant corredor){
        this.dorsal = dorsal;
        this.modalitat = modalitat;
        this.horaSortida = horaSortida;
        this.horaArribada = horaArribada;
        this.corredor = corredor;
    }

    //SETTERS Y GETTERS
    public void setModalitat(boolean modalitat) {
        this.modalitat = modalitat;
    }

    public void setHoraSortida(LocalTime horaSortida) {
        this.horaSortida = horaSortida;
    }

    public void setHoraArribada(LocalTime horaArribada) { // Aqui lo tendremos que hacer con excepciones y tal no con cosas cutres
        if (horaSortida != null) {
            this.horaArribada = horaArribada;
        } // Aqui en un futuro lanzara una excepcion jeje
    }

    public void setTempsTotal() {
        if (horaSortida != null && horaArribada != null)
        this.tempsTotal = Duration.between(horaSortida, horaArribada);
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public int getDorsal() {
        return dorsal;
    }

    public boolean isModalitat() {
        return modalitat;
    }

    public LocalTime getHoraSortida() {
        return horaSortida;
    }

    public LocalTime getHoraArribada() {
        return horaArribada;
    }

    public Duration getTempsTotal() {
        return tempsTotal;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public Participant getCorredor() {
        return corredor;
    }
    
    public void addCodiBarres(long codi) {
        this.codi = codi;
    }

    //equals para saber si ya existe
    @Override
    public boolean equals(Object o) {
        Inscripcio ins = (Inscripcio) o;
        return ins.getDorsal() == getDorsal();
    }

    @Override
    public String toString() {
        String esmodalitat = modalitat ? "Llarga" : "Curta";
        return dorsal + " - " + corredor.getNom() + " " + corredor.getCognoms() + " - " + esmodalitat + " - Sortida: " + String.valueOf(getHoraSortida()) +
                " - Arribada: " + String.valueOf(getHoraArribada()) + " - Duració: " + String.valueOf(getTempsTotal());
    }

}

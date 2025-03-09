package cem.model;

import cem.enums.Asistencia;
import cem.view.AskDataCEM;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Inscripcio {

    private int dorsal; //Creo que esto tendria que ser codi de barres no voy NGL
    private boolean modalitat; // pongo boolean porque solo hay dos opciones, se puedde canmbiar a String -> Nota: Entoces deberiamos poner una constante para la simpleza del codigo
    private LocalDateTime horaSortida;
    private LocalDateTime horaArribada;
    private Duration tempsTotal;
    private Asistencia asistencia;
    private Corredor corredor;

    public Inscripcio(int dorsal, boolean modalitat, Corredor corredor) {
        this.dorsal = dorsal;
        this.modalitat = modalitat;
        this.corredor = corredor;
    }

    public Inscripcio(int dorsal){
        this.dorsal = dorsal;
    }

    public void setModalitat(boolean modalitat) {
        this.modalitat = modalitat;
    }

    public void setHoraSortida() {
        this.horaSortida = AskDataCEM.askTime();
    }

    public void setHoraArribada() { // Aqui lo tendremos que hacer con excepciones y tal no con cosas cutres
        if (horaSortida != null) {
            this.horaArribada = AskDataCEM.askTime();
        }
    }

    public void setTempsTotal() {
        if (horaSortida != null && horaArribada != null)
        this.tempsTotal = Duration.between(horaSortida, horaArribada);
    }


    // Este deberia ser con enums pero no sabemos como hacerlo jeje
    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public int getDorsal() {
        return dorsal;
    }

    public boolean isModalitat() {
        return modalitat;
    }

    public LocalDateTime getHoraSortida() {
        return horaSortida;
    }

    public LocalDateTime getHoraArribada() {
        return horaArribada;
    }

    public Duration getTempsTotal() {
        return tempsTotal;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    @Override
    public boolean equals(Object o) {
        Inscripcio ins = (Inscripcio) o;

        return ins.getDorsal() == getDorsal();
    }

    @Override
    public String toString() {
        String esmodalitat = modalitat ? "Llarga" : "Curta"; // ESTO SOLO INDICA SI ES FEDERADO FALTAN LOS OTROS DATOS
        return dorsal + " - " + corredor.getNom() + corredor.getCognoms() + " - " + esmodalitat;
    }


}

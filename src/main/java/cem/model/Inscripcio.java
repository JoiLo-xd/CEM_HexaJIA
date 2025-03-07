package cem.model;

import cem.enums.Asistencia;

public class Inscripcio {

    //Las cosas que son Horas en un futuro se tendran que cambiar a java Time.
    private final int dorsal; //Creo que esto tendria que ser codi de barres no voy NGL
    private final boolean modalitat; // pongo boolean porque solo hay dos opciones, se puedde canmbiar a String -> Nota: Entoces deberiamos poner una constante para la simpleza del codigo
    private Integer horaSortida; //Integer para que pueda ser NULL
    private int horaArribada;
    private int tempsTotal;
    private Asistencia asistencia;
    private final Corredor corredor;

    public Inscripcio(int dorsal, boolean modalitat, Corredor corredor) {
        this.dorsal = dorsal;
        this.modalitat = modalitat;
        this.corredor = corredor;
    }

    public void setHoraSortida(int horaSortida) {
        this.horaSortida = horaSortida;
    }

    public void setHoraArribada(int horaArribada) { // Aqui lo tendremos que hacer con excepciones y tal no con cosas cutres
        if (horaSortida != null) {
            this.horaArribada = horaArribada;
        }
    }

    public void setTempsTotal(int tempsTotal) {
        this.tempsTotal = tempsTotal;
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

    public int getHoraSortida() {
        return horaSortida;
    }

    public int getHoraArribada() {
        return horaArribada;
    }

    public int getTempsTotal() {
        return tempsTotal;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public Corredor getCorredor() {
        return corredor;
    }
}

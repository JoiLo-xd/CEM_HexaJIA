package cem.model;

public class Inscripcio {
  private int dorsal;
  private boolean modalitat; // pongo boolean porque solo hay dos opciones, se puedde canmbiar a String
  private int horaSortida;
  private int horaArribada;
  private int tempsTotal;
  private String asistencia; // me acuerdo que era o String porque hay tres opciones (asistir, no asistir, abandona) o se puede hacer con dos boolean
  private Corredor corredor;

  public Inscripcio(int dorsal, boolean modalitat, int horaSortida, int horaArribada, int tempsTotal, String asistencia, Corredor corredor){
      this.dorsal = dorsal;
      this.modalitat = modalitat;
      this.horaSortida = horaSortida;
      this.horaArribada = horaArribada;
      this.tempsTotal = tempsTotal;
      this.asistencia = asistencia;
      this.corredor = corredor;
  }
}

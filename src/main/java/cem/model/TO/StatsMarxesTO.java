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
public class StatsMarxesTO {
    private int edicio;
    private int inscrits;
    private int enCursa;
    private int arribats;
    private int absents;
    private int abandonat;
    private LocalTime tempsMesRapid;
    private LocalTime tempsMesLent;

    public StatsMarxesTO(int edicio, int inscrits, int enCursa, int arribats, int absents, int abandonat, LocalTime tempsMesRapid, LocalTime tempsMesLent) {
        this.edicio = edicio;
        this.inscrits = inscrits;
        this.enCursa = enCursa;
        this.arribats = arribats;
        this.absents = absents;
        this.abandonat = abandonat;
        this.tempsMesRapid =  tempsMesRapid != null ? tempsMesRapid : null;
        this.tempsMesLent = tempsMesLent != null ? tempsMesLent : null;
    }

    public String getEdicio() {
        return edicio + "";
    }

    public String getInscrits() {
        return inscrits + "";
    }

    public String getEnCursa() {
        return enCursa + "";
    }

    public String getArribats() {
        return arribats + "";
    }

    public String getAbsents() {
        return absents + "";
    }

    public String getAbandonat() {
        return abandonat + "";
    }

    public LocalTime getTempsMesRapid() {
        return tempsMesRapid;
    }

    public LocalTime getTempsMesLent() {
        return tempsMesLent;
    }
    
    
    
}

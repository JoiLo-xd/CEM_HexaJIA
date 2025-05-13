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
    private String tempsMesRapid;
    private String tempsMesLent;

    public StatsMarxesTO(int edicio, int inscrits, int enCursa, int arribats, int absents, int abandonat, String tempsMesRapid, String tempsMesLent) {
        this.edicio = edicio;
        this.inscrits = inscrits;
        this.enCursa = enCursa;
        this.arribats = arribats;
        this.absents = absents;
        this.abandonat = abandonat;
        this.tempsMesRapid =  tempsMesRapid;
        this.tempsMesLent = tempsMesLent;
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

    public String getTempsMesRapid() {
        return tempsMesRapid;
    }

    public String getTempsMesLent() {
        return tempsMesLent;
    }
}

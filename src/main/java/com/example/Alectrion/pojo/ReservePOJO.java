package com.example.Alectrion.pojo;

public class ReservePOJO {

    private int userID;
    private int estID;
    private String horario;

    public ReservePOJO(int userID, int estID, String horario) {
        this.userID = userID;
        this.estID = estID;
        this.horario = horario;
    }

    public ReservePOJO(){}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEstID() {
        return estID;
    }

    public void setEstID(int estID) {
        this.estID = estID;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}

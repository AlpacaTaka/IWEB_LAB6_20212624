package com.example.iweb_lab6_20212624.beans;

public class Actor {

    private int actorId;
    private String actorNombre;
    private String actorApellido;
    private int anoNacimiento;
    private boolean premioOscar;


    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getActorNombre() {
        return actorNombre;
    }

    public void setActorNombre(String actorNombre) {
        this.actorNombre = actorNombre;
    }

    public String getActorApellido() {
        return actorApellido;
    }

    public void setActorApellido(String actorApellido) {
        this.actorApellido = actorApellido;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public boolean isPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(boolean premioOscar) {
        this.premioOscar = premioOscar;
    }
}

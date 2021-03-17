package org.gabysanchez.application;

import java.io.Serializable;

public class Data implements Serializable {


 private Dificultad dificultad;
 private String namePlayer;


    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }
}

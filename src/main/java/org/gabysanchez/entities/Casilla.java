package org.gabysanchez.entities;


import org.gabysanchez.entities.barcos.Barco;

import java.io.Serializable;

public class Casilla implements Serializable {
    private int x;
    private int y;
    private Barco barco;
    private EstadoCasilla estado;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.barco = null;
        this.estado = EstadoCasilla.AGUA;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EstadoCasilla getEstado() {
        return estado;
    }

    public void setEstado(EstadoCasilla estado) {
        this.estado = estado;
    }
}

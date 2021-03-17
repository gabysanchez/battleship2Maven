package org.gabysanchez.entities.barcos;

import java.io.Serializable;

public class ParteBarco implements Serializable {
    private int x;
    private int y;
    private EstadoParte estado;

    public ParteBarco(int x, int y) {
        this.x = x;
        this.y = y;
        this.estado = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EstadoParte getEstado() {
        return estado;
    }

    public void setEstado(EstadoParte estado) {
        this.estado = estado;
    }
}

package org.gabysanchez.entities;

import java.io.Serializable;

public class Tablero implements Serializable {
    private Casilla[][] casillas;
    private  Integer tamTablero;


    public Tablero() {
        tamTablero=10;
        this.casillas = new Casilla[tamTablero][tamTablero];
        for (int x=0;x<10;x++){
            for (int y=0;y<10;y++){
                casillas [x][y]=new Casilla(x,y);
            }
        }
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

}

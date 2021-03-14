package org.gabysanchez.application;

import org.gabysanchez.entities.IA;
import org.gabysanchez.entities.Jugador;

import java.util.Date;

public class Partida {
    private Jugador j1;
    private Jugador j2;
    private int tamTablero;
    private Date fecha;

    public Partida() {
        this.j1 = new Jugador(1);
        this.j2 = new Jugador(2);
        this.tamTablero = 10;
    }

    public int getTamTablero() {
        return tamTablero;
    }

    public Jugador getJ1() {
        return j1;
    }

    public Jugador getJ2() {
        return j2;
    }
    public void createIA(){
        j2=new IA(2);
    }
}

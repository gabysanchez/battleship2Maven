package org.gabysanchez.application;

import org.gabysanchez.entities.IA;
import org.gabysanchez.entities.Jugador;

import java.io.Serializable;
import java.util.Date;

public class Partida implements Serializable {
    private Jugador j1;
    private Jugador j2;
    private int tamTablero;
    private Date fecha;
    private Dificultad dificultad;
    private EstadoPartida estadoPartida;
    private String nombre;

    public Partida() {
        this.j1 = new Jugador(1);
        this.j2 = new Jugador(2);
        this.tamTablero = 10;
        this.estadoPartida=EstadoPartida.Null;
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

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPartida getEstadoPartida() {
        return estadoPartida;
    }

    public void setEstadoPartida(EstadoPartida estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

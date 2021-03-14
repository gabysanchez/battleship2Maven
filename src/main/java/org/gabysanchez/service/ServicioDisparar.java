package org.gabysanchez.service;

import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Partida;
import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.EstadoCasilla;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.entities.barcos.EstadoParte;
import org.gabysanchez.entities.barcos.ParteBarco;
import org.gabysanchez.ui.tablero.Boton;

import java.util.Arrays;
import java.util.stream.Stream;

public class ServicioDisparar {

    private static ServicioDisparar servicioDisparar;

    public static ServicioDisparar getInstance() {
        if(servicioDisparar == null){
            servicioDisparar = new ServicioDisparar();
        }
        return servicioDisparar;
    }

    private void disparo(Jugador jugador, int x, int y){
        Casilla casilla = jugador.getTableroAtaque().getCasillas()[x][y];
        if (casilla.getEstado().equals(EstadoCasilla.AGUA)){
            casilla.setEstado(EstadoCasilla.DISPARO);
            if (casilla.getBarco() != null){
                ParteBarco[] partes = casilla.getBarco().getPartes();
                for (int i = 0; i < partes.length; i++) {
                    ParteBarco parte = partes[i];
                    if (parte.getX()==x&&parte.getY()==y){
                        parte.setEstado(EstadoParte.TOCADO);
                    }
                }
                Stream<ParteBarco> partesTocadas = Arrays.stream(partes).filter(parteBarco -> parteBarco.getEstado()==EstadoParte.TOCADO);
                if (partes.length==partesTocadas.count()){
                    for (int i = 0; i < partes.length; i++) {
                        ParteBarco parte = partes[i];
                        parte.setEstado(EstadoParte.HUNDIDO);
                    }
                }
            }
            Controller.getInstance().getPartida().getJ2().setTableroDefensa(Controller.getInstance().getPartida().getJ1().getTableroAtaque());
            Controller.getInstance().getPartida().getJ1().setTableroDefensa(Controller.getInstance().getPartida().getJ2().getTableroAtaque());
        }
    }
    public void checkDisparo(Boton bt, Partida partida){
        disparo(partida.getJ1(), bt.getX(), bt.getY());
        Casilla casilla = partida.getJ2().memoryAtac();
        disparo(partida.getJ2(), casilla.getX(),casilla.getY());
        Controller.getInstance().getPartida().getJ1().setTableroAtaque(Controller.getInstance().getPartida().getJ2().getTableroDefensa());
    }
}

package org.gabysanchez.service;


import org.gabysanchez.application.Controller;
import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.entities.Tablero;
import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.entities.barcos.Posicion;

import java.util.Random;

public class ServicioColocarBarco {

    private static ServicioColocarBarco servicioColocarBarco;


    public static ServicioColocarBarco getInstance() {
        if (servicioColocarBarco == null) {
            servicioColocarBarco = new ServicioColocarBarco();
        }
        return servicioColocarBarco;
    }

    public void colocarBarco(Barco barco, Jugador jugador) {
        Posicion _posicion = barco.getPosicion();
        Tablero tablero = jugador.getTableroDefensa();
        AlgoritmoPosicionBarco algoritmoPosicionBarco = new AlgoritmoPosicionBarco();
        Random random = new Random();
        int longitud = barco.getLongitud();
        int x = barco.getX();
        int y = barco.getY();
        boolean barcoOK = algoritmoPosicionBarco.checkPosition(barco,tablero,_posicion);
        if (barcoOK){
            if (_posicion == Posicion.VERTICAL){
                for (int i = 0; i < longitud; i++) {
                    Casilla casilla = tablero.getCasillas()[x][y+i];
                    casilla.setBarco(barco);
                }
            }else if (_posicion == Posicion.HORIZONTAL){
                for (int i = 0; i < longitud; i++) {
                    Casilla casilla = tablero.getCasillas()[x+i][y];
                    casilla.setBarco(barco);
                }
            }

            if (jugador.getnBarcos()>0){
                jugador.setnBarcos(jugador.getnBarcos()-1);
                jugador.removeBarco(barco);
            }

            barco.definirPartes();



            //System.out.println(jugador.getnBarcos());
        }else {
            barco.setX(random.nextInt(10));
            barco.setY(random.nextInt(10));
            barco.setPosicion(Posicion.getRandomPosition());
            colocarBarco(barco,jugador);
        }

    }


    public boolean barcoPosOK(int x, int y, Tablero tablero) {
        for (int i = x - 1; i <= x + 1; i++) {
            if (i >= 0 && i <tablero.getCasillas().length) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (j >= 0 && j <tablero.getCasillas().length) {
                        Casilla casilla = tablero.getCasillas()[i][j];
                        if (casilla.getBarco() != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}



package org.gabysanchez.service;



import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.Tablero;
import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.entities.barcos.Posicion;

public class AlgoritmoPosicionBarco {

    public Boolean checkPosition(Barco barco, Tablero tablero, Posicion _posicion) {
        //Random random = new Random();
        int longitud = barco.getLongitud();
        int x = barco.getX();
        int y = barco.getY();
        boolean barcoOK = true;
        if (_posicion == Posicion.VERTICAL) {
            for (int i = 0; i < longitud; i++) {
                if(tablero.getCasillas().length < y + longitud || !checkPositionAround(x, y + i, tablero)){
                    barcoOK=false;
                    break;
                }
            }
        }
        if (_posicion == Posicion.HORIZONTAL) {
            for (int i = 0; i < longitud; i++) {
                if(tablero.getCasillas().length < x + longitud || !checkPositionAround(x+i, y, tablero)){
                    barcoOK=false;
                    break;
                }
            }
        }
        if (barcoOK){
            return true;
        }else {
            return false;
        }

    }
    public boolean checkPositionAround(int x, int y, Tablero tablero) {
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

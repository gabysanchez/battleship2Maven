package org.gabysanchez;

import org.gabysanchez.application.Controller;
import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.entities.barcos.Posicion;
import org.gabysanchez.ui.tablero.EstadoUiTablero;

import java.io.IOException;
import java.util.List;

public class ControllerSpecific {

    public void girar(){
        Posicion posicion = Controller.getInstance().getPartida().getJ1().getNextSelectedBarco().getPosicion();
        if (posicion.equals(Posicion.VERTICAL)){
            Controller.getInstance().getPartida().getJ1().getNextSelectedBarco().setPosicion(Posicion.HORIZONTAL);
        }else {
            Controller.getInstance().getPartida().getJ1().getNextSelectedBarco().setPosicion(Posicion.VERTICAL);
        }
    }


    public void borrar(){
        Controller.getInstance().getPartida().getJ1().getMapBarcos().clear();
        for (int x=0;x<10;x++){
            for (int y=0;y<10;y++){
                Casilla casilla = Controller.getInstance().getPartida().getJ1().getTableroDefensa().getCasillas()[x][y];
                casilla.setBarco(null);
            }
        }
        try {
            Controller.getInstance().getScene().getUiTableroDefensa().setEstado(EstadoUiTablero.USE);
            Controller.getInstance().colocar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

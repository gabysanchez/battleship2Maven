package org.gabysanchez.service;


import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.entities.barcos.Posicion;
import org.gabysanchez.ui.tablero.Boton;

public class ServicioDibujar {
    private static ServicioDibujar servicioDibujar;

    public static ServicioDibujar getInstance() {
        if(servicioDibujar == null){
            servicioDibujar = new ServicioDibujar();
        }
        return servicioDibujar;
    }

    public void removeSombra(Boton bt, Jugador jugador, Boton[][] botones){

        Barco barco1 = jugador.getNextSelectedBarco();
        for (int i = 0; i < barco1.getLongitud(); i++) {
            if (barco1.getPosicion() == Posicion.VERTICAL) {
                if (botones.length >= (bt.getY() + barco1.getLongitud())) {
                    Casilla casilla = jugador.getTableroDefensa().getCasillas()[bt.getX()][bt.getY()+i];
                    if (casilla.getBarco()!= null){
                        botones[bt.getX()][bt.getY()+i].setStyle("-fx-background-color: #B30A80");
                    }else {
                        botones[bt.getX()][bt.getY()+i].setStyle("-fx-background-color: #98FFEF");
                    }
                }
            }else if (barco1.getPosicion() == Posicion.HORIZONTAL) {
                if (botones.length >= (bt.getX() + barco1.getLongitud())) {
                    Casilla casilla = jugador.getTableroDefensa().getCasillas()[bt.getX()+i][bt.getY()];
                    if (casilla.getBarco()!= null){
                        botones[bt.getX()+i][bt.getY()].setStyle("-fx-background-color: #B30A80");
                    }else {
                        botones[bt.getX()+i][bt.getY()].setStyle("-fx-background-color: #98FFEF");
                    }
                }
            }
        }
    }
    public void addSombra(Boton bt, Jugador jugador, Boton[][] botones){
        Barco barco1 = jugador.getNextSelectedBarco();
        for (int i = 0; i < barco1.getLongitud(); i++) {
            if (barco1.getPosicion() == Posicion.VERTICAL) {
                if (botones.length >= (bt.getY() + barco1.getLongitud())) {
                    botones[bt.getX()][bt.getY() + i].setStyle("-fx-background-color: #B787A8");
                }
            }else if (barco1.getPosicion() == Posicion.HORIZONTAL) {
                if (botones.length >= (bt.getX() + barco1.getLongitud())) {
                    botones[bt.getX()+i][bt.getY()].setStyle("-fx-background-color: #B787A8");
                }
            }
        }
    }
    public void addBarco(Boton bt,Jugador jugador,Boton[][] botones){
        AlgoritmoPosicionBarco algoritmoPosicionBarco = new AlgoritmoPosicionBarco();
        Barco barco1 = jugador.getNextSelectedBarco();
        for (int i = 0; i < barco1.getLongitud(); i++) {
            if (barco1.getPosicion() == Posicion.VERTICAL) {
                if (botones.length >= (bt.getY() + barco1.getLongitud())) {
                    barco1.setX(bt.getX());
                    barco1.setY(bt.getY());
                    if (algoritmoPosicionBarco.checkPosition(barco1, jugador.getTableroDefensa(), barco1.getPosicion())){
                        jugador.setBarco(barco1);
                    }
                }
            }
            if (barco1.getPosicion() == Posicion.HORIZONTAL) {
                if (botones.length >= (bt.getX() + barco1.getLongitud())) {
                    barco1.setX(bt.getX());
                    barco1.setY(bt.getY());
                    if (algoritmoPosicionBarco.checkPosition(barco1, jugador.getTableroDefensa(), barco1.getPosicion())){
                        jugador.setBarco(barco1);
                    }
                }
            }
        }
    }
}

package org.gabysanchez.ui.tablero;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.gabysanchez.application.Controller;
import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.EstadoCasilla;
import org.gabysanchez.entities.barcos.EstadoParte;
import org.gabysanchez.entities.barcos.ParteBarco;
import org.gabysanchez.service.ServicioDibujar;
import org.gabysanchez.ui.scenes.SceneTablero;


public class UiTableroDefensa extends UiTablero{
    public UiTableroDefensa() {}

    @Override
    protected EventHandler<MouseEvent> eventMouseEntered(Boton bt) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (getEstado().equals(EstadoUiTablero.USE)){
                    ServicioDibujar.getInstance().addSombra(bt, Controller.getInstance().getPartida().getJ1(),botones);
                }
            }
        };
    }

    @Override
    protected EventHandler<MouseEvent> eventMouseExited(Boton bt) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (getEstado().equals(EstadoUiTablero.USE)){
                    ServicioDibujar.getInstance().removeSombra(bt,Controller.getInstance().getPartida().getJ1(),botones);
                }
            }
        };
    }

    @Override
    protected EventHandler<ActionEvent> eventOnAction(Boton bt, SceneTablero sceneTablero) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (getEstado().equals(EstadoUiTablero.USE)){
                    ServicioDibujar.getInstance().addBarco(bt,Controller.getInstance().getPartida().getJ1(),botones);
                    redraw();
                    sceneTablero.update();
                }
            }
        };
    }

    public void redraw() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Casilla casilla = Controller.getInstance().getPartida().getJ1().getTableroDefensa().getCasillas()[y][x];
                if (casilla.getEstado().equals(EstadoCasilla.DISPARO)) {
                    if (casilla.getBarco() != null) {
                        ParteBarco[] partes = casilla.getBarco().getPartes();
                        ParteBarco parte = null;
                        for (int i = 0; i < partes.length; i++) {
                            if (partes[i].getX() == casilla.getX() && partes[i].getY() == casilla.getY()) {
                                parte = partes[i];
                            }
                        }
                        if (parte != null && parte.getEstado().equals(EstadoParte.TOCADO)) {
                            getBotones()[y][x].setStyle("-fx-background-color: #787277");
                        } else {
                            getBotones()[y][x].setStyle("-fx-background-color: #1D090F");
                        }
                    } else {
                        getBotones()[y][x].setStyle("-fx-background-color: #6EBCB0");
                    }
                } else {
                    getBotones()[y][x].setStyle("-fx-background-color: #98FFEF");
                    if (Controller.getInstance().getPartida().getJ1().getTableroDefensa().getCasillas()[y][x].getBarco() != null) {
                        getBotones()[y][x].setStyle("-fx-background-color: #B30A80");
                    }
                }
            }
        }
    }
}

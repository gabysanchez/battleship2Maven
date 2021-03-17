package org.gabysanchez.ui.tablero;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.gabysanchez.application.Controller;
import org.gabysanchez.application.EstadoPartida;
import org.gabysanchez.entities.Casilla;
import org.gabysanchez.entities.EstadoCasilla;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.entities.barcos.EstadoParte;
import org.gabysanchez.entities.barcos.ParteBarco;
import org.gabysanchez.service.ServicioDibujar;
import org.gabysanchez.service.ServicioDisparar;
import org.gabysanchez.ui.scenes.SceneTablero;

public class UiTableroAtaque extends UiTablero{

    public UiTableroAtaque() {

    }

    @Override
    protected EventHandler<MouseEvent> eventMouseEntered(Boton bt) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //ServicioDibujar.getInstance().addSombra(bt,jugador,botones);
                if (getEstado().equals(EstadoUiTablero.USE)){
                    botones[bt.getX()][bt.getY()].setStyle("-fx-background-color: #E8DE69");
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
                    Controller.getInstance().getScene().getUiTableroDefensa().redraw();
                    redraw();
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
                    redraw();
                    ServicioDisparar.getInstance().checkDisparo(bt,Controller.getInstance().getPartida());
                    if (Controller.getInstance().getPartida().getEstadoPartida().equals(EstadoPartida.COMBATE)){
                        sceneTablero.update();
                    }
                }
            }
        };
    }

    public void redraw(){
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                //Tablero Enemigo
                Jugador jugador = Controller.getInstance().getPartida().getJ1();
                if (jugador.getId()==1){
                    Casilla casilla = jugador.getTableroAtaque().getCasillas()[y][x];
                    if(casilla.getEstado().equals(EstadoCasilla.DISPARO)){
                        if (casilla.getBarco() != null){
                            ParteBarco[] partes =casilla.getBarco().getPartes();
                            ParteBarco parte = null;
                            for (int i = 0; i < partes.length; i++) {
                                if (partes[i].getX()==casilla.getX()&&partes[i].getY()==casilla.getY()){
                                    parte=partes[i];
                                }
                            }
                            if (parte!=null&&parte.getEstado().equals(EstadoParte.TOCADO)){
                                getBotones()[y][x].setStyle("-fx-background-color: #9C2148");
                            }else {
                                getBotones()[y][x].setStyle("-fx-background-color: #1D090F");
                            }
                        }else {
                            getBotones()[y][x].setStyle("-fx-background-color: #6EBCB0");
                        }
                    }else {
                        getBotones()[y][x].setStyle("-fx-background-color: #98FFEF");
                    }


                }//Tablero Jugador
                /*
                else if (jugador.getId()==0){
                    if(jugador.getTablero().getCasillas()[y][x].getBarco() != null){
                        getBotones()[y][x].setStyle("-fx-background-color: #B30A80");
                    }
                }

                 */
            }
        }
    }
    /*
    public void redrawEnemy(){
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                Casilla casilla = jugador.getTablero().getCasillas()[y][x];
                if(casilla.getBarco() != null&&casilla.getEstado()==EsatoCasilla.AGUA){
                    getBotones()[y][x].setStyle("-fx-background-color: #52053B");
                }else {
                    getBotones()[y][x].setStyle("-fx-background-color: #98FFEF");
                }
            }
        }
    }

     */
}

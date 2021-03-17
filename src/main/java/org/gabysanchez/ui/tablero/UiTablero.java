package org.gabysanchez.ui.tablero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import org.gabysanchez.application.Controller;
import org.gabysanchez.ui.scenes.SceneTablero;

public abstract class UiTablero {

    protected Boton[][] botones;
    private  int tamTablero;
    private EstadoUiTablero estado;

    protected abstract EventHandler<MouseEvent> eventMouseEntered(Boton bt);
    protected abstract EventHandler<MouseEvent> eventMouseExited(Boton bt);
    protected abstract EventHandler<ActionEvent> eventOnAction(Boton bt, SceneTablero sceneTablero);

    public UiTablero() {
        this.estado=EstadoUiTablero.VIEW;
        this.tamTablero=Controller.getInstance().getPartida().getTamTablero();
        this.botones = new Boton[tamTablero][tamTablero];
        for (int x=0;x<10;x++){
            for (int y=0;y<10;y++){
                this.botones [x][y]=new Boton(x,y);
            }
        }
    }
    public void dibujar(Group gBotones,SceneTablero sceneTablero){
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                Boton bt = botones[x][y];
                bt.setPrefWidth(32);
                bt.setPrefHeight(32);
                bt.setLayoutX(x*32);
                bt.setLayoutY(y*32);
                bt.setStyle("-fx-background-color: #98FFEF");
                bt.setOnMouseEntered(eventMouseEntered(bt));
                bt.setOnMouseExited(eventMouseExited(bt));
                bt.setOnAction(eventOnAction(bt,sceneTablero));
                if(!gBotones.getChildren().contains(botones[x][y])) {
                    gBotones.getChildren().add(botones[x][y]);
                }
            }
        }
    }

    public EstadoUiTablero getEstado() {
        return estado;
    }

    public void setEstado(EstadoUiTablero estado) {
        this.estado = estado;
    }

    public Boton[][] getBotones() {
        return botones;
    }
    public void redraw(){}
}

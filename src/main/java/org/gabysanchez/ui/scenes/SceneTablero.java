package org.gabysanchez.ui.scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.gabysanchez.App;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.ui.tablero.UiTablero;
import org.gabysanchez.ui.tablero.UiTableroAtaque;
import org.gabysanchez.ui.tablero.UiTableroDefensa;


public abstract class SceneTablero {
    Scene escena = App.getScene();
    HBox boxIz = (HBox) escena.getRoot().getChildrenUnmodifiable().get(1);
    VBox panelBotones = (VBox) boxIz.getChildrenUnmodifiable().get(1);

    Jugador jugador;

    UiTablero uiTableroAtaque;
    UiTablero uiTableroDefensa;

    public SceneTablero(Jugador jugador) {
        this.jugador = jugador;
        //this.uiTableroAtaque = null;
        this.uiTableroDefensa = new UiTableroDefensa();
        this.uiTableroAtaque = new UiTableroAtaque();
    }

    public UiTablero getUiTableroAtaque() {
        return uiTableroAtaque;
    }

    public UiTablero getUiTableroDefensa() {
        return uiTableroDefensa;
    }

    public void crearUiTablero(UiTablero uiTablero){
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        Group gBotones = null;
        if (uiTablero==uiTableroDefensa){
            gBotones = (Group) box.getChildren().get(2);
        }else if (uiTablero==uiTableroAtaque){
            gBotones = (Group) box.getChildren().get(0);
        }
        uiTablero.dibujar(gBotones,this);

    }
    public void create(){
        System.out.println("no va");
    }
    public void update(){}
}

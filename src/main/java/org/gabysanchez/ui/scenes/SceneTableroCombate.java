package org.gabysanchez.ui.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.gabysanchez.App;
import org.gabysanchez.application.Controller;
import org.gabysanchez.entities.Jugador;

public class SceneTableroCombate extends SceneTablero{

    public SceneTableroCombate(Jugador jugador) {
        super(jugador);
    }
    public void create(){
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        HBox HboxDe = (HBox) escena.getRoot().getChildrenUnmodifiable().get(2);
        VBox Vbox = (VBox) HboxDe.getChildren().get(0);
        Label nameJ2 = (Label) Vbox.getChildren().get(0);
        Label valorJ2 = (Label) Vbox.getChildren().get(1);
        Label nameJ1 = (Label) Vbox.getChildren().get(3);
        Label valorJ1 = (Label) Vbox.getChildren().get(4);
        nameJ2.setText(Controller.getInstance().getPartida().getJ2().getNombre());
        valorJ2.setText(String.valueOf(Controller.getInstance().getPartida().getJ1().getnBarcos()));
        nameJ1.setText(Controller.getInstance().getPartida().getJ1().getNombre());
        valorJ1.setText(String.valueOf(Controller.getInstance().getPartida().getJ2().getnBarcos()));
    }
    public void update(){
        create();
    }
}

package org.gabysanchez.ui.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.service.ServicioSelectBarco;
import org.gabysanchez.ui.tablero.EstadoUiTablero;

import java.util.List;

public class SceneTableroColocar extends SceneTablero{

    public SceneTableroColocar(Jugador jugador) {
        super(jugador);
        uiTableroAtaque.setEstado(EstadoUiTablero.VIEW);
    }
    public void create(){
        //System.out.println(escena.getRoot().getChildrenUnmodifiable());
        for (Class<? extends Barco> key : jugador.getMapBarcos().keySet()){
            List<Barco> tipoBarco = jugador.getMapBarcos().get(key);
            String nameClave = String.valueOf(key);
            String[] name = nameClave.split("Barco");
            HBox cajaBoton = new HBox();
            Button bt = new Button();
            bt.setText(name[1]);
            bt.setPrefWidth(128);
            bt.setPrefHeight(32);
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ServicioSelectBarco.getInstance().selectedBarco(bt,jugador,key);
                }
            });
            Label tx = new Label();
            tx.setText(String.valueOf(tipoBarco.size()));
            tx.setStyle("-fx-font: 24 arial;");

            cajaBoton.getChildren().add(bt);
            cajaBoton.getChildren().add(tx);

            panelBotones.getChildren().add(cajaBoton);
        }
    }


    public void update(){
        panelBotones.getChildren().clear();
        create();
    }
}

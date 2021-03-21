package org.gabysanchez.ui.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import org.gabysanchez.App;
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
        VBox panelBotones = (VBox) boxIz.getChildrenUnmodifiable().get(1);
        //System.out.println(escena.getRoot().getChildrenUnmodifiable());
        for (Class<? extends Barco> key : jugador.getMapBarcos().keySet()){
            List<Barco> tipoBarco = jugador.getMapBarcos().get(key);
            String nameClave = String.valueOf(key);
            String[] name = nameClave.split("Barco");
            HBox cajaBoton = new HBox();
            Button bt = new Button();
            bt.setText(name[1]);
            bt.setPrefWidth(128);
            bt.getStyleClass().add("buttonMenuSmall");
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ServicioSelectBarco.getInstance().selectedBarco(bt,jugador,key);
                }
            });
            Label tx = new Label();
            tx.setText(String.valueOf(tipoBarco.size()));
            tx.getStyleClass().add("textMenu");
            tx.setStyle("-fx-font: 30px Tahoma;");
            cajaBoton.setSpacing(20);
            bt.setStyle("-fx-font: 15px Tahoma;");

            cajaBoton.getChildren().add(bt);
            cajaBoton.getChildren().add(tx);
            panelBotones.getChildren().add(cajaBoton);

        }
    }


    public void update(){
        VBox panelBotones = (VBox) boxIz.getChildrenUnmodifiable().get(1);
        panelBotones.getChildren().clear();
        create();
    }
}

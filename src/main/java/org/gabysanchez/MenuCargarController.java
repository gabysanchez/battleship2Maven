package org.gabysanchez;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Partida;
import org.gabysanchez.dao.DAOFactory;

import java.io.IOException;
import java.util.List;

public class MenuCargarController {

    public void load(){
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        ScrollPane pane = (ScrollPane) box.getChildren().get(0);
        VBox lista = (VBox) pane.getContent();
        DAOFactory.getInstance().getDaoPartidasSerializable().getPartidas();
        List<Partida>partidas = Controller.getInstance().getPartidas();
        pane.setFitToWidth(true);
        partidas.forEach(partida -> {
            HBox fila = new HBox();
            fila.setAlignment(Pos.CENTER);
            fila.setSpacing(15);
            Button btPartida = new Button();
            Button btBorrar = new Button();
            btBorrar.setText("X");
            btBorrar.setVisible(false);
            btPartida.setText(partida.getNombre()+"     "+partida.getFecha());
            btPartida.setMinWidth(400);
            btPartida.getStyleClass().add("buttonMenuSmallC");
            btBorrar.getStyleClass().add("buttonMenuSmallX");
            fila.getChildren().add(btPartida);
            fila.getChildren().add(btBorrar);
            lista.getChildren().add(fila);
            btPartida.setOnAction(e->{
                try {
                    Controller.getInstance().cargar(partida);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            btBorrar.setOnAction(e->{
                Controller.getInstance().borrar(partida);
                recargar(lista);
            });
        });
        HBox Hbox = (HBox) box.getChildrenUnmodifiable().get(1);
        CheckBox check = (CheckBox) Hbox.getChildren().get(1);
        check.setSelected(false);



        //System.out.println(partidas);
    }
    public void visible(){

        Scene escena = App.getScene();
        VBox Vbox = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        ScrollPane pane = (ScrollPane) Vbox.getChildren().get(0);

        HBox Hbox = (HBox) Vbox.getChildrenUnmodifiable().get(1);
        CheckBox check = (CheckBox) Hbox.getChildren().get(1);
        VBox lista = (VBox) pane.getContent();
        lista.getChildren().forEach(fila ->{
            HBox caja = (HBox) fila;
            Button bt = (Button) caja.getChildren().get(1);
            if (check.isSelected()){
                bt.setVisible(true);
            }else {
                bt.setVisible(false);
            }
        });



    }

    public void recargar(VBox lista){
        lista.getChildren().clear();
        load();
    }
    public void volver() throws IOException {
        App.setRoot("menu");
    }
}

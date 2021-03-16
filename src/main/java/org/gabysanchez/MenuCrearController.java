package org.gabysanchez;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Partida;
import org.gabysanchez.dao.DAOFactory;

import java.io.IOException;

public class MenuCrearController {
    @FXML
    public void start() throws IOException {
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        TextField text = (TextField) box.getChildren().get(1);
        Label message = (Label) box.getChildren().get(3);
        if (!text.getText().equals("")){
            Controller.getInstance().getPartida().setNombre(text.getText());
            DAOFactory.getInstance().getDaoPartidasSerializable().getPartidas();
            Boolean crear = true;
            for (int i = 0; i < Controller.getInstance().getPartidas().size(); i++) {
                Partida partida = Controller.getInstance().getPartidas().get(i);
                if (partida.getNombre().equals(text.getText())){
                    message.setText("Ya existe una partida con ese nombre");
                    crear = false;
                    break;
                }
            }
            if (crear){
                Controller.getInstance().colocar();
            }
        }else {
            message.setText("Escribe un nombre de Partida");
        }
    }
    public void volver() throws IOException {
        App.setRoot("menu");
    }
}

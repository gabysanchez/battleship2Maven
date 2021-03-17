package org.gabysanchez;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Data;
import org.gabysanchez.application.Dificultad;
import org.gabysanchez.application.Partida;
import org.gabysanchez.dao.DAOFactory;

import java.io.IOException;

public class MenuAjustesContoller {



    public void setLevel(){
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        Slider slider = (Slider) box.getChildren().get(4);
        Label lavel = (Label) box.getChildren().get(3);
        Double valorFicultad = slider.getValue();
        Dificultad dificultad = null;
        if (valorFicultad == 1.0) {
            lavel.setText(String.valueOf(Dificultad.FACIL));
            dificultad=Dificultad.FACIL;
        } else if (valorFicultad == 2.0) {
            lavel.setText(String.valueOf(Dificultad.NORMAL));
            dificultad=Dificultad.NORMAL;
        } else if (valorFicultad == 3.0) {
            lavel.setText(String.valueOf(Dificultad.DIFICIL));
            dificultad=Dificultad.DIFICIL;
        } else if (valorFicultad == 4.0) {
            lavel.setText(String.valueOf(Dificultad.INFERNAL));
            dificultad=Dificultad.INFERNAL;
        }
        Data data = Controller.getInstance().getData();
        data.setDificultad(dificultad);
        DAOFactory.getInstance().getDaoDataSerializable().setData(data);
    }
    public void volver() throws IOException {
        setName();
        Data data = Controller.getInstance().getData();
        DAOFactory.getInstance().getDaoDataSerializable().setData(data);
        App.setRoot("menu");
    }
    public void setName(){
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        TextField text = (TextField) box.getChildren().get(1);
        Controller.getInstance().getData().setNamePlayer(text.getText());
    }
    public void load(){
        Scene escena = App.getScene();
        VBox box = (VBox) escena.getRoot().getChildrenUnmodifiable().get(0);
        TextField text = (TextField) box.getChildren().get(1);
        Slider slider = (Slider) box.getChildren().get(4);
        Label lavel = (Label) box.getChildren().get(3);
        DAOFactory.getInstance().getDaoDataSerializable().getData();
        Dificultad dificultad = Controller.getInstance().getData().getDificultad();
        lavel.setText(String.valueOf(dificultad));
        if (dificultad.equals(Dificultad.FACIL)) {
            slider.adjustValue(1.0);
        } else if (dificultad.equals(Dificultad.NORMAL)) {
            slider.adjustValue(2.0);
        } else if (dificultad.equals(Dificultad.DIFICIL)) {
            slider.adjustValue(3.0);
        } else if (dificultad.equals(Dificultad.INFERNAL)) {
            slider.adjustValue(4.0);
        }
        text.setText(Controller.getInstance().getData().getNamePlayer());
    }
    public void save(){
        Partida partida = Controller.getInstance().getPartida();
        Controller.getInstance().getPartidas().add(partida);
        DAOFactory.getInstance().getDaoPartidasSerializable().setPartidas(Controller.getInstance().getPartidas());
    }
}

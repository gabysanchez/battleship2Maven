package org.gabysanchez;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.gabysanchez.application.Controller;
import org.gabysanchez.application.EstadoPartida;
import org.gabysanchez.application.Partida;
import org.gabysanchez.dao.DAOFactory;
import org.gabysanchez.ui.tablero.Boton;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MenuBox {
    private static Stage stage;
    private static Scene scene;
    Boolean guardado;

    public MenuBox() {
        this.guardado=false;
    }

    public void crearMenuBox(){
        stage = new Stage();
        try {
            scene = new Scene(loadFXML("menuBox"),100,330);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(250);
        stage.setHeight(400);

        scene.getRoot().setStyle(" -fx-border-color: white");

        Button btSave = (Button) scene.getRoot().getChildrenUnmodifiable().get(1);
        Button btMenu = (Button) scene.getRoot().getChildrenUnmodifiable().get(2);

        if (App.getScene().getRoot().getId().equals("PanelMenu")){
            btSave.disableProperty().setValue(true);
            btMenu.disableProperty().setValue(true);
        }else if (App.getScene().getRoot().getId().equals("PanelCombate")){
            btSave.disableProperty().setValue(false);
        }else {
            btSave.disableProperty().setValue(true);
        }
        for (int i = 0; i < scene.getRoot().getChildrenUnmodifiable().size(); i++) {
            scene.getRoot().getChildrenUnmodifiable().get(i).setStyle("-fx-pref-width: 150px;");
        }

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE: continuar(); break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public void guardar(){
        DAOFactory.getInstance().getDaoPartidasSerializable().getPartidas();
        Controller.getInstance().getPartida().setFecha(new Date());

        for (int i = 0; i < Controller.getInstance().getPartidas().size(); i++) {
            Partida partida = Controller.getInstance().getPartidas().get(i);
            if (partida.getNombre().equals(Controller.getInstance().getPartida().getNombre())){
                Controller.getInstance().removePartida(partida);
            }
        }
        Controller.getInstance().addPartida();
        DAOFactory.getInstance().getDaoPartidasSerializable().setPartidas(Controller.getInstance().getPartidas());
        guardado=true;
    }
    public void menu() throws IOException {
        if (!guardado&&App.getScene().getRoot().getId().equals("PanelCombate")){
            try {
                if (ConfirmBox.display("Alerta","¿Guardar Partida?")){
                    guardar();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.close();
        App.setRoot("menu");
    }
    public void salir(){
        if (!guardado&&App.getScene().getRoot().getId().equals("PanelCombate")){
            try {
                if (ConfirmBox.display("Alerta","¿Guardar Partida?")){
                    guardar();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.close();
        App.getStage().close();
    }
    public void continuar(){
        stage.close();
    }

}

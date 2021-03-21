package org.gabysanchez;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.gabysanchez.application.Controller;
import org.gabysanchez.application.EstadoPartida;
import org.gabysanchez.application.Partida;
import org.gabysanchez.dao.DAOFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage window) throws IOException {
        stage=window;


        stage.setOnCloseRequest(e -> {
            if (App.getScene().getRoot().getId().equals("PanelCombate")){
                e.consume();save();
            }});

        stage.getIcons().add(new Image(App.class.getResourceAsStream("assets/boat.png")));


        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        scene = new Scene(loadFXML("menu"), 1280, 720);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE: menuBox(); break;
                }
            }
        });

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
    public void save(){
        Boolean answer = null;
        try {
            answer = ConfirmBox.display("Alerta","¿Guardar Partida?");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (answer){
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
        }
        stage.close();
    }
    public void menuBox(){
        MenuBox menuBox = new MenuBox();
        menuBox.crearMenuBox();
    }
}
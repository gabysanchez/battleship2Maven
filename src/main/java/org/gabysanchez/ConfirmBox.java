package org.gabysanchez;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class ConfirmBox {

    static Boolean answer;

    public static Boolean display(String title,String menssage) throws IOException {
        Stage stage = new Stage();
        VBox layaut = new VBox();
        Scene scene = new Scene(layaut,200,200);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setWidth(250);
        stage.setMinWidth(250);
        stage.setHeight(150);
        stage.setMinHeight(150);


        Label label = new Label();
        label.setText(menssage);
        HBox hBox = new HBox();

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e ->{
            answer = true;
            stage.close();
        });
        noButton.setOnAction(e ->{
            answer = false;
            stage.close();
        });
        stage.setOnCloseRequest(e -> { e.consume(); });



        scene.getStylesheets().add(ConfirmBox.class.getResource("styles/DarkTheme.css").toExternalForm());
        layaut.getStyleClass().add("backgroundBox");
        label.getStyleClass().add("textDato");
        yesButton.getStyleClass().add("buttonBox");
        noButton.getStyleClass().add("buttonBox");
        hBox.getChildren().addAll(yesButton,noButton);
        layaut.getChildren().addAll(label,hBox);
        layaut.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        layaut.setSpacing(10);
        hBox.setSpacing(20);
        stage.setScene(scene);
        stage.showAndWait();
        return answer;
    }
}

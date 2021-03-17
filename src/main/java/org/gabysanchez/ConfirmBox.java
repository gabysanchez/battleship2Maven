package org.gabysanchez;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ConfirmBox {

    static Boolean answer;

    public static Boolean display(String title,String menssage) throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setWidth(250);
        stage.setMinWidth(250);
        Label label = new Label();
        label.setText(menssage);

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

        VBox layaut = new VBox();
        layaut.getChildren().addAll(label,yesButton,noButton);
        layaut.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layaut);
        stage.setScene(scene);
        stage.showAndWait();
        return answer;
    }
}

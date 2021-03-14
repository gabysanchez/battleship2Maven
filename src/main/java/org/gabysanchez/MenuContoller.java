package org.gabysanchez;

import javafx.fxml.FXML;
import org.gabysanchez.App;
import org.gabysanchez.application.Controller;
import org.gabysanchez.ui.scenes.SceneTablero;
import org.gabysanchez.ui.scenes.SceneTableroColocar;

import java.io.IOException;

public class MenuContoller {

    @FXML
    public void start() throws IOException {
        Controller.getInstance().colocar();
    }
}

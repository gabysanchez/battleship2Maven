package org.gabysanchez;

import java.io.IOException;
import javafx.fxml.FXML;
import org.gabysanchez.application.Partida;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("colocacion");
        Partida partida = new Partida();
    }
}

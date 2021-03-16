package org.gabysanchez;

import javafx.fxml.FXML;
import org.gabysanchez.application.Controller;

import java.io.IOException;

public class MenuContoller {




    public void crear() throws IOException {
        App.setRoot("menuCrear");
    }

    public void ajustes() throws IOException {
        App.setRoot("menuAjustes");
        MenuAjustesContoller menuAjustesContoller = new MenuAjustesContoller();
        menuAjustesContoller.load();
    }
    public void cargarPartida() throws IOException {
        App.setRoot("menuCargar");
        MenuCargarController menuCargarController = new MenuCargarController();
        menuCargarController.load();
    }
    public void btClose(){ }
    public void btMaxi(){ }
    public void btMin(){ }
}

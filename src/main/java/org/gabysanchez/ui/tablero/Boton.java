package org.gabysanchez.ui.tablero;

import javafx.scene.control.Button;

public class Boton extends Button {
    private int x;
    private int y;

    public Boton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

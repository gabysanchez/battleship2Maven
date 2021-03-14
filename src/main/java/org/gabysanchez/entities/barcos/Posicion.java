package org.gabysanchez.entities.barcos;

import java.util.Random;

public enum Posicion {
    VERTICAL,
    HORIZONTAL;

    private static final Posicion[] posicion = Posicion.values();

    public static Posicion getRandomPosition(){
        Random random = new Random();
        return posicion[random.nextInt(posicion.length)];
    }


}

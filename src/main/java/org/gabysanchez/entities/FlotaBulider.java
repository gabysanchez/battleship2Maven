package org.gabysanchez.entities;


import org.gabysanchez.entities.barcos.*;

import java.io.Serializable;

public class FlotaBulider implements IFlotaBulider, Serializable {

    Barco[] arrayBarcos = new Barco[10];

    @Override
    public Barco[] dameFlota() {
        arrayBarcos[0] = new BarcoPortaaviones(1, 1);
        arrayBarcos[1] = new BarcoBuque(1, 1);
        arrayBarcos[2] = new BarcoBuque(1, 1);
        arrayBarcos[3] = new BarcoSubmarino(1, 1);
        arrayBarcos[4] = new BarcoSubmarino(1, 1);
        arrayBarcos[5] = new BarcoSubmarino(1, 1);
        arrayBarcos[6] = new BarcoLancha(1, 1);
        arrayBarcos[7] = new BarcoLancha(1, 1);
        arrayBarcos[8] = new BarcoLancha(1, 1);
        arrayBarcos[9] = new BarcoLancha(1, 1);
        return arrayBarcos;
    }
}

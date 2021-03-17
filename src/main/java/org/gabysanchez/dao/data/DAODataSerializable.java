package org.gabysanchez.dao.data;

import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Data;
import org.gabysanchez.application.Dificultad;
import org.gabysanchez.dao.DAOSerializable;

import java.io.*;

public class DAODataSerializable extends DAOSerializable {

    private Data data;

    public DAODataSerializable(String file) {
        super(file);
        data = new Data();
    }

    public void setData(Data data){
        save(data);
    }

    public void getData(){
        data = (Data) load(data);
        Controller.getInstance().setData(data);

    }
}

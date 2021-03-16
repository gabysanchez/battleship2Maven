package org.gabysanchez.dao;

import java.io.*;

public class DAOSerializable {

    private String file;

    public DAOSerializable(String file) {
        this.file = file;
    }

    public void save(Object objet){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objet);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object load(Object objet){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            objet = (Object) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {

        }
        return objet;
    }
}

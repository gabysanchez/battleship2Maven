package org.gabysanchez.dao;

import org.gabysanchez.dao.data.DAODataSerializable;
import org.gabysanchez.dao.partidas.DAOPartidasSerializable;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAODataSerializable daoDataSerializable;
    private DAOPartidasSerializable daoPartidasSerializable;

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory == null) daoFactory = new DAOFactory();
        return daoFactory;
    }

    public DAODataSerializable getDaoDataSerializable() {
        if(daoDataSerializable== null){
            daoDataSerializable = new DAODataSerializable("data.txt");
        }
        return daoDataSerializable;
    }
    public DAOPartidasSerializable getDaoPartidasSerializable() {
        if(daoPartidasSerializable== null){
            daoPartidasSerializable = new DAOPartidasSerializable("partidas.txt");
        }
        return daoPartidasSerializable;
    }
}

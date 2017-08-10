package com.segvek.terminal.model.lazy;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.dao.TypeEstakadaDAO;
import com.segvek.terminal.dao.mysql.DrainLocationMysqlDAO;
import com.segvek.terminal.dao.mysql.TypeEstakadaMysqlDAO;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.TypeEstakada;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstakadaLazy extends Estakada{
    
    TypeEstakadaDAO typeEstakadaDAO = new TypeEstakadaMysqlDAO();
    DrainLocationDAO drainLocationDAO = new DrainLocationMysqlDAO();
    
    public EstakadaLazy(Long id, TypeEstakada typeEstakada, String name, List<DrainLocation> drainLocations) {
        super(id, typeEstakada, name, drainLocations);
    }

    @Override
    public TypeEstakada getTypeEstakada() {
        TypeEstakada te = super.getTypeEstakada();
        if(te==null){
            try {
                te=typeEstakadaDAO.getTypeEstacadaByEstakada(this);
                setTypeEstakada(te);
            } catch (DAOException ex) {
                Logger.getLogger(EstakadaLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return te;
    }

    @Override
    public List<DrainLocation> getDrainLocations() {
        List<DrainLocation> list = super.getDrainLocations();
        if(list==null){
            try {
                list=drainLocationDAO.getDrainLocationsByEstacada(this);
            } catch (DAOException ex) {
                Logger.getLogger(EstakadaLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
}

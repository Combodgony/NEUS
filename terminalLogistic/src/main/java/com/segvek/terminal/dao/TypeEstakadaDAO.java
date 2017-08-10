package com.segvek.terminal.dao;

import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.TypeEstakada;
import com.segvek.terminal.model.TypeTank;

public interface TypeEstakadaDAO {

    public TypeEstakada getTypeEstacadaByEstakada(Estakada estakada) throws DAOException;

    public TypeEstakada getTypeEstacadaByTypeTank(TypeTank typeTank) throws DAOException;
    
}

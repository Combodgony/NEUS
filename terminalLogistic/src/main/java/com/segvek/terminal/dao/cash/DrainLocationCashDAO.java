package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrainLocationCashDAO implements DrainLocationDAO {

    private DrainLocationDAO locationDAO;

    private List<DrainLocation> list;
    private Map<Long, DrainLocation> admissionMap;

    public DrainLocationCashDAO(DrainLocationDAO drainLocationDAO) {
        this.locationDAO = drainLocationDAO;
        list = new ArrayList<>();
        admissionMap = new HashMap<>();
    }

    @Override
    public DrainLocation getDrainLocationByAdmission(Admission admission) throws DAOException {
        DrainLocation l = admissionMap.get(admission.getId());
        if (l != null) {
            return l;
        }
        l = locationDAO.getDrainLocationByAdmission(admission);
        if (l != null) {
            DrainLocation cashLocation = getFromCash(l.getId());
            if (cashLocation == null) {
                list.add(l);
            } else {
                l = cashLocation;
            }
        } else {
            throw new DAOException();
        }
        admissionMap.put(admission.getId(), l);
        return l;
    }

    @Override
    public List<DrainLocation> getDrainLocationsByEstacada(Estakada estakada) throws DAOException {
        return locationDAO.getDrainLocationsByEstacada(estakada);
    }

    private DrainLocation getFromCash(Long id) {
        for (DrainLocation l : list) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }
}

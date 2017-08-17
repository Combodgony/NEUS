package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DrainLocationCashDAO implements DrainLocationDAO {

    private DrainLocationDAO drainLocationDAO;

    private Set<DrainLocation> set;
    private Map<Long, DrainLocation> admissionMap;
    private boolean all = false;

    public DrainLocationCashDAO(DrainLocationDAO drainLocationDAO) {
        this.drainLocationDAO = drainLocationDAO;
        set = new HashSet<>();
        admissionMap = new HashMap<>();
    }

    @Override
    public DrainLocation getDrainLocationByAdmission(Admission admission) throws DAOException {
        DrainLocation l = admissionMap.get(admission.getId());
        if (l != null) {
            return l;
        }
        l = drainLocationDAO.getDrainLocationByAdmission(admission);
        if (l != null) {
            DrainLocation cashLocation = getFromCash(l.getId());
            if (cashLocation == null) {
                set.add(l);
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
        return drainLocationDAO.getDrainLocationsByEstacada(estakada);
    }

    @Override
    public List<DrainLocation> getAllDrainLocation() throws DAOException {
        List<DrainLocation> list = new ArrayList<>();
        if (all) {
            Iterator i = set.iterator();
            while (i.hasNext()) {
                list.add((DrainLocation) i.next());
            }
            return list;
        }
        list = drainLocationDAO.getAllDrainLocation();
        set.addAll(list);
        list.clear();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            list.add((DrainLocation) i.next());
        }
        all = true;
        return list;
    }

    private DrainLocation getFromCash(Long id) {
        for (DrainLocation l : set) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }

}

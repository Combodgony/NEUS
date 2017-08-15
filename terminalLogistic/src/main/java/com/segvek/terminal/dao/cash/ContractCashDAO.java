package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContractCashDAO implements ContractDAO{
    
    private ContractDAO contractDAO;

    private List<Contract> list;
    private Map<Long, Contract> admissionMap;
    
    private boolean all=false;
    
    public ContractCashDAO(ContractDAO contractDAO){
        this.contractDAO = contractDAO;
        list=new ArrayList<>();
        admissionMap= new HashMap<>();
    }

    @Override
    public List<Contract> getContractsByClient(Client client) throws DAOException {
        return contractDAO.getContractsByClient(client);
    }

    @Override
    public List<Contract> getAllContract() throws DAOException {
        if(!all){
            list = contractDAO.getAllContract();
            admissionMap.clear();
            all=true;
        }
        return list;
    }

    @Override
    public void addContract(Contract contract) throws DAOException {
        contractDAO.addContract(contract);
        list.add(contract);
    }

    @Override
    public void updateContract(Contract contract) throws DAOException {
        contractDAO.updateContract(contract);
    }

    @Override
    public Contract getContractByAdmission(Admission admission) throws DAOException {
        Contract c = admissionMap.get(admission.getId());
        if(c!=null)
            return c;
        c=contractDAO.getContractByAdmission(admission);
        Contract cashContract = getFromCash(c.getId());
        if(cashContract==null){
            list.add(c);
        }else{
            c=cashContract;
        }
        admissionMap.put(admission.getId(), c);
        if(c==null)
            throw new DAOException();
        return c;
    }
    
    private Contract getFromCash(Long id){
        for(Contract c:list)
            if(c.getId()==id)
                return c;
        return null;
    }
}

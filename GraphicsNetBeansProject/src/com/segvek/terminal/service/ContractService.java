package com.segvek.terminal.service;

import com.segvek.terminal.dao.ContentContractDAO;
import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.mysql.ContentContractMysqlDAO;
import com.segvek.terminal.dao.mysql.ContractMysqlDAO;
import com.segvek.terminal.model.ContentContract;
import com.segvek.terminal.model.Contract;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContractService {
    private ContractDAO cdao;
    ContentContractDAO ccdao;
    
    public ContractService() {
        cdao = new ContractMysqlDAO();
        ccdao=new ContentContractMysqlDAO();
    }
    
    
    public void saveContract(Contract contract) throws ServiceException{
        if(contract.getNumber()==null){
            throw  new ServiceException("Для сохранения введите номер контракта!");
        }
        if(contract.getBeginDate()==null || contract.getEndDate()==null){
            throw  new ServiceException("Сроки проекта не заполены!\nДля сохранения заполните начальные сроки проекта!");
        }
        if(contract.getClient()==null){
            throw  new ServiceException("Поле клиента пустое!\nДля сохранения выберите клиента!");
        }
        if(contract.getContent()==null){
            throw  new ServiceException("Договор не содержит перевозок!\nДля сохранения добавте минимум одну единицу груза!");
        }
        try {
            cdao.saveContract(contract);
            for(ContentContract cc:contract.getContent()){
                ccdao.saveContentContract(cc);
            }
        } catch (DAOException ex) {
            Logger.getLogger(ContractService.class.getName()).log(Level.SEVERE, null, ex);
            throw  new ServiceException("Не удалось сохранить договор!\n"+ex.getMessage());
        }
    }
}
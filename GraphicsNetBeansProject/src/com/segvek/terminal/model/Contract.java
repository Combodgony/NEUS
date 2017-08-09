package com.segvek.terminal.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Contract {
    private Long id;
    private String number;
    private Date beginDate;
    private Date endDate;
    private Client client;
    
    private List<ContentContract> content;

    public Contract(Long id, String number, Date beginDate, Date endDate, Client client) {
        this.id = id;
        this.number = number;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return number;
    }

    public List<ContentContract> getContent() {
        return content;
    }

    public void setContent(List<ContentContract> content) {
        this.content = content;
    }

    public void addContentElement(ContentContract cc) {
        if(content==null)
            content=new ArrayList<>();
        content.add(cc);
    }

    public void deleteElementContract(ContentContract cc) {
        if(content!=null){
            content.remove(cc);
        }
    }
    
    
    
}

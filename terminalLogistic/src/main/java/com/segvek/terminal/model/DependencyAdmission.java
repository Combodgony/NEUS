package com.segvek.terminal.model;


public class DependencyAdmission extends MainModel{
     private Admission admission;
     private Admission independet;

    public DependencyAdmission(Long id,Admission admission, Admission independet) {
        super(id);
        this.admission = admission;
        this.independet = independet;
    }

    

    public Admission getIndependnet() {
        return independet;
    }

    public void setIndependet(Admission independet) {
        this.independet = independet;
    }

    public Admission getDepend() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
    
}

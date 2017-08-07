package com.segvek.terminal.model;


public class DependencyAdmission {
     private Admission admission;
     private Admission independet;

    public DependencyAdmission(Admission admission, Admission independet) {
        this.admission = admission;
        this.independet = independet;
    }

    public Admission getIndependet() {
        return independet;
    }

    public void setIndependet(Admission independet) {
        this.independet = independet;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }
}

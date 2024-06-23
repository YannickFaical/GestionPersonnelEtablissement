package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class JourConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJourConge;

    @ManyToOne
    @JoinColumn(name = "idConge")
    private Conge conge;

    @Temporal(TemporalType.DATE)
    private Date dateConge;

    // Autres attributs et méthodes

    public int getIdJourConge() {
        return idJourConge;
    }

    public void setIdJourConge(int idJourConge) {
        this.idJourConge = idJourConge;
    }

    public Conge getConge() {
        return conge;
    }

    public void setConge(Conge conge) {
        this.conge = conge;
    }

    public Date getDateConge() {
        return dateConge;
    }

    public void setDateConge(Date dateConge) {
        this.dateConge = dateConge;
    }
}

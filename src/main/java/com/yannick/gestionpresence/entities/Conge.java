package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConge;


    @ManyToOne
    @JoinColumn(name = "idPersonnel")

    private Personnel personnel;

    @Getter
    @Setter
    @OneToMany(mappedBy = "conge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JourConge> joursConge = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String motif;

    @Enumerated(EnumType.STRING)
    private Status statutValidation;


    public enum Status {

        EN_ATTENTE, ACCEPTE, REFUSE

    }


// Getters and setters

    public int getIdConge() {
        return idConge;
    }

    public void setIdConge(int idConge) {
        this.idConge = idConge;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Date getDateDebut() {
        return dateDebut;
    }


    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Status getStatutValidation() {
        return statutValidation;
    }

    public void setStatutValidation(Status statutValidation) {
        this.statutValidation = statutValidation;
    }

    public void addJourConge(JourConge jourConge) {
        joursConge.add(jourConge);
        jourConge.setConge(this);
    }

    public void removeJourConge(JourConge jourConge) {
        joursConge.remove(jourConge);
        jourConge.setConge(null);
    }

}


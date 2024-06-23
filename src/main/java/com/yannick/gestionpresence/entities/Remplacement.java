package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;

@Entity
public class Remplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personnel_absent_id")
    private Personnel personnelAbsent;

    @ManyToOne
    @JoinColumn(name = "personnel_remplacant_id")
    private Personnel personnelRemplacant;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personnel getPersonnelAbsent() {
        return personnelAbsent;
    }

    public void setPersonnelAbsent(Personnel personnelAbsent) {
        this.personnelAbsent = personnelAbsent;
    }

    public Personnel getPersonnelRemplacant() {
        return personnelRemplacant;
    }

    public void setPersonnelRemplacant(Personnel personnelRemplacant) {
        this.personnelRemplacant = personnelRemplacant;
    }


}

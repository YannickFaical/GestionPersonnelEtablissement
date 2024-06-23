package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;

@Entity
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GestionPresence getGestionPresence() {
        return gestionPresence;
    }

    public void setGestionPresence(GestionPresence gestionPresence) {
        this.gestionPresence = gestionPresence;
    }

    @OneToOne
    @JoinColumn(name = "gestion_presence_id")
    private GestionPresence gestionPresence;
}

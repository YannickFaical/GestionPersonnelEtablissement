package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class GestionPresence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "gestion_presence_etage",
            joinColumns = @JoinColumn(name = "gestion_presence_id"),
            inverseJoinColumns = @JoinColumn(name = "etage_id")
    )

    private Set<Etage> etages = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "gestion_presence_personnel",
            joinColumns = @JoinColumn(name = "gestion_presence_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private Set<Personnel> personnels = new HashSet<>();

    // Getters and setters

}

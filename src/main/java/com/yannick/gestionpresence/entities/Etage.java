package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Etage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String libelle;

    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	

	public Etage(int id, String libelle, Personnel personnel) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.personnel = personnel;
		
	}

	public Etage() {
		super();
	}
    
}

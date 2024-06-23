package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@Entity
public class PlageHoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    private Time heureDebut;

    @Temporal(TemporalType.TIME)
    private Time heureFin;

    @Enumerated(EnumType.STRING)
    private JourSemaine jourSemaine;

    @ManyToOne
    @JoinColumn(name = "id_personnel")
    private Personnel personnel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Time getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}

	public JourSemaine getJourSemaine() {
		return jourSemaine;
	}

	public void setJourSemaine(JourSemaine jourSemaine) {
		this.jourSemaine = jourSemaine;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public PlageHoraire(Long id, Time heureDebut, Time heureFin, JourSemaine jourSemaine, Personnel personnel) {
		super();
		this.id = id;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.jourSemaine = jourSemaine;
		this.personnel = personnel;
	}

	public PlageHoraire() {
		super();
	}

	
    
}

package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Absence {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date jourAbsence;

    @Getter
    @Setter
    private LocalTime heureDebut;
    @Getter
    @Setter
    private LocalTime heureFin;

    @Getter
    @Setter
    private String type; // Type d'absence (congé, maladie, retard, etc.)
    
    private String justifier;
    
    private String justificatif;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Date getJourAbsence() {
		return jourAbsence;
	}

	public void setJourAbsence(Date jourAbsence) {
		this.jourAbsence = jourAbsence;
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public LocalTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getJustifier() {
		return justifier;
	}

	public void setJustifier(String justifier) {
		this.justifier = justifier;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}


//	public Absence(Long id, Personnel personnel, Date jourAbsence, LocalTime heureDebut, LocalTime heureFin,
//			String type, String justifier, String justificatif) {
//		super();
//		this.id = id;
//		this.personnel = personnel;
//		this.jourAbsence = jourAbsence;
//		this.heureDebut = heureDebut;
//		this.heureFin = heureFin;
//		this.type = type;
//		this.justifier = justifier;
//		this.justificatif = justificatif;
//	}

//	public Absence() {
//		super();
//	}


}

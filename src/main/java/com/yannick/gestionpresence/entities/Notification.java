package com.yannick.gestionpresence.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotif;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    private String destinataire;

	@ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

	public Long getIdNotif() {
		return idNotif;
	}

	public void setIdNotif(Long idNotif) {
		this.idNotif = idNotif;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	
	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public Notification(Long idNotif, String message, Date date, String destinataire, Personnel personnel) {
		super();
		this.idNotif = idNotif;
		this.message = message;
		this.date = date;
		this.destinataire = destinataire;
		this.personnel = personnel;
	}

	public Notification() {
		super();
	}

	
}
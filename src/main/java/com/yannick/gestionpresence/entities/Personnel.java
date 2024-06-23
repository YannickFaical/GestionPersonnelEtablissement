package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idPersonnel;
    
    private String nom;
    
	private String prenom;
    private String email;

    private String password;

    @Column(name = "poste")
    private String poste;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB",length = 100000000)
    private byte[] photo;
    
    
    private String estPermanent;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    
    public enum Role {
        ADMIN,
        PERSONNEL
    }

    @OneToMany(mappedBy = "personnel")
    private List<Presence> presences;

    @OneToMany(mappedBy = "personnel")
    private List<Conge> conges;

    @OneToMany(mappedBy = "personnel")
    private List<Retard> retards;

    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL)
    private List<PlageHoraire> plageHoraires;

    @OneToMany(mappedBy = "personnel")
    private List<Notification> notifications;
    
    @OneToMany(mappedBy = "personnel")
    private List<Etage> etages;
    
    public int getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(int idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}


	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public String getEstPermanent() {
		return estPermanent;
	}

	public void setEstPermanent(String estPermanent) {
		this.estPermanent = estPermanent;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Personnel(int idPersonnel, String nom, String prenom, String email, String password, String poste,
			byte[] photo, String estPermanent, Role role, List<Presence> presences, List<Conge> conges,
			List<Retard> retards, List<PlageHoraire> plageHoraires, List<Notification> notifications) {
		super();
		this.idPersonnel = idPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.poste = poste;
		this.photo = photo;
		this.estPermanent = estPermanent;
		this.role = role;
		this.presences = presences;
		this.conges = conges;
		this.retards = retards;
		this.plageHoraires = plageHoraires;
		this.notifications = notifications;
	}

//	public Personnel() {
//		super();
//	}


}

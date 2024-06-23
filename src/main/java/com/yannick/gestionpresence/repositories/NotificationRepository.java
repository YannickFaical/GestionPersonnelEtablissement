package com.yannick.gestionpresence.repositories;

import com.yannick.gestionpresence.entities.Absence;
import com.yannick.gestionpresence.entities.Notification;
import com.yannick.gestionpresence.entities.Personnel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
   
	List<Notification> findByPersonnel(Personnel personnel);
	
	List<Notification> findByDestinataireContaining(String destinataire);
	
	List<Notification> findByDestinataireContainingAndPersonnel(String destinataire, Personnel personnel);
}
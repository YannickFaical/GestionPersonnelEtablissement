package com.yannick.gestionpresence.service;

import com.yannick.gestionpresence.entities.Notification;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
	
	@Autowired
    private NotificationRepository notificationRepository;
	
    
	public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
	
	public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
	
	public List<Notification> getNotificationByPersonnel(Personnel personnel) {
        return notificationRepository.findByPersonnel(personnel);
    }
	    
}
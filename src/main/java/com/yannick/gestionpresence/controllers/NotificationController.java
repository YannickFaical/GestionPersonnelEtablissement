package com.yannick.gestionpresence.controllers;

import com.yannick.gestionpresence.entities.Absence;
import com.yannick.gestionpresence.entities.Notification;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.NotificationRepository;
import com.yannick.gestionpresence.service.AbsenceService;
import com.yannick.gestionpresence.service.NotificationService;
import com.yannick.gestionpresence.service.PersonnelService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class NotificationController {
	
	@Autowired
    private NotificationRepository notificationRepository;
	
	@Autowired
    private AbsenceService absenceService;

    @Autowired
    private PersonnelService personnelService;
	
	@GetMapping("/")
    public String index(Model model) {
		long totalAbsences = absenceService.countTotalAbsences();
		long totalPersonnel = personnelService.countTotalPersonnel();
       
        // Récupérer les notifications où le destinataire contient le mot 'ADMIN'
        List<Notification> notifications = notificationRepository.findByDestinataireContaining("ADMIN");
        Collections.reverse(notifications);
        model.addAttribute("notifications", notifications);
        model.addAttribute("totalAbsences", totalAbsences);
        model.addAttribute("totalPersonnel", totalPersonnel);
        
        return "index"; 
    }
	
	 @GetMapping("/dashbord")
		public String dashbord(Model model, HttpSession session) {
		// Récupérer l'utilisateur connecté depuis la session
	        Personnel user = (Personnel) session.getAttribute("user");
	        long totalAbsences = absenceService.countUserAbsences(user);
	        
	        // Récupérer les notifications concernant le personnel connecté et dont le destinataire contient 'PERSONNEL'
	        List<Notification> notifications = notificationRepository.findByDestinataireContainingAndPersonnel("PERSONNEL", user);
	        Collections.reverse(notifications);
	        model.addAttribute("notifications", notifications);
	        model.addAttribute("totalAbsences", totalAbsences);
	        
			return "utilisateur/dashbord";
		}
	
	
	@GetMapping("/notifications")
    public String getNotifications(Model model) {
        // Récupérer les notifications où le destinataire contient le mot 'ADMIN'
        List<Notification> notifications = notificationRepository.findByDestinataireContaining("ADMIN");
        Collections.reverse(notifications);
        model.addAttribute("notifications", notifications);
        return "notificationsAdmin"; 
    }
	
	@GetMapping("/notificationsUser")
    public String getNotificationsUser(Model model, HttpSession session) {
        // Récupérer l'utilisateur connecté depuis la session
        Personnel user = (Personnel) session.getAttribute("user");
        // Récupérer les notifications concernant le personnel connecté et dont le destinataire contient 'PERSONNEL'
        List<Notification> notifications = notificationRepository.findByDestinataireContainingAndPersonnel("PERSONNEL", user);
        Collections.reverse(notifications);
        model.addAttribute("notifications", notifications);
        return "utilisateur/notifications";
    }

}

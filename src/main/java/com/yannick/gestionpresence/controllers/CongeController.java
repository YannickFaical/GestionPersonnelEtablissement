package com.yannick.gestionpresence.controllers;

import com.yannick.gestionpresence.entities.Conge;
import com.yannick.gestionpresence.entities.Notification;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.CongeRepository;
import com.yannick.gestionpresence.repositories.NotificationRepository;
import com.yannick.gestionpresence.service.AuthService;
import com.yannick.gestionpresence.service.CongeService;
import com.yannick.gestionpresence.service.NotificationService;
import com.yannick.gestionpresence.service.PersonnelService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CongeController {
   

    @Autowired
    private CongeService congeService;
    
    @Autowired
    private CongeRepository congeRepository;
    @Autowired
    private PersonnelService personnelService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;
    
    @Autowired
    public CongeController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/afficherCongeAdmin")
    public String getAllCongesAdmin(Model model, HttpSession session) {
        // Récupérer l'utilisateur connecté depuis la session
        Personnel user = (Personnel) session.getAttribute("user");
            
            List<Conge> conges = congeRepository.findAll();
            model.addAttribute("conges", conges);
            return "afficherConge";
    }
            
            @GetMapping("/afficherCongeUser")
            public String getAllCongesUser(Model model, HttpSession session) {
                // Récupérer l'utilisateur connecté depuis la session
                Personnel user = (Personnel) session.getAttribute("user");
                
            List<Conge> conges = congeRepository.findByPersonnel(user);
            model.addAttribute("conges", conges);
            return "utilisateur/conges";
        }
    


    @GetMapping("/conges/{id}")
    public String getCongeById(@PathVariable("id") int id, Model model) {
        Conge conge = congeService.getCongeById(id);
        if (conge == null) {
            // Gérer le cas où le congé n'est pas trouvé
            return "redirect:/afficherConge"; // Rediriger vers la liste des congés par exemple
        }
        model.addAttribute("conge", conge);
        return "detailConge"; // Assurez-vous d'avoir une vue "detailConge.html" pour afficher les détails du congé
    }

    @GetMapping("/ajouterConge")
    public String showAddCongeForm(Model model) {
        Conge conge = new Conge();
        model.addAttribute("conge", conge);
        // Récupérez la liste de personnel depuis votre service
        List<Personnel> personnelList = personnelService.getAllPersonnel();
        model.addAttribute("personnelList", personnelList);
        return "ajouterCongeAdmin"; // Assurez-vous d'avoir une vue "ajouterConge.html" pour le formulaire d'ajout de congé
    }
    
    @PostMapping("/saveConge")
    public String saveConge(@ModelAttribute("conge") @Valid Conge conge, BindingResult result) {
        if (result.hasErrors()) {
            return "ajouterConge"; // Retourne à la page d'ajout en cas d'erreurs de validation
        }
        congeService.saveConge(conge); // Sauvegarde du congé
        return "redirect:/afficherConge"; // Redirection vers la liste des congés après l'ajout
    }
    
    //Amene vers la page pour demander un congé
    @GetMapping("/demanderConge")
	public String demanderConge() {
		
		return "utilisateur/demanderConge";
	}
    
    //Demander congé
    @PostMapping("/ajouterCongeUser")
    public String demanderConge(@RequestParam("dateDebut") Date dateDebut,
                                @RequestParam("dateFin") Date dateFin,
                                @RequestParam("motif") String motif,
                                HttpSession session,
                                Model model) {
        // Récupérer l'utilisateur connecté à partir de la session
        Personnel user = (Personnel) session.getAttribute("user");

        // Vérifier si le congé peut être accordé
        if (!congeService.canGrantLeaveForHE(user.getIdPersonnel())) {
            model.addAttribute("errorMessage", "Vous ne pouvez pas demander un congé pour le moment!");
            return "utilisateur/demanderConge";
        }

        // Créer une nouvelle demande de congé
        Conge conge = new Conge();
        conge.setDateDebut(dateDebut);
        conge.setDateFin(dateFin);
        conge.setMotif(motif);
        conge.setStatutValidation(Conge.Status.EN_ATTENTE);
        conge.setPersonnel(user); // Assurez-vous que l'entité Conge a une relation avec le personnel

        // Enregistrer la demande de congé
        congeRepository.save(conge);

        // Envoie une notification à l'administrateur
        String message = "Demande de congé reçue de ";
        String destinataire = "ADMIN";
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDestinataire(destinataire);
        notification.setPersonnel(user);
        notification.setDate(new Date()); // Date actuelle
        notificationRepository.save(notification);

        return "redirect:/afficherCongeUser";
    }

    @GetMapping("/editerConge/{idConge}")
    public String showEditCongeForm(@PathVariable("idConge") int idConge, Model model) {
        Conge conge = congeService.getCongeById(idConge);
        if (conge == null) {
            // Gérer le cas où le congé n'est pas trouvé
            return "redirect:/afficherCongeAdmin";
        }
        model.addAttribute("conge", conge);
        return "validerOuRefuserConge"; // Assurez-vous que cette vue existe et est correctement nommée
    }
    

    @PostMapping("/validerOuRefuserConge/{idConge}")
    public String editConge(@PathVariable("idConge") int idConge, @ModelAttribute("conge") Conge updatedConge, HttpSession session) {
        // Récupérer l'ID du personnel à partir du formulaire
        int personnelId = updatedConge.getPersonnel().getIdPersonnel();
        
        // Récupérer le congé existant à partir de la base de données
        Conge existingConge = congeService.getCongeById(idConge);
        if (existingConge == null) {
            // Gérer le cas où le congé n'est pas trouvé
            return "redirect:/afficherCongeAdmin"; 
        }

        // Mettre à jour les champs nécessaires
        existingConge.setStatutValidation(updatedConge.getStatutValidation());

        // Enregistrer les modifications dans la base de données
        congeService.saveConge(existingConge);
        
        // Envoie une notification au personnel concerné
        String message = "Votre demande de congé a été répondue " + (updatedConge.getStatutValidation() == Conge.Status.ACCEPTE ? "acceptée" : "refusée") + ".";
        String destinataire = "PERSONNEL";
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDestinataire(destinataire);
        
        // Récupérer le personnel à partir de son ID
        Personnel personnel = personnelService.findById(personnelId);
        notification.setPersonnel(personnel);
        notification.setDate(new Date()); // Date actuelle
        notificationRepository.save(notification);

        // Rediriger vers la page appropriée
        return "redirect:/afficherCongeAdmin"; 
    }




    @GetMapping("/supprimerConge/{idConge}")
    public String deleteConge(@PathVariable("idConge") int idConge) {
        congeService.deleteConge(idConge);
        return "redirect:/afficherConge"; // Rediriger vers la liste des congés après la suppression
    }
    @GetMapping("/detailsConge/{idConge}")
    public String showCongeDetails(@PathVariable("idConge") int idConge, Model model) {
        Conge conge = congeService.getCongeById(idConge);
        if (conge == null) {
            // Gérer le cas où le congé n'est pas trouvé
            return "redirect:/afficherConge"; // Rediriger vers la liste des congés par exemple
        }
        model.addAttribute("conge", conge);
        return "detailsConge"; // Assurez-vous d'avoir une vue "detailsConge.html" pour afficher les détails
    }

}

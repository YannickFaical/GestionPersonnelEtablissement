package com.yannick.gestionpresence.controllers;


import com.yannick.gestionpresence.entities.Absence;
import com.yannick.gestionpresence.entities.Notification;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.NotificationRepository;
import com.yannick.gestionpresence.service.AbsenceService;
import com.yannick.gestionpresence.service.NotificationService;
import com.yannick.gestionpresence.service.PersonnelService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AbsenceController {

	@Autowired
    private NotificationRepository notificationRepository;

    private final AbsenceService absenceService;
    private final PersonnelService personnelService;

    public AbsenceController(AbsenceService absenceService, PersonnelService personnelService) {
        this.absenceService = absenceService;
        this.personnelService = personnelService;
    }

    // Total Absences
 /* @GetMapping("/dashbord")
    public String getTotalAbsences(Model model, HttpSession session) {
        // Récupérer l'utilisateur connecté à partir de la session
        Personnel user = (Personnel) session.getAttribute("user");
        
        // Vérifier si l'utilisateur est connecté
        if (user != null) {
            // Utiliser l'identifiant du personnel de type int
            int idPersonnel = user.getIdPersonnel();
            
            // Utiliser l'identifiant pour obtenir le nombre total d'absences
            long totalAbsences = absenceService.getTotalAbsencesForUser(idPersonnel);
            
            // Ajouter le nombre total d'absences au modèle pour l'affichage
            model.addAttribute("totalAbsences", totalAbsences);
            
            // Retourner la vue pour afficher le nombre total d'absences
            return "utilisateur/dashbord";
        } else {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            return "redirect:/login";
        }
    }
*/
    
    @GetMapping("/absencesAdmin")
    public String getAllAbsencesAdmin(Model model, HttpSession session) {
        // Récupérer l'utilisateur connecté depuis la session
        Personnel user = (Personnel) session.getAttribute("user");
     // Si c'est un admin, récupérer toutes les absences
        List<Absence> absences = absenceService.getAllAbsences();
        model.addAttribute("absences", absences);
        return "afficherAbsences";
    }
    
        
    @GetMapping("/absencesUser")
    public String getAllAbsences(Model model, HttpSession session) {
        // Récupérer l'utilisateur connecté depuis la session
        Personnel user = (Personnel) session.getAttribute("user");
        
            // Si c'est un personnel, récupérer ses absences seulement
            List<Absence> absences = absenceService.getAbsencesByPersonnel(user);
            model.addAttribute("absences", absences);
            return "utilisateur/absences";
        }
    
    
    @GetMapping("/justifierAbsence")
    public String showJustifyAbsencePage(@RequestParam("absenceId") Long absenceId, Model model) {
        // Récupérez l'absence par son ID (à partir du service ou du référentiel)
        Absence absence = absenceService.getAbsenceById(absenceId);
        // Ajoutez l'absence au modèle pour qu'elle soit accessible dans la vue
        model.addAttribute("absence", absence);
        return "utilisateur/justifierAbsences"; // Nom de votre vue Thymeleaf
    }
    
    @PostMapping("/saveJustification")
    public String saveJustification(@RequestParam("absenceId") Long absenceId,
                                    @RequestParam("justification") String justification,
                                    HttpSession session) {
    	Personnel user = (Personnel) session.getAttribute("user");
    	
        // Récupérer l'absence par son ID
        Absence absence = absenceService.getAbsenceById(absenceId);
        
        // Mettre à jour l'attribut 'justifier' de l'absence
        absence.setJustifier("Justifié");
        
        // Mettre à jour la justification de l'absence si nécessaire
        absence.setJustificatif(justification);
        
        // Envoie une notification à l'administrateur
        String message = "Absence justifiée de ";
        String destinataire = "ADMIN";
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDestinataire(destinataire);
        notification.setPersonnel(user);
        notification.setDate(new Date()); // Date actuelle
        notificationRepository.save(notification);
        
        // Enregistrer l'absence mise à jour dans la base de données
        absenceService.saveAbsence(absence);
        
        return "redirect:/absencesUser";
    }



    @GetMapping("/absences/{id}")
    public String getAbsenceById(@PathVariable("id") int id, Model model) {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence == null) {
            return "redirect:/absences";
        }
        model.addAttribute("absence", absence);
        return "detailAbsence";
    }

    
    @GetMapping("/ajouterAbsence")
    public String showAddAbsenceForm(Model model) {
        Absence absence = new Absence();
        model.addAttribute("absence", absence);
        List<Personnel> personnelList = personnelService.getAllPersonnel();
        model.addAttribute("personnelList", personnelList);
        return "ajouterAbsence";
    }

    
    @PostMapping("/saveAbsence")
    public String saveAbsence(@ModelAttribute("absence") @Valid Absence absence, BindingResult result) {
        if (result.hasErrors()) {
            return "ajouterAbsence";
        }
        absenceService.saveAbsence(absence);
        return "redirect:/absencesAdmin";
    }

    @GetMapping("/editerAbsence/{id}")
    public String showEditAbsenceForm(@PathVariable("id") int id, Model model) {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence == null) {
            return "redirect:/absences";
        }
        model.addAttribute("absence", absence);
        List<Personnel> personnelList = personnelService.getAllPersonnel();
        model.addAttribute("personnelList", personnelList);
        return "modifierAbsence";
    }

    @PostMapping("/absences/editer/{id}")
    public String updateAbsence(@PathVariable("id") Long id,
                                @ModelAttribute("absence") @Valid Absence absence,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "modifierAbsence";
        }
        absence.setId(id);
        absenceService.saveAbsence(absence);
        return "redirect:/absences";
    }

    @GetMapping("/supprimerAbsence/{id}")
    public String deleteAbsence(@PathVariable("id") int id) {
        absenceService.deleteAbsence(id);
        return "redirect:/absences";
    }

    @GetMapping("/detailsAbsence/{id}")
    public String showAbsenceDetails(@PathVariable("id") int id, Model model) {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence == null) {
            return "redirect:/absences";
        }
        model.addAttribute("absence", absence);
        return "detailsAbsence";
    }
}
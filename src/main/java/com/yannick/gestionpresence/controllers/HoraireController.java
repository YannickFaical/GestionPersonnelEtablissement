package com.yannick.gestionpresence.controllers;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.yannick.gestionpresence.entities.JourSemaine;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.entities.PlageHoraire;
import com.yannick.gestionpresence.repositories.PersonnelRepository;
import com.yannick.gestionpresence.repositories.PlageHoraireRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/horaire")
public class HoraireController {

    private final PlageHoraireRepository horaireRepository;
    private final PersonnelRepository personnelRepository;

    @Autowired
    public HoraireController(PlageHoraireRepository horaireRepository, PersonnelRepository personnelRepository) {
        this.horaireRepository = horaireRepository;
        this.personnelRepository = personnelRepository;
    }

    @GetMapping("/afficherPlanning")
    public String afficherPlanning(Model model) {
    	List<PlageHoraire> plagesHoraire = horaireRepository.findAll();
        model.addAttribute("plagesHoraire", plagesHoraire);
        // Ajouter le code pour afficher le planning s'il est nécessaire
        return "planning"; // Assurez-vous d'avoir une vue "planning.html"
    }

    @GetMapping("/ajouterHoraire")
    public String ajouterHoraire(Model model) {
        model.addAttribute("plageHoraire", new PlageHoraire());
        model.addAttribute("joursSemaine", JourSemaine.values());
        model.addAttribute("personnelList", personnelRepository.findAll());
        return "ajouterHoraire";
    }

    @PostMapping("/saveHoraire")
    public String saveHoraire(@ModelAttribute("plageHoraire") @Valid PlageHoraire plageHoraire,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            // S'il y a des erreurs de validation, retourner à la page d'ajout avec les erreurs
            model.addAttribute("joursSemaine", JourSemaine.values());
            model.addAttribute("personnelList", personnelRepository.findAll());
            return "ajouterHoraire"; // Assurez-vous d'avoir une vue "ajouterHoraire.html" avec les erreurs
        }

        Personnel personnel = personnelRepository.findById(plageHoraire.getPersonnel().getIdPersonnel()).orElse(null);
        if (personnel == null) {
            // Gérer le cas où le personnel n'est pas trouvé
            return "redirect:/ajouterHoraire";
        }

        plageHoraire.setPersonnel(personnel);
        horaireRepository.save(plageHoraire);
        model.addAttribute("successMessage", "Horaire du personnel enregistré avec succès.");

        return "redirect:/horaire/afficherPlanning";
    }
    
    @GetMapping("/editerHoraire/{id}")
    public String editerHoraire(@PathVariable("id") int id, Model model) {
        Optional<PlageHoraire> plageHoraireOptional = horaireRepository.findById(id);
        if (plageHoraireOptional.isPresent()) {
            PlageHoraire plageHoraire = plageHoraireOptional.get();
            model.addAttribute("plageHoraire", plageHoraire);
            model.addAttribute("joursSemaine", JourSemaine.values());
            model.addAttribute("personnelList", personnelRepository.findAll());
            return "editerHoraire"; // Assurez-vous d'avoir une vue "editerHoraire.html"
        } else {
            // Gérer le cas où la plage horaire n'est pas trouvée
            return "redirect:/horaire/afficherPlanning";
        }
    }
   

    @PostMapping("/updateHoraire/{id}")
    public String updateHoraire(@PathVariable("id") Long id, @ModelAttribute("plageHoraire") @Valid PlageHoraire plageHoraire,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            // S'il y a des erreurs de validation, retourner à la page d'édition avec les erreurs
            model.addAttribute("joursSemaine", JourSemaine.values());
            model.addAttribute("personnelList", personnelRepository.findAll());
            return "editerHoraire"; // Assurez-vous d'avoir une vue "editerHoraire.html" avec les erreurs
        }

        Personnel personnel = personnelRepository.findById(plageHoraire.getPersonnel().getIdPersonnel()).orElse(null);
        if (personnel == null) {
            // Gérer le cas où le personnel n'est pas trouvé
            return "redirect:/horaire/editerHoraire/" + id;
        }

        plageHoraire.setPersonnel(personnel);
        plageHoraire.setId(id); // Assurez-vous de définir l'ID pour la mise à jour
        horaireRepository.save(plageHoraire);
        model.addAttribute("successMessage", "Horaire du personnel mis à jour avec succès.");

        return "redirect:/horaire/afficherPlanning";
    }
    @GetMapping("/supprimerHoraire/{id}")
    public String supprimerHoraire(@PathVariable("id") int id) {
        horaireRepository.deleteById(id);
        return "redirect:/horaire/afficherPlanning";
    }
}
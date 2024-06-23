package com.yannick.gestionpresence.controllers;



import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.PersonnelRepository;
import com.yannick.gestionpresence.service.PersonnelService;

import jakarta.validation.Valid;

import org.hibernate.query.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private PersonnelRepository personnelRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonnelController.class);
 

    @Autowired
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }
//    @GetMapping
//    public List<Personnel> getAllPersonnel() {
//        return personnelService.getAllPersonnel();
////    }
//@GetMapping("/{id}")
//public Personnel getPersonnelById(@PathVariable int id) {
//    return  personnelService.getPersonnelById(id);
//
////}
//@PostMapping("/add")
//public Personnel addEPersonnel(@RequestBody Personnel personnel) {
//    return personnelService.savePersonnel(personnel);
//}
    

    @GetMapping("/afficherPersonnel")
    public String getAllPersonnel(@RequestParam(defaultValue="") String motcle,
    		Model model) {
    	
        List<Personnel> personnels;
        if(motcle.equals("")) {
            personnels = personnelRepository.findAll();
        	
        }
        else {
            personnels = personnelRepository.findAllByMotCle(motcle);
        }
        logger.info("Nombre de personnels récupérés : {}", personnels.size()); // Ajout du log
        model.addAttribute("personnelList",personnels);

        return "afficherPersonnel";
    }


    @GetMapping("/ajouterPersonnel")
    public String addEPersonnel() {
        return "ajouterPersonnel";
    }

   /* @PostMapping("/savePersonnel")
    public String saveClient( @RequestParam String nom,@RequestParam String prenom, @RequestParam String email, @RequestParam String password,@RequestParam String estPermanent, @RequestParam String poste
                          ,   @RequestParam String photo) {

        Personnel newPersonnel=new Personnel();
        newPersonnel.setNom(nom);
        newPersonnel.setPrenom(prenom);
        newPersonnel.setEmail(email);
        newPersonnel.setPassword(password);
        newPersonnel.setEstPermanent(estPermanent);
        newPersonnel.setPoste(poste);
        newPersonnel.setPhoto(photo);
  
        personnelRepository.save(newPersonnel);
        return "redirect:/afficherPersonnel";
    }   */
    
    /////
    @PostMapping("/savePersonnel")
    public String savePersonnel(@ModelAttribute("personnel") @Valid Personnel personnel,
                                @RequestParam("photo") MultipartFile photoFile,
                                RedirectAttributes redirectAttributes,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Gérer les erreurs de validation
            return "redirect:/ajouterPersonnel";
        }

        try {
            // Votre logique de traitement
        	byte[] photoBytes = photoFile.getBytes();
        	personnel.setPhoto(photoBytes);
        	personnelRepository.save(personnel);
        }catch (IOException e) {
            // Gérer les erreurs d'E/S lors de la manipulation de la photo
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur s'est produite lors de l'ajout du personnel.");
            return "redirect:/ajouterPersonnel";
        } catch (Exception e) {
            // Gérer les autres exceptions
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Une erreur s'est produite.");
            return "redirect:/ajouterPersonnel";
        }

        return "redirect:/afficherPersonnel";
    }
    ////
    
    @GetMapping("/editerPersonnel")
	public String editerPersonnel(@RequestParam("idPersonnel") int idPersonnel,Model model) {
		Personnel personnel=personnelRepository.findAllByIdPersonnel(idPersonnel);
		model.addAttribute("personnel", personnel);
		return "modifierPersonnel";
	}
    
    
  /* @PostMapping("/updatePersonnel")
	public String modifierClient(@RequestParam int idPersonnel, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String password,@RequestParam String estPermanent,
			@RequestParam String poste, @RequestParam String photo) {
		
		personnelRepository.mettreAJourPersonnel(idPersonnel, nom, prenom, email, password,estPermanent,photo);
		return "redirect:/afficherPersonnel";
	}  */
    
    ////
    @PostMapping("/updatePersonnel")
    public String modifierPersonnel(@ModelAttribute("personnel") Personnel personnel,
                                    @RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Vérifier si un fichier a été fourni
            if (!file.isEmpty()) {
                // Convertir le fichier en tableau de bytes
                byte[] photoBytes = file.getBytes();
                // Assigner le tableau de bytes à l'attribut photo de l'objet Personnel
                personnel.setPhoto(photoBytes);
            }

            // Enregistrer ou mettre à jour le personnel
            personnelService.saveOrUpdate(personnel);

            redirectAttributes.addFlashAttribute("successMessage", "Personnel mis à jour avec succès !");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'upload de la photo.");
        }

        return "redirect:/afficherPersonnel";
    }
    
    
    @GetMapping("/supprimerPersonnel")
    public String supprimerPersonnel(@RequestParam("idPersonnel") int idPersonnel) {
        personnelService.deletePersonnel(idPersonnel);
        return "redirect:/afficherPersonnel"; // Rediriger après la suppression
    }

    ///

    @DeleteMapping("/delete/{id}")
    public void deletePersonnel(@PathVariable int id) {
        personnelService.deletePersonnel(id);
    }

   /* @PutMapping("/update/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable int idPersonnel, @RequestBody Personnel personnelDetails) {
    
        Personnel existingPersonnel = personnelService.getPersonnelById(idPersonnel);
        if (existingPersonnel == null) {
            return ResponseEntity.notFound().build();
        }
        existingPersonnel.setIdPersonnel(personnelDetails.getIdPersonnel());
        existingPersonnel.setNom(personnelDetails.getNom());
        existingPersonnel.setPrenom(personnelDetails.getPrenom());
        existingPersonnel.setPoste(personnelDetails.getPoste());
        existingPersonnel.setEmail(personnelDetails.getEmail());
        existingPersonnel.setPassword(personnelDetails.getPassword());
        existingPersonnel.setEstPermanent(personnelDetails.getEstPermanent());
      //  Personnel updatePersonnel = personnelRepository.save(existingPersonnel);
       Personnel updatedPersonnel = personnelService.savePersonnel(existingPersonnel);
        return ResponseEntity.ok(updatedPersonnel);
        
    } */
}

package com.yannick.gestionpresence.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yannick.gestionpresence.entities.Etage;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.service.EtageService;
import com.yannick.gestionpresence.service.PersonnelService;

@Controller
public class AffectationController {

	 @Autowired
	    private PersonnelService personnelService;

	    @Autowired
	    private EtageService etageService;
	    
	    
	    @GetMapping("/afficherEtage")
	    public String showHommesDetages(Model model) {
	        List<Etage> etages = etageService.findAllEtagesWithPersonnel();
	        model.addAttribute("etages", etages);
	        return "afficherEtage";
	    }
	    
	    @GetMapping("/affecterEtage")
	    public String showAffectationForm(Model model) {
	    	model.addAttribute("personnelListHE", personnelService.getHommesDEtages());
	        return "affecterEtage";
	    }

	    @PostMapping("/affectationEtage")
	    public String affecterEtage(@RequestParam("personnelId") int personnelId, @RequestParam("libelle") String libelle) {
	        Personnel personnel = personnelService.findById(personnelId);
	        Etage etage = new Etage();
	        etage.setLibelle(libelle);
	        etage.setPersonnel(personnel);
	        etageService.save(etage);
	        return "redirect:/afficherEtage";
	    }
	}
	
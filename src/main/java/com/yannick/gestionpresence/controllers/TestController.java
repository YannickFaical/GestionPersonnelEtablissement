package com.yannick.gestionpresence.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
   
	   
	    
	    @GetMapping("/modifierEtage")
		public String modifierEtage() {
			
			return "modifierEtage";
		}
	    /*
	    @GetMapping("/afficherCongeUser")
		public String afficherConge() {
			
			return "afficherConge";
		}
	    
	    @GetMapping("/ajouterConge")
		public String ajouterConge() {
			
			return "ajouterConge";
		}
	    
	    @GetMapping("/modifierConge")
		public String modifierConge() {
			
			return "modifierConge";
		}
	    */
	    
	    @GetMapping("/profil")
		public String profil() {
			
			return "profil";
		}
	    
	    

	    
	    
	    @GetMapping("/enretards")
		public String enretards() {
			
			return "utilisateur/retards";	
		}
	    
	    
	    
	    @GetMapping("/justifierRetards")
		public String justifierRetards() {
			
			return "utilisateur/justifierRetards";
		}
	    
	    
	   }

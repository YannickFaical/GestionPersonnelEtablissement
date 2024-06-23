package com.yannick.gestionpresence.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) boolean error, Model model) {
        // Vérifie s'il y a une erreur d'authentification et affiche un message en conséquence
        if (error) {
            model.addAttribute("errorMessage", "Email ou mot de passe incorrect !");
        }
        return "login"; // Page de connexion
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        // Vérifier les informations d'identification
        if (authService.authenticate(email, password)) {
            // Authentification réussie, récupérer l'utilisateur par son email
            Personnel user = authService.getUserByEmail(email);
            
            // Enregistrer l'utilisateur dans la session
            session.setAttribute("user", user);
            
            // Rediriger en fonction du rôle de l'utilisateur
            if (user.getRole() == Personnel.Role.ADMIN) {
                return "redirect:/";
            } else if (user.getRole() == Personnel.Role.PERSONNEL) {
                return "redirect:/dashbord";
            } else {
                // Rediriger vers une page d'erreur si le rôle n'est pas reconnu
                return "redirect:/error";
            }
        } else {
            // Authentification échouée, rediriger vers la page de connexion avec un message d'erreur
            return "redirect:/login?error=true";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        // Déconnexion de l'utilisateur
        session.invalidate();
        
        // Ajouter des en-têtes pour empêcher le navigateur de mettre en cache la page de connexion
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        
        return "redirect:/login";
    }

}

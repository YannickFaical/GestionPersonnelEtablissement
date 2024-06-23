package com.yannick.gestionpresence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.PersonnelRepository;

@Service
public class AuthService {

    @Autowired
    private PersonnelRepository personnelRepository;

    public boolean authenticate(String email, String password) {
        Personnel personnel = personnelRepository.findByEmail(email);
        if (personnel != null && personnel.getPassword().equals(password)) {
            return true; // Authentification réussie
        }
        return false; // Authentification échouée
    }

    public Personnel getUserByEmail(String email) {
        return personnelRepository.findByEmail(email);
    }
    
    public String getUserRoleByEmail(String email) {
        // Récupérer le rôle de l'utilisateur à partir de son email
        Personnel personnel = personnelRepository.findByEmail(email);
        if (personnel != null) {
            return personnel.getRole().name(); // Convertir l'enum en String
        }
        return null; // Gérer le cas où aucun utilisateur n'est trouvé avec cet email
    }

}

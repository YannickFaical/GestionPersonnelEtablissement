package com.yannick.gestionpresence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yannick.gestionpresence.entities.Etage;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.EtageRepository;
import com.yannick.gestionpresence.repositories.PersonnelRepository;

@Service
public class EtageService {
	@Autowired
    private EtageRepository etageRepository;
	private PersonnelRepository personnelRepository;

    public Etage affecterPersonnelAEtage(int personnelId, String libelle) {
        // Récupérer le personnel par son ID
        Personnel personnel = personnelRepository.findById(personnelId)
            .orElseThrow(() -> new IllegalArgumentException("Personnel non trouvé"));
        
        // Créer un nouvel étage et l'affecter au personnel
        Etage etage = new Etage();
        etage.setLibelle(libelle);
        etage.setPersonnel(personnel);
        
        return etageRepository.save(etage);
    }
    
    public void save(Etage etage) {
        etageRepository.save(etage);
    }
    
    //gere affiche HE
    public List<Etage> findAllEtagesWithPersonnel() {
        return etageRepository.findAllWithPersonnel();
    }
}

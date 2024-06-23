package com.yannick.gestionpresence.service;

import com.yannick.gestionpresence.entities.Absence;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    
    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public Absence getAbsenceById(int id) {
        return absenceRepository.findById((long) id).orElse(null);
    }

    public void saveAbsence(Absence absence) {
        absenceRepository.save(absence);
    }

    public void deleteAbsence(int id) {
        absenceRepository.deleteById((long) id);
    }
    
    public List<Absence> getAbsencesByPersonnel(Personnel personnel) {
        return absenceRepository.findByPersonnel(personnel);
    }
    
    public Absence getAbsenceById(Long absenceId) {
        // Utiliser le repository pour récupérer l'absence par son ID
        return absenceRepository.findById(absenceId).orElse(null);
    }
    
    public long countTotalAbsences() {
        return absenceRepository.count();
    }
    
    public long countUserAbsences(Personnel user) {
        return absenceRepository.countByPersonnel(user);
    }
    
  /*  public long getTotalAbsencesForUser(int idPersonnel) {
        
        return absenceRepository.countByIdPersonnel(idPersonnel);
    }
  */  
}
package com.yannick.gestionpresence.service;

import com.yannick.gestionpresence.entities.Conge;
import com.yannick.gestionpresence.entities.Personnel;
import com.yannick.gestionpresence.repositories.CongeRepository;
import com.yannick.gestionpresence.repositories.PersonnelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;


@Service
public class CongeService {
    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private PersonnelRepository personnelRepository;
  /*  
    public Conge createLeaveRequest(int idPersonnel, Conge conge) throws AccessDeniedException {
        Personnel personnel = personnelRepository.findById(idPersonnel).orElseThrow(() -> new EntityNotFoundException("Personnel not found"));
        if (!personnel.getRole().equals("ADMIN")) {
            conge.setPersonnel(personnel);
            conge.setStatutValidation(Conge.Status.EN_ATTENTE);
            congeRepository.save(conge);
        } else {
            throw new AccessDeniedException("Admin cannot make a leave request");
        }
        return conge;

    }
    public void approveLeaveRequest(int idConge) throws AccessDeniedException {

        Conge conge = congeRepository.findById(idConge).orElseThrow(() -> new EntityNotFoundException("Conge not found"));

        if (conge.getStatutValidation().equals(Conge.Status.ACCEPTE)) {

            conge.setStatutValidation(Conge.Status.ACCEPTE);

            congeRepository.save(conge);

        } else {

            throw new AccessDeniedException("Leave request already approved or rejected");

        }
    }

    public void rejectLeaveRequest(int idConge) throws AccessDeniedException {
        Conge conge = congeRepository.findById(idConge).orElseThrow(() -> new EntityNotFoundException("Conge not found"));
        if (conge.getStatutValidation().equals(Conge.Status.EN_ATTENTE) || conge.getStatutValidation().equals(Conge.Status.ACCEPTE)) {
            conge.setStatutValidation(Conge.Status.REFUSE);
            congeRepository.save(conge);
        } else {
            throw new AccessDeniedException("Leave request already approved or rejected");
        }
    }

*/
    
    
    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }

    public Conge getCongeById(int idConge) {
        return congeRepository.findById(idConge).orElse(null);
    }

    public Conge saveConge(Conge conge) {
        return congeRepository.save(conge);
    }

    public void deleteConge(int idConge) {
        congeRepository.deleteById(idConge);
    }

   public void  deleteCongeByPersonnelId(int idPersonnel){

      // List<Conge> conges = congeRepository.findByPersonnel_Id(idPersonnel);

       List<Conge> conges = congeRepository.findByPersonnelIdPersonnel(idPersonnel);

       // Supprimer chaque congé individuellement
       for (Conge conge : conges) {
           congeRepository.deleteById(conge.getIdConge());
       }

    }
    public List<Conge> getCongeByPersonnelIdPersonnel(int idPersonnel) {
        return congeRepository.findByPersonnel_IdPersonnel(idPersonnel);
    }

    public Conge getCongeByPersonnelIdPersonnelAndIdConge(int idPersonnel, int idConge) {
        return congeRepository.getCongeByPersonnelIdPersonnelAndIdConge(idPersonnel, idConge);
    }
    
    
    public boolean canGrantLeaveForHE(int personnelId) {
        Personnel personnel = personnelRepository.findById(personnelId).orElse(null);
        if (personnel == null || !"HE".equals(personnel.getPoste())) {
            return true; // Si le personnel n'est pas un HE, il n'y a pas de restriction
        }
        
        int totalHECount = congeRepository.countPersonnelByPoste("HE");
        int acceptedHECount = congeRepository.countAcceptedHELeaves("HE");

        return acceptedHECount < totalHECount - 1;
    }
    

//    public Conge getCongeByIdPersonnel(int idPersonnel) {
//        List<Conge> conges = congeRepository.findByPersonnelIdPersonnel(idPersonnel);
//
//        for (Conge conge : conges) {
//            congeRepository.findById(conge.getIdConge());
//        }
//
//        return null;
//    }
}

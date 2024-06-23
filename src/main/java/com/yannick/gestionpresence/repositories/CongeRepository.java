package com.yannick.gestionpresence.repositories;

import com.yannick.gestionpresence.entities.Conge;
import com.yannick.gestionpresence.entities.Personnel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Integer> {
   // List<Conge> findByPersonnelId(int idPersonnel);

    List<Conge> findByPersonnelIdPersonnel(int idPersonnel);
    
    List<Conge> findAll();
    
    List<Conge> findByPersonnel(Personnel personnel);

    List<Conge> findByPersonnel_IdPersonnel(int idPersonnel);

    Conge getCongeByPersonnelIdPersonnel(int idPersonnel);

    Conge getCongeByPersonnelIdPersonnelAndIdConge(int idPersonnel, int idConge);
    
    @Query("SELECT COUNT(c) FROM Conge c WHERE c.personnel.poste = :poste AND c.statutValidation = 'ACCEPTE'")
    int countAcceptedHELeaves(@Param("poste") String poste);

    @Query("SELECT COUNT(p) FROM Personnel p WHERE p.poste = :poste")
    int countPersonnelByPoste(@Param("poste") String poste);

    // List<Conge> findByPersonnel_Id(int idPersonnel);
    //List<Personnel> findByPersonnel_Id(int idPersonnel);
 //  @Transactional
 //  @Modifying
  // @Query("DELETE FROM Conge c WHERE c.personnel.idPersonnel = :idPersonnel")
 //  List<Personnel> deleteCongeByPersonnelId(@Param("idPersonnel") int idPersonnel);


}

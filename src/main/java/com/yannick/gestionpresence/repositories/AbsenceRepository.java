package com.yannick.gestionpresence.repositories;

import com.yannick.gestionpresence.entities.Absence;
import com.yannick.gestionpresence.entities.Personnel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

	    List<Absence> findByPersonnel(Personnel personnel);
	    
	    long countByPersonnel(Personnel personnel);
	    

}

package com.yannick.gestionpresence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yannick.gestionpresence.entities.Etage;

public interface EtageRepository extends JpaRepository<Etage, Integer>{

	@Query("SELECT e FROM Etage e JOIN FETCH e.personnel")
    List<Etage> findAllWithPersonnel();
}

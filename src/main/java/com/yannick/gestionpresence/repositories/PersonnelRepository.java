package com.yannick.gestionpresence.repositories;

import com.yannick.gestionpresence.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
	
	public Personnel findAllByIdPersonnel(@Param("idPersonnel") int idPersonnel);

    @Query("select c from Personnel c where c.nom like %:motcle%")
   public List<Personnel> findAllByMotCle(@Param("motcle") String motcle);
    
    
    @Transactional
	@Modifying
	@Query("update Personnel c set c.nom=:nom, c.prenom=:prenom, c.email=:email, c.photo=:photo, c.estPermanent=:estPermanent, c.role=:role where c.idPersonnel=:idPersonnel")
	public void mettreAJourPersonnel(int idPersonnel, String nom, String prenom, String email, String photo, 
			String estPermanent, String role);

    Personnel findByEmail(String email);

    // List<Personnel> findPersonnelById(int idPersonnel);

    List<Personnel> findByPoste(String poste); // Méthode pour trouver les HE
    
    List<Personnel> findAllByPoste(String poste);
}

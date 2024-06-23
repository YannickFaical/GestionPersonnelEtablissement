package com.yannick.gestionpresence.repositories;

import com.yannick.gestionpresence.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence, Integer> {

}

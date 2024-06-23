package com.yannick.gestionpresence.service;

import com.yannick.gestionpresence.entities.Presence;
import com.yannick.gestionpresence.repositories.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenceService {
    @Autowired
    private PresenceRepository presenceRepository;

    public List<Presence> getAllPresences() {
        return presenceRepository.findAll();
    }

    public Presence getPresenceById(int id) {
        return presenceRepository.findById(id).orElse(null);
    }

    public Presence savePresence(Presence presence) {
        return presenceRepository.save(presence);
    }

    public void deletePresence(int id) {
        presenceRepository.deleteById(id);
    }
}

package com.yannick.gestionpresence.service;

import com.yannick.gestionpresence.entities.Retard;
import com.yannick.gestionpresence.repositories.RetardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetardService {
    @Autowired
    private RetardRepository retardRepository;

    public List<Retard> getAllRetards() {
        return retardRepository.findAll();
    }

    public Retard getRetardById(int id) {
        return retardRepository.findById(id).orElse(null);
    }

    public Retard saveRetard(Retard retard) {
        return retardRepository.save(retard);
    }

    public void deleteRetard(int id) {
        retardRepository.deleteById(id);
    }
}

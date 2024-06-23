package com.yannick.gestionpresence.service;


import com.yannick.gestionpresence.entities.Personnel;

import com.yannick.gestionpresence.entities.Presence;
import com.yannick.gestionpresence.repositories.PersonnelRepository;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
@Transactional
@Service
public class PersonnelService {

//    @Autowired
//    private FaceRecognitionService faceRecognitionService;
    @Autowired
    private PersonnelRepository personnelRepository;

    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    public Personnel getPersonnelById(int id) {
        return personnelRepository.findById(id).orElse(null);
    }

    public Personnel savePersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    public void deletePersonnel(int idPersonnel) {
        personnelRepository.deleteById(idPersonnel);
    }

    // Méthode pour enregistrer ou mettre à jour un Personnel
    public void saveOrUpdate(Personnel personnel) {
        personnelRepository.save(personnel);
    }
    
    public Personnel findById(int id) {
        return personnelRepository.findById(id).orElse(null);
    }
    
    // Pour l'affectation des HE
    public List<Personnel> getHommesDEtages() {
        return personnelRepository.findByPoste("HE");
    }
    
    public List<Personnel> findAllByPoste(String poste) {
        return personnelRepository.findAllByPoste(poste);
    }
    
    public long countTotalPersonnel() {
        return personnelRepository.count();
    }

  

//    public byte[] getPersonnelPhoto(int idPersonnel) {
//        // Récupérer le personnel par son identifiant
//        Personnel personnel = personnelRepository.findById(idPersonnel)
//                                                  .orElseThrow(() -> new RuntimeException("Personnel introuvable"));
//
//        // Récupérer l'attribut photo de la classe Personnel
//        byte[] photo = personnel.getPhoto();
//
//        return photo;
//    }

//    public Personnel authenticate(InputStream imageStream) throws Exception {
//
//        Mat image = Imgcodecs.imread("path/to/identity_photo.png");
//
//        Mat detectedImage = faceRecognitionService.detectFace(image);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        Imgcodecs.imencode(".png", detectedImage, outputStream);
//
//        byte[] bytes = outputStream.toByteArray();
//
//        InputStream inputStream = new ByteArrayInputStream(bytes);
//
//        Personnel personnel = new Personnel();
//
//        personnel.setPhoto(inputStream.readAllBytes());
//
//        if (faceRecognitionService.recognizeFace(Imgcodecs.imread(inputStream), personnel)) {
//
//            return personnel;
//
//        } else {
//
//            throw new Exception("Personnel not recognized");
//
//        }
//
//    }
//
//
//    public Presence markPresent(Personnel personnel) {
//
//        return faceRecognitionService.markPresent(personnel);
//
//    }

}


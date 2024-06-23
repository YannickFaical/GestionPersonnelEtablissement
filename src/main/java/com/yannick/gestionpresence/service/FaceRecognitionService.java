package com.yannick.gestionpresence.service;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;


import com.yannick.gestionpresence.entities.Personnel;

import com.yannick.gestionpresence.entities.Presence;

//import org.opencv.imgproc.Imgproc;

import org.opencv.objdetect.CascadeClassifier;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FaceRecognitionService {
//
//    Personnel personnel;
//    private static final String FACE_CASCADE_PATH = "haarcascade_frontalface_default.xml";
//
//    private static final CascadeClassifier FACE_CASCADE = new CascadeClassifier(FACE_CASCADE_PATH);
//
//
//    public Mat detectFace(Mat image) {
//
//        MatOfRect faces = new MatOfRect();
//
//        FACE_CASCADE.detectMultiScale(image, faces);
//
//        return image;
//
//    }
//
//
//    public Mat preprocessImage(Mat image) {
//
//        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
//
//        Imgproc.equalizeHist(image, image);
//
//        return image;
//
//    }
//
//
//    public boolean recognizeFace(Mat image) {
//
//        // Detect faces in the image
//
//        List<Rect> faces = new ArrayList<>();
//
//        FACE_CASCADE.detectMultiScale(image, (MatOfRect) faces, 1.1, 3, 0, new Size(30, 30));
//
//
//        // If no faces are detected, return false
//
//        if (faces.isEmpty()) {
//
//            return false;
//
//        }
//
//
//        // Extract the largest face from the image
//
//        Rect face = faces.get(0);
//
//        for (Rect rect : faces) {
//
//            if (rect.width * rect.height > face.width * face.height) {
//
//                face = rect;
//
//            }
//
//        }
//
//
//        // Extract the face from the image
//
//        Mat faceMat = new Mat(image, face);
//
//
//        // Recognize the face
//
//        String name = String.valueOf(recognizeFace(faceMat));
//
//
//        // If the recognized face matches the name of the personnel, return true
//
//        if (name.equals(personnel.getNom())) {
//
//            return true;
//
//        }
//
//
//        // Otherwise, return false
//
//        return false;
//
//    }
//
//    public Presence markPresent(Personnel personnel) {
//
//        Presence presence = new Presence();
//
//        presence.setPersonnel(personnel);
//
//        presence.setHeureArrivee(new Date());
//
//        presence.setPresent(true);
//
//        return presence;
//
//    }
//

}

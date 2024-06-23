package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Presence {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;
    
    
 
    private Date date;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureArrivee;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDepart;

    @Getter
    @Setter
    private  boolean present;
    
    


//    @JoinColumn(name = "type_conge_id")
//    private String typeConge;


    // Getters and setters
}

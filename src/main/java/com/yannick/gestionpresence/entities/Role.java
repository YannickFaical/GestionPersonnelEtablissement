package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Role {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;


    @JoinColumn(name = "type_role_id")
    private String typeRole;
}

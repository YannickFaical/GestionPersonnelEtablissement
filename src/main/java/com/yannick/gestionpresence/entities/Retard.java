package com.yannick.gestionpresence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Retard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date date;

    @Getter
    @Setter
    @Temporal(TemporalType.TIME)
    private Date heureArriveePrevue;

    @Getter
    @Setter
    @Temporal(TemporalType.TIME)
    private Date heureArriveeReelle;


}

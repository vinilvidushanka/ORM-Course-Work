package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "eid")
    private Enrollment enrollment;

    private Double amount;

    private LocalDate date;
}

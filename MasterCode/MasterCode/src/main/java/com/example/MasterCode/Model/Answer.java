package com.example.MasterCode.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;

    @Lob
    private byte[] imageData; // Store image data directly in the database

    @ManyToOne // Many answers can belong to one user
    @JoinColumn(name = "student_id", nullable = false) // Foreign key
    private Student student; // Reference to the user who submitted the answer
    @ManyToOne // Many answers can belong to one problem
    @JoinColumn(name = "problem_id", nullable = false) // Foreign key
    private Problem problem; // Reference to the associated problem

}

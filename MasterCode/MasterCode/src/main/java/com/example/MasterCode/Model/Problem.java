package com.example.MasterCode.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problem_id;
    private String description;
    private String link;
    @OneToMany
    List<Answer> answers=new ArrayList<>();

}

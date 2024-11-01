package com.example.MasterCode.Service;

import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    @Autowired
    ProblemRepository problemRepository;
    public List<Problem> findallproblems(){
        return problemRepository.findAll();
    }
    public String saveProblem(Problem problem){
        problemRepository.save(problem);
        return "success";
    }
    public Optional<Problem> findById(Long id){
        return problemRepository.findById(id);

    }
}

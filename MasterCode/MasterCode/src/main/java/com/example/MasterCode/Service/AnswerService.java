package com.example.MasterCode.Service;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public String save(Answer answer){
        answerRepository.save(answer);
        return "success";
    }
    public List<Answer> findByProblem(Problem problem){
        return answerRepository.findByProblem(problem);
    }
    public List<Answer> findAll(){
        return answerRepository.findAll();
    }

}

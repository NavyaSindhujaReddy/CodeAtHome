package com.example.MasterCode.Service;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Repository.AnswerRepository;
import com.example.MasterCode.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ProblemService problemService;
    @Autowired
    StudentRepository studentRepository;
    public void save(Answer answer){
        answerRepository.save(answer);
    }
    public List<Answer> findByProblem(Problem problem){
        return answerRepository.findByProblem(problem);
    }
    public List<Answer> findAll(){
        return answerRepository.findAll();
    }
    public List<Answer> findProblemsById(String user)
    {
        return answerRepository.findProblemsById(user);
    }
    public List<Answer> findByStudentRollNo(String rollno) {
        Student student = studentRepository.findUser(rollno);
        System.out.println(student);// Find the student by roll number
        if (student != null) {
            return answerRepository.findByStudent(student); // Fetch answers associated with the found student
        }
        return new ArrayList<>();  // Fetch answers using repository method
    }

    public Answer findByStudentAndProblemId(String rollno,Long problem_id){
        Problem problem=problemService.findByProblemId(problem_id);
        Student student=studentRepository.findUser(rollno);
        if(problem==null && student==null)
        {
            return null;
        }
        return answerRepository.findByStudentAndProblemId(student,problem);
    }

}

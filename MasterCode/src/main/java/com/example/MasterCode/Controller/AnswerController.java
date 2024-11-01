package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Repository.AnswerRepository;
import com.example.MasterCode.Repository.ProblemRepository;
import com.example.MasterCode.Service.AnswerService;
import com.example.MasterCode.Service.ProblemService;
import com.example.MasterCode.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("answers")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @Autowired
    ProblemService problemService;
    @Autowired
    StudentService studentService;
       @PostMapping("/submit")
        public String submitAnswer(
              @RequestParam("imageData") MultipartFile imageFile,
              @RequestParam("studentId") Long studentId,
              @RequestParam("problemId") Long problemId) throws IOException {

               Answer answer = new Answer();
                answer.setImageData(imageFile.getBytes()); // Convert image file to byte array
              Student student = studentService.findById(studentId).orElseThrow();
              int c=student.getCount();
              student.setCount(++c);
               Problem problem = problemService.findById(problemId).orElseThrow();
               answer.setStudent(student);
               answer.setProblem(problem);
               return answerService.save(answer);
       }
    @GetMapping("/{problemId}/answers")
    public List<Answer>  getAnswers(@PathVariable Long problemId) {

        Problem problem = problemService.findById(problemId).orElse(null);
        return answerService.findByProblem(problem);
    }
    @GetMapping("/")
    public List<Answer> allAnswers(){
           return answerService.findAll();
    }

}

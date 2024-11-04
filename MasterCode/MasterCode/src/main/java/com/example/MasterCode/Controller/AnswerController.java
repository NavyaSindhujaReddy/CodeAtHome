package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Repository.AnswerRepository;
import com.example.MasterCode.Repository.ProblemRepository;
import com.example.MasterCode.Service.AnswerService;
import com.example.MasterCode.Service.ProblemService;
import com.example.MasterCode.Service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
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
       public ModelAndView submitAnswer(@RequestParam("image") MultipartFile imageFile, @RequestParam("user") String studentId, @RequestParam("problemId") Long problemId, HttpServletRequest request) throws IOException {
           Answer answer = new Answer();
           byte[] imageBytes = imageFile.getBytes();
           answer.setImageData(imageBytes);
           Student student=studentService.findUser(studentId);
           int c=student.getCount();
           c++;
           student.setCount(c);
           Problem problem = problemService.findById(problemId).orElseThrow();
           answer.setStudent(student);
           answer.setProblem(problem);
           answerService.save(answer);
           studentService.add(student);
           ModelAndView m=new ModelAndView();
           String user=request.getParameter("user");
           String name=studentService.findUserName(user);
           m.setViewName("Dashboard");
           m.addObject("name",name);
           m.addObject("user",user);
           List<Problem> p=problemService.findallproblems();
           m.addObject("sp",p);
           return m;
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

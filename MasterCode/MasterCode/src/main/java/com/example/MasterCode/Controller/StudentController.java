package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Service.AnswerService;
import com.example.MasterCode.Service.ProblemService;
import com.example.MasterCode.Service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    ProblemService problemService;
    @Autowired
    AnswerService answerService;
    @GetMapping("/")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }
    @GetMapping("{studentid}")
    public Optional<Student> getStudentById(@PathVariable Long studentid){
        return studentService.findById(studentid);

    }
    @PostMapping("/register")
    public ModelAndView addStudent(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String roll = request.getParameter("rollno");
        Student s = studentService.findUser(roll);
        ModelAndView m=new ModelAndView("login");
        if (s == null) {
            Student newStudent = new Student();
            newStudent.setCount(0);
            newStudent.setPassword(request.getParameter("password"));
            newStudent.setRollno(roll);
            newStudent.setSection(request.getParameter("section"));
            newStudent.setStudentname(request.getParameter("name"));
            studentService.add(newStudent);
            m.addObject("registerMsg", "Registered Successfully");
        } else {
            System.out.println("hi");
            m.addObject("registerMsg","User Already Exists");
        }
        return m;
    }

    @GetMapping("/login")
    public ModelAndView verifyUser(HttpServletRequest request)
    {
        String user=request.getParameter("user");
        String pass=request.getParameter("pass");
        if(user.equals("Teacher")&&pass.equals("Teacher")) {
        ModelAndView m = new ModelAndView();
        m.setViewName("TeacherView");
        m.addObject("user",user);
        return m;
    }
        Student s=studentService.findUser(user);
        if(s==null)
        {
            ModelAndView m=new ModelAndView();
            m.addObject("msg","User Not Found");
            m.setViewName("login");
            return m;
        }
        else if(s.getPassword().equals(pass))
        {
            ModelAndView m = new ModelAndView();
            String name=studentService.findUserName(user);
            m.setViewName("Dashboard");
            m.addObject("name",name);
            m.addObject("user",user);
            List<Problem> p=problemService.findallproblems();
            m.addObject("sp",p);
            return m;
        }
        ModelAndView m=new ModelAndView();
        m.addObject("msg","Wrong Password");
        m.setViewName("login");
        return m;
    }
    @GetMapping("/count/{studentid}")
    public int noOfProblems (@PathVariable Long studentid){
        return studentService.noOfProblems(studentid);
    }
    @PostMapping("/changepassword")
    public ModelAndView changePassword(HttpServletRequest request)
    {
        String user=request.getParameter("user");
        String oldp=request.getParameter("oldp");
        String newp=request.getParameter("newp");
        String confp=request.getParameter("confp");
        Student s=studentService.findUser(user);
        ModelAndView mv=new ModelAndView();
        if(s.getPassword().equals(oldp))
        {
            studentService.updatePassword(user,newp);
            mv.addObject("msg","Password Changed");
            mv.setViewName("DashBoard");
        }
        else {
            mv.addObject("msg", "Old Password Doesn't Match");
            mv.setViewName("changepassword");
        }
        mv.addObject("user",user);
        String name=studentService.findUserName(user);
        mv.addObject("name",name);
        List<Problem> p=problemService.findallproblems();
        mv.addObject("sp",p);
        return mv;
    }
    @GetMapping("/section/{section}")
    @ResponseBody
    public List<Student> sectionwiseStudents(@PathVariable String section){
        return studentService.sectionwiseStudents(section);
    }
    @GetMapping("{rollno}/answers")
    public ResponseEntity<List<Answer>> getAnswersByStudentRollNo(@PathVariable String  rollno) {
        System.out.println("Fetching answers for rollno: " + rollno);
        List<Answer> answers = answerService.findByStudentRollNo(rollno); // Method to fetch answers by roll number
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

}

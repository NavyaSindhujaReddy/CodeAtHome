package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("/")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }
    @GetMapping("{studentid}")
    public Optional<Student> getStudentById(@PathVariable Long studentid){
        return studentService.findById(studentid);

    }
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        return studentService.add(student);

    }
    @GetMapping("/count/{studentid}")
    public int noOfProblems (@PathVariable Long studentid){
        return studentService.noOfProblems(studentid);
    }
    @GetMapping("/login/{user}/{pass}")
    public String verifyUser(@PathVariable String user,@PathVariable String pass) {

        String s = studentService.findUser(user);
        if (s.equals(""))
            return "User Not Found";
        if (s.equals(pass)) {
            return "Success";
        }
        return "Invaid Password";
    }
    @PostMapping("/updatePassword/{rollno}/{password}")
    public String updatePassword(@PathVariable String rollno,@PathVariable String password){
        System.out.println(password);
        return studentService.updatePassword(rollno,password);
    }
}

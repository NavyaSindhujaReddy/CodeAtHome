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
    @GetMapping("/login/{user}/{pass}")
    public String verifyUser(@PathVariable String user,@PathVariable String pass)
    {
        Student s=studentService.findUser(user);
        if(s==null)
            return "User Not Found";
        if(s.getPassword().equals(pass))
        {
            return "Success";
        }
        return "Invaid Password";
    }
    @GetMapping("/count/{studentid}")
    public int noOfProblems (@PathVariable Long studentid){
        return studentService.noOfProblems(studentid);
    }
    @PostMapping("/update/{user}/{password}")
    public String updatePassword(@PathVariable String user,@PathVariable String password)
    {
        Student s=studentService.findUser(user);
        if(s==null)
            return "No User";
        return studentService.updatePassword(user,password);
    }
}

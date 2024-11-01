package com.example.MasterCode.Service;

import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public List<Student> findAll(){
        return studentRepository.findAll();
    }
    public Optional<Student> findById(Long id){
        return studentRepository.findById(id);
    }
    public String add(Student student){
        studentRepository.save(student);
        return "success";
    }
    public int noOfProblems(Long studentid){
        return studentRepository.noOfProblems(studentid);
    }
    public String findUser(String user)
    {
        return studentRepository.findUser(user);
    }
    public String updatePassword(String user,String password)
    {
        studentRepository.updatePassword(user,password);
        return "Updated";
    }
}

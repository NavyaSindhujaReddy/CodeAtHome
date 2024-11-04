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
    public void add(Student student){
        studentRepository.save(student);
    }
    public int noOfProblems(Long studentid){
        return studentRepository.noOfProblems(studentid);
    }
    public Student findUser(String user)
    {
        return studentRepository.findUser(user);
    }
    public String updatePassword(String user,String password)
    {
        studentRepository.updatePassword(user,password);
        return "Updated";
    }
    public String findUserName(String roll)
    {
        return studentRepository.findUserName(roll);
    }
    public List<Student> findBySection(String section)
    {
        return studentRepository.findBySection(section);
    }
    public List<Student> sectionwiseStudents(String section){
        return studentRepository.findBySectionWise(section);
    }

}

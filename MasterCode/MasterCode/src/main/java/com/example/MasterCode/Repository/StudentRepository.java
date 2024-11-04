package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select count from Student  S  where S.studentId=:id")
    public int noOfProblems(Long id);
    @Query("Select s from Student s where s.rollno=:user")
    public Student findUser(String user);
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.password = :password WHERE s.rollno = :user")
    void updatePassword(String user, String password);
    @Query("Select studentname from Student s where s.rollno=:roll")
    String findUserName(String roll);
    @Query("Select s from Student s where s.section=:section order by s.count DESC")
    List<Student> findBySection(@Param("section")String Section);
    @Query("select S from Student S where S.section=:section")
    List<Student> findBySectionWise(String section);

}

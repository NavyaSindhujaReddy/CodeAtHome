package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select count from Student  S  where S.studentId=:id")
    public int noOfProblems(Long id);
    @Query("select  S from Student  S where S.rollno=:user")
    public Student findUser(String user);
    @Modifying
    @Transactional
    @Query("update Student S set S.password = :password where S.rollno = :rollno")
    public void updatePassword(String rollno, String password);
}

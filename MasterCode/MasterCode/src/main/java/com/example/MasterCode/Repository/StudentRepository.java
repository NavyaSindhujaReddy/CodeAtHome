package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select count from Student  S  where S.studentId=:id")
    public int noOfProblems(Long id);
    @Query("Select password from Student s where s.rollno=:user")
    public String findUser(String user);
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.password = :password WHERE s.rollno = :user")
    void updatePassword(String user, String password);
}

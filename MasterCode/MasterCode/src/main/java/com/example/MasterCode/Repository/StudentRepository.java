package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select count from Student  S  where S.studentId=:id")
    public int noOfProblems(Long id);
}

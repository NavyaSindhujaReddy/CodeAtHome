package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByProblem(Problem problem);
    @Query("SELECT a FROM Answer a WHERE a.student.rollno = :roll")
    List<Answer> findProblemsById(String roll);
    @Query("Select A from Answer A where A.student=:student and A.problem=:problem")
    Answer findByStudentAndProblemId(Student student, Problem problem);
    @Query("select A from Answer A  where A.student=:student")
    List<Answer> findByStudent(Student student);
}

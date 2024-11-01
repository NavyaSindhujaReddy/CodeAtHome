package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByProblem(Problem problem);
    @Modifying
    @Transactional
    @Query("delete from Answer A where A.problem=:problem and A.student=:student")
    public void deleteAnswer(@Param("problem") Problem problem, @Param("student") Student student);
}
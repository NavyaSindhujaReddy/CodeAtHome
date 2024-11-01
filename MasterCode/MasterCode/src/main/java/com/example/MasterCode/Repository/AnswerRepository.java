package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByProblem(Problem problem);
}

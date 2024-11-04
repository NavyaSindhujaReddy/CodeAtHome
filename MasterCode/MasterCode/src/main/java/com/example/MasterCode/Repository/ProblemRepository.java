package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {
    @Query("select P from Problem P where P.problem_id=:problem_id")
    public Problem findByProblemId(Long problem_id);
    void deleteById(Long problemid);

}

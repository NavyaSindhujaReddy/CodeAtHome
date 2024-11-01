package com.example.MasterCode.Repository;

import com.example.MasterCode.Model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {

}

package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;
    @GetMapping("/")
    public ResponseEntity<List<Problem>> findallproblems(){
        return new ResponseEntity<List<Problem>>(problemService.findallproblems(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public String addProblem(@RequestBody Problem problem){
       return  problemService.saveProblem(problem);
    }
    @DeleteMapping("/deleteproblem/{id}")
    public String deleteProblem(@PathVariable Long id)
    {
        problemService.deleteProblem(id);
        return "Deleted";
    }
}

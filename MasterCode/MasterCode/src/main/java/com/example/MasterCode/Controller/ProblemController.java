package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Controller
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Problem>> findallproblems(){
        List<Problem> r=problemService.findallproblems();
        for(Problem p:r)
            System.out.println(p);
        return new ResponseEntity<List<Problem>>(problemService.findallproblems(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public String addProblem(@RequestBody Problem problem){
       return  problemService.saveProblem(problem);
    }
    @DeleteMapping("/delete/{problem_id}")
    public ResponseEntity<String> deleteProblem(@PathVariable String  problem_id){
        Long problemid=Long.parseLong(problem_id);
        String s=problemService.deleteById(problemid);
        if (s.equals("deleted")) {
            return ResponseEntity.ok("Problem deleted successfully."); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem not found."); // 404 Not Found
        }
    }

}

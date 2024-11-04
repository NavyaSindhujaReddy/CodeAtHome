package com.example.MasterCode.Controller;

import com.example.MasterCode.Model.Answer;
import com.example.MasterCode.Model.Problem;
import com.example.MasterCode.Model.Student;
import com.example.MasterCode.Service.AnswerService;
import com.example.MasterCode.Service.ProblemService;
import com.example.MasterCode.Service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    StudentService studentService;
    @Autowired
    ProblemService problemService;
    @Autowired
    AnswerService answerService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/changepassword")
    public ModelAndView showPassword(@RequestParam String user)
    {
        ModelAndView m=new ModelAndView();
        m.addObject("user",user);
        String name=studentService.findUserName(user);
        m.addObject("name",name);
        m.setViewName("changepassword");
        return m;
    }
    @GetMapping("/viewSubmissions")
    public ModelAndView showSubmissions(@RequestParam String user)
    {
        ModelAndView m=new ModelAndView();
        m.addObject("user",user);
        String name=studentService.findUserName(user);
        List<Answer> a=answerService.findProblemsById(user);
        List<String> ls=new ArrayList<>();
        for(Answer a1:a)
        {
            byte[] imageBytes = a1.getImageData();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            ls.add(base64Image);
        }
        m.addObject("l",a);
        m.addObject("ls",ls);
        m.addObject("name",name);
        m.setViewName("submissions");
        return m;
    }
    @GetMapping("/leaderboard")
    public ModelAndView showLeaderBoard(@RequestParam String user)
    {
        ModelAndView m=new ModelAndView("leaderboard");
        m.addObject("user",user);
        Student st=studentService.findUser(user);
        String name=studentService.findUserName(user);
        m.addObject("name",name);
        List<Student> s=studentService.findBySection(st.getSection());
        List<List<String>> l=new ArrayList<>();
        for(int i=0;i<s.size();i++)
        {
            List<String> s1=new ArrayList<>();
            s1.add(s.get(i).getRollno());
            s1.add(String.valueOf(s.get(i).getCount()));
            s1.add(String.valueOf(i+1));
            l.add(s1);
        }
        m.addObject("s",l);
        return m;
    }
    @PostMapping("/submit")
    public ModelAndView showSubmitPage(HttpServletRequest request)
    {
        String user=request.getParameter("user");
        Long pid=Long.parseLong(request.getParameter("problemId"));
        Optional<Problem> p=problemService.findById(pid);
        ModelAndView m=new ModelAndView();
        m.setViewName("problemsubmit");
        m.addObject("user",user);
        if (p.isPresent()) {
            Problem problem = p.get();
            m.addObject("p", problem);
        } else {
            m.addObject("p", null);
        }
        m.addObject("pid",pid);
        return m;
    }
    @GetMapping("/problems")
    public ModelAndView showProblems(@RequestParam String user)
    {
        ModelAndView m=new ModelAndView();
        m.addObject("user",user);
        String name=studentService.findUserName(user);
        m.addObject("name",name);
        m.setViewName("Dashboard");
        List<Problem> p=problemService.findallproblems();
        m.addObject("sp",p);
        return m;
    }
}

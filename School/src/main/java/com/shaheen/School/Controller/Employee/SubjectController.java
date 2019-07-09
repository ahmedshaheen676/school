/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Employee;

import com.shaheen.School.Model.Employee.Subject;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Employee.SubjectService;
import com.shaheen.School.Service.User.UserService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lts
 */
@Controller
@RequestMapping("/Subject")
public class SubjectController {

    @Autowired
    private UserService userService;
//    
//    @Autowired
//    private EmployeeService employeeService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = {"/", ""})
    public String getAllSubjects(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Subject> allSubjects = subjectService.findAll();
        model.addAttribute("allSubjects", allSubjects);
        return "subject/subjects";
    }

    @RequestMapping("addSubject")
    public String addSubject(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/addSubject";
    }

    @RequestMapping(value = "addSubject", method = RequestMethod.POST)
    public String addSubject(@ModelAttribute("subject") Subject subject, Model model, Principal principal) {
        if (subject.getSubjectTitle() != null && !subject.getSubjectTitle().equals("")) {
            subjectService.save(subject);
        }

        return "redirect:";
    }
    
    @RequestMapping(value = "/updateSubject", method = RequestMethod.GET)
    public String updateSubject(@RequestParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        if (id > 0 && id != null) {
            try {
                Subject subject = subjectService.findOne(id);
                if (subject.getSubjectTitle() != null) {
                    model.addAttribute("subject", subject);
                 
                    return "subject/updateSubject";
                } else {
                    return "common/badRequestPage";

                }
            } catch (Exception e) {
                e.printStackTrace();
                return "common/badRequestPage";
            }

        }
        return "redirect:";

    }

    @RequestMapping(value = "/updateSubject", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("subject") Subject subject, HttpServletRequest re) {
        subjectService.save(subject);
        return "redirect:";

    }
}

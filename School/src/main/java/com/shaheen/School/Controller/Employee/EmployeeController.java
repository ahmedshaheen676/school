/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Employee;

import com.shaheen.School.Model.Employee.Degree;
import com.shaheen.School.Model.Employee.Employee;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Employee.DegreeService;
import com.shaheen.School.Service.Employee.EmployeeService;
import com.shaheen.School.Service.Employee.JobService;
import com.shaheen.School.Service.Employee.QualificationService;
import com.shaheen.School.Service.Employee.SpicialService;
import com.shaheen.School.Service.Employee.SubjectService;
import com.shaheen.School.Service.User.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lts
 */
@Controller
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private QualificationService qualificationService;

    @Autowired
    private SpicialService spicialService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = {"/", ""})
    public String getAllEmployees(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee/employees";
    }

    @RequestMapping("addEmployee")
    public String addEmployee(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("allJobs", jobService.findAll());
        model.addAttribute("allDegrees", degreeService.findAll());
        model.addAttribute("allQualifications", qualificationService.findAll());
        model.addAttribute("allSpicials", spicialService.findAll());
        model.addAttribute("allSubjects", subjectService.findAll());

        return "employee/addEmployee";
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public String addEmployee(
            @ModelAttribute("employee") Employee employee,
//            @ModelAttribute("job") Job job,
            @ModelAttribute("degree1") Degree degree1,
            @ModelAttribute("degree2") Degree degree2
//            @ModelAttribute("qualification") Qualification qualification,
//            @ModelAttribute("spicial") Spicial spicial,
//            @ModelAttribute("subjects") ArrayList<Subject> subjects
    ) {

        if (employee.getEmployeeName().trim() != null && !employee.getEmployeeName().trim().equals("")) {
//            job.getEmployees().add(employee);
//            
            degree1.getEmployees().add(employee);
            degree2.getEmployees().add(employee);
//            
            employee.setCurrentDegreeId(degree1.getId());
//            
//            qualification.getEmployees().add(employee);
//            
//            spicial.getEmployees().add(employee);
//            
//            spicial.getEmployees().add(employee);
//            
//            for (Subject subject : subjects) {
//                subject.getEmployees().add(employee);
//            }
            employeeService.save(employee);
        }
//        System.out.println("عدد المواد" + subjects.size());
        return "redirect:";

    }

}

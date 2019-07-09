/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Employee;

import com.shaheen.School.Model.Employee.Job;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Employee.JobService;
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
@RequestMapping("/Job")
public class JobController {

    @Autowired
    private UserService userService;
//    
//    @Autowired
//    private EmployeeService employeeService;

    @Autowired
    private JobService jobService;

    @RequestMapping(value = {"/", ""})
    public String getAllJobs(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Job> allJobs = jobService.findAll();
        model.addAttribute("allJobs", allJobs);
        return "employee/job/jobs";
    }

    @RequestMapping("addJob")
    public String addJob(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        Job job = new Job();
        model.addAttribute("job", job);
        return "employee/job/addJob";
    }

    @RequestMapping(value = "addJob", method = RequestMethod.POST)
    public String addJob(@ModelAttribute("job") Job job, Model model, Principal principal) {
        if (job.getJobTitle() != null && !job.getJobTitle().equals("")) {
            jobService.save(job);
        }

        return "redirect:";
    }
    
    @RequestMapping(value = "/updateJob", method = RequestMethod.GET)
    public String updateJob(@RequestParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        if (id > 0 && id != null) {
            try {
                Job job = jobService.findOne(id);
                if (job.getJobTitle() != null) {
                    model.addAttribute("job", job);
                 
                    return "employee/job/updateJob";
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

    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("job") Job job, HttpServletRequest re) {
        jobService.save(job);
        return "redirect:";

    }
}

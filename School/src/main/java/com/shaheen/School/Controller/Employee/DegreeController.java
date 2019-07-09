/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Employee;

import com.shaheen.School.Model.Employee.Degree;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Employee.DegreeService;
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
@RequestMapping("/Degree")
public class DegreeController {

    @Autowired
    private UserService userService;
//    
//    @Autowired
//    private EmployeeService employeeService;

    @Autowired
    private DegreeService degreeService;

    @RequestMapping(value = {"/", ""})
    public String getAllDegrees(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Degree> allDegrees = degreeService.findAll();
        model.addAttribute("allDegrees", allDegrees);
        return "employee/degree/degrees";
    }

    @RequestMapping("addDegree")
    public String addDegree(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        Degree degree = new Degree();
        model.addAttribute("degree", degree);
        return "employee/degree/addDegree";
    }

    @RequestMapping(value = "addDegree", method = RequestMethod.POST)
    public String addDegree(@ModelAttribute("degree") Degree degree, Model model, Principal principal) {
        if (degree.getDegreeName()!= null && !degree.getDegreeName().equals("")) {
            degreeService.save(degree);
        }

        return "redirect:";
    }

    @RequestMapping(value = "/updateDegree", method = RequestMethod.GET)
    public String updateDegree(@RequestParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        if (id > 0 && id != null) {
            try {
                Degree degree = degreeService.findOne(id);
                if (degree.getDegreeName()!= null) {
                    model.addAttribute("degree", degree);

                    return "employee/degree/updateDegree";
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

    @RequestMapping(value = "/updateDegree", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("degree") Degree degree, HttpServletRequest re) {
        degreeService.save(degree);
        return "redirect:";

    }
}

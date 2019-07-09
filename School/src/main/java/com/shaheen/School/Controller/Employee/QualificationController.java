/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Employee;

import com.shaheen.School.Model.Employee.Qualification;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Employee.QualificationService;
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
@RequestMapping("/Qualification")
public class QualificationController {

    @Autowired
    private UserService userService;

    @Autowired
    private QualificationService qualificationService;

    @RequestMapping(value = {"/", ""})
    public String getAllQualifications(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        
        List<Qualification> allQualifications = qualificationService.findAll();
        model.addAttribute("allQualifications", allQualifications);
        return "employee/qualification/qualifications";
    }

    @RequestMapping("addQualification")
    public String addQualifcation(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        Qualification qualifcation = new Qualification();
        model.addAttribute("qualification", qualifcation);
        return "employee/qualification/addQualification";
    }

    @RequestMapping(value = "addQualification", method = RequestMethod.POST)
    public String addQualifcation(@ModelAttribute("qualification") Qualification qualification, Model model, Principal principal) {
        if (qualification.getQualificationTitle() != null && !qualification.getQualificationTitle().equals("")) {
            qualificationService.save(qualification);
        }

        return "redirect:";
    }
    
    @RequestMapping(value = "/updateQualification", method = RequestMethod.GET)
    public String updateQualifcation(@RequestParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        if (id > 0 && id != null) {
            try {
                Qualification qualification = qualificationService.findOne(id);
                if (qualification.getQualificationTitle() != null) {
                    model.addAttribute("qualification", qualification);
                 
                    return "employee/qualification/updateQualification";
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

    @RequestMapping(value = "/updateQualification", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("qualification") Qualification qualifcation, HttpServletRequest re) {
        qualificationService.save(qualifcation);
        return "redirect:";

    }
}

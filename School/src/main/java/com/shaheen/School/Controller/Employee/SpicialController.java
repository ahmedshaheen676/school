/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Employee;

import com.shaheen.School.Model.Employee.Spicial;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Employee.SpicialService;
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
@RequestMapping("/Spicial")
public class SpicialController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpicialService spicialService;

    @RequestMapping(value = {"/", ""})
    public String getAllSpicials(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        List<Spicial> allSpicials = spicialService.findAll();
        model.addAttribute("allSpicials", allSpicials);
        return "employee/spicial/spicials";
    }

    @RequestMapping("addSpicial")
    public String addQualifcation(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        Spicial qualifcation = new Spicial();
        model.addAttribute("spicial", qualifcation);
        return "employee/spicial/addSpicial";
    }

    @RequestMapping(value = "addSpicial", method = RequestMethod.POST)
    public String addQualifcation(@ModelAttribute("spicial") Spicial spicial, Model model, Principal principal) {
        if (spicial.getSpicialTitle() != null && !spicial.getSpicialTitle().equals("")) {
            spicialService.save(spicial);
        }

        return "redirect:";
    }

    @RequestMapping(value = "/updateSpicial", method = RequestMethod.GET)
    public String updateQualifcation(@RequestParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        if (id > 0 && id != null) {
            try {
                Spicial spicial = spicialService.findOne(id);
                if (spicial.getSpicialTitle() != null) {
                    model.addAttribute("spicial", spicial);

                    return "employee/spicial/updateSpicial";
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

    @RequestMapping(value = "/updateSpicial", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("spicial") Spicial qualifcation, HttpServletRequest re) {
        spicialService.save(qualifcation);
        return "redirect:";

    }
}

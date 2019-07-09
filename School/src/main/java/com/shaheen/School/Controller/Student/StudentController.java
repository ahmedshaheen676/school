/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Student;

import com.shaheen.School.Controller.common.MyErrorController;
import com.shaheen.School.DAO.Student.StudentDAO;
import com.shaheen.School.Model.Student.Classes;
import com.shaheen.School.Model.Student.StudentState;
import com.shaheen.School.Model.Student.Student;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Student.ClassesService;
import com.shaheen.School.Service.Student.StateService;
import com.shaheen.School.Service.Student.StudentService;
import com.shaheen.School.Service.User.UserService;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/Student")
public class StudentController extends MyErrorController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StateService stateService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = {"/", ""})
    public String getAllStudents(Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateGraduated(false);

        model.addAttribute("allStudents", allStudents);

        return "student/students";
    }
//

    //---------------------------------------------
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        Student student = new Student();
        StudentState studentState = new StudentState();

        model.addAttribute("student", student);
        model.addAttribute("studentState", studentState);

        List<Classes> allClasses = classesService.findAll();
        model.addAttribute("allClasses", allClasses);

        return "student/addStudent";

    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student,
            @ModelAttribute("studentState") StudentState studentState,
            Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        // boolen to check is their eny error on values user enterd ;
        boolean error = false;

        student.setStudentState(studentState);

        studentState.setStudent(student);

//        stateService.save(studentState);

        Map<String, Boolean> check = studentDAO.save(student);
        for (Map.Entry<String, Boolean> entry : check.entrySet()) {
            // if their any error it 
            if (entry.getValue()) {
                System.out.println(entry.getKey());
                model.addAttribute(entry.getKey(), entry.getValue());
                error = true;
            }
//            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        if (error) {
            model.addAttribute("student", student);
            model.addAttribute("studentState", studentState);
            List<Classes> allClasses = classesService.findAll();
            model.addAttribute("allClasses", allClasses);

            return "student/addStudent";
        }
        return "redirect:";

    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.GET)
    public String updateStudent(@RequestParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Classes> allClasses = classesService.findAll();
        model.addAttribute("allClasses", allClasses);

        if (id > 0 && id != null) {
            try {
                Student student = studentService.findOne(id);
                if (student.getName() != null) {
                    model.addAttribute("student", student);

//                    model.addAttribute("studentState", student.getStudentState());
//                    System.out.println(student.getStudentState().toString());
                    return "student/updateStudent";
                } else {
                    return "redirect:/NotFoundPage";

                }
            } catch (Exception e) {
//                e.printStackTrace();
                return "redirect:/NotFoundPage";

            }

        }
        return "redirect:";

    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("student") Student student,
            @ModelAttribute("studentState") StudentState studentState,
            HttpServletRequest re) {

        student.setStudentState(studentState);
        studentService.save(student);
        return "redirect:";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteStudent(@ModelAttribute("student") Student student,Model model, Principal principal ,
            HttpServletRequest re) {

        if (studentService.deleteStudent(student)) {
            return "redirect:";

        }

        return updateStudent(student.getId(),  model,  principal);

    }

    //---------------------------------------------
    @RequestMapping(value = "/addAbsensce", method = RequestMethod.GET)
    public String addAbsensce(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

//        Student student = new Student();
//        StudentState studentState = new StudentState();
//
//        model.addAttribute("student", student);
//        model.addAttribute("studentState", studentState);
//
//        List<Classes> allClasses = classesService.findAll();
//        model.addAttribute("allClasses", allClasses);
        return "student/Absensce";

    }

    /// التقارير 
    //**************************
    //**المحولون من مدارس اخري**
    //**************************
    @RequestMapping(value = "/comeFrom")
    public String getComeFromStudents(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateComeFrom(false, true);
        model.addAttribute("ReportTitle", "بيانات الطلاب المحولين من مدارس اخري");
        model.addAttribute("fromOrTO", "محول من");
        model.addAttribute("cameFromSchool", true);
        model.addAttribute("sendedToSchool", false);
        model.addAttribute("allStudents", allStudents);
        return "student/Reports/OtherSchool";
    }

    //**************************
    //**المحولون الي مدارس اخري**
    //**************************
    @RequestMapping(value = "/sendTo")
    public String getSendToStudents(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateSendTo(false, true);

        model.addAttribute("ReportTitle", "بيانات الطلاب المحولين الي مدارس اخري");
        model.addAttribute("fromOrTO", "محول الي");

        model.addAttribute("cameFromSchool", false);
        model.addAttribute("sendedToSchool", true);

        model.addAttribute("allStudents", allStudents);
        return "student/Reports/OtherSchool";
    }

    //**************************
    //**المتـــــــــــــسربون**
    //**************************
    @RequestMapping(value = "/getOut")
    public String getGetOutStudents(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateGetOut(false, true);

//        model.addAttribute("OtherSchoolTitle", "بيانات الطلاب المحولين الي مدارس اخري");
//        model.addAttribute("fromOrTO", "محول الي");
//        
//        model.addAttribute("cameFromSchool", false);
//        model.addAttribute("sendedToSchool", true);
        model.addAttribute("allStudents", allStudents);
        return "student/Reports/getOut";
    }

    //**************************
    //**ولـــــــــي الامر مطلق**
    //**************************
    @RequestMapping(value = "/divorsed")
    public String getDivorsedStudent(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateDivorsed(false, true);

//        model.addAttribute("OtherSchoolTitle", "بيانات الطلاب المحولين الي مدارس اخري");
//        model.addAttribute("fromOrTO", "محول الي");
//        
//        model.addAttribute("cameFromSchool", false);
//        model.addAttribute("sendedToSchool", true);
        model.addAttribute("allStudents", allStudents);
        return "student/Reports/divorsed";
    }

    //**************************
    //**الضـــــــمان الاجتماعي**
    //**************************
    @RequestMapping(value = "/socialSecure")
    public String getSocialSecureStudent(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateSocialSecure(false, true);

        model.addAttribute("ReportTitle", "طلاب الضمان الاجتماعي");
//        model.addAttribute("fromOrTO", "محول الي");
//        
//        model.addAttribute("cameFromSchool", false);
//        model.addAttribute("sendedToSchool", true);

        model.addAttribute("allStudents", allStudents);
        return "student/Reports/SSorPDorST";
    }

    //**************************
    //**الايــــــــــــــــتام**
    //**************************
    @RequestMapping(value = "/parentDied")
    public String getParentDiedStudent(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateParentDied(false, true);

        model.addAttribute("ReportTitle", "الطلاب الايتام");
//        model.addAttribute("fromOrTO", "محول الي");
//        
//        model.addAttribute("cameFromSchool", false);
//        model.addAttribute("sendedToSchool", true);

        model.addAttribute("allStudents", allStudents);
        return "student/Reports/SSorPDorST";
    }

    //**************************
    //**الـــموقــــــوف قيدهم**
    //**************************
    @RequestMapping(value = "/stoped")
    public String getStopedStudent(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateStoped(false, true);

        model.addAttribute("ReportTitle", "الطلاب الموقوف قيدهم");
//        model.addAttribute("fromOrTO", "محول الي");
//        
//        model.addAttribute("cameFromSchool", false);
//        model.addAttribute("sendedToSchool", true);

        model.addAttribute("allStudents", allStudents);
        return "student/Reports/SSorPDorST";
    }

    //**************************
    //**الخريحين**
    //**************************
    @RequestMapping(value = "/graduated")
    public String getGraduatedStudent(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Student> allStudents = studentService.StudentStateGraduated(true);

        model.addAttribute("ReportTitle", "الخريجين");
//        model.addAttribute("fromOrTO", "محول الي");
//        
//        model.addAttribute("cameFromSchool", false);
//        model.addAttribute("sendedToSchool", true);

        model.addAttribute("allStudents", allStudents);
        return "student/Reports/SSorPDorST";
    }

    @RequestMapping(value = "/reportAllStudent/{requestParam}", method = RequestMethod.GET)
    public void reportAllStudents(
            @PathParam(value = "requestParam") String requestParam,
            HttpServletResponse response
    ) {
        try {
            response.setContentType("application/x-pdf");
            response.addHeader("Content-Disposition", "attachment;inline=schoolBudget.pdf");

//            JRBeanCollectionDataSource dataSource
//                    = new JRBeanCollectionDataSource(classesService.SchoolBudject());
            InputStream inputStream
                    = this.getClass().
                            getResourceAsStream("/static/Reports/AllStudents.jrxml");

//            String path = 
//                    resourceLoader.getResource("classpath:/static/Reports/SchoolBudget.jrxml").getURI().getPath();
//            System.out.println("path :" + path);
//            logger.info(inputStream.toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

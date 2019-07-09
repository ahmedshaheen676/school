/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Controller.Student;
import com.shaheen.School.Controller.common.MyErrorController;
import java.sql.Connection;
import com.shaheen.School.Model.Student.Classes;
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.Student.ClassesService;
import com.shaheen.School.Service.User.UserService;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
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
@RequestMapping("/Class")
public class ClassController extends MyErrorController{

    private static final Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private ResourceLoader resourceLoader;

    @RequestMapping(value = {"/", ""})
    public String getAllclasses(Model model, Principal principal, HttpServletRequest re) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user.getId() > 0 && user.getId() != null) {
                model.addAttribute("user", user);
            }
        }

        List<Classes> allClasses = classesService.findAll();
//        List<Classes> allClasses = classesService.findByStudentsStudentStateGraduated(false);
        model.addAttribute("allClasses", allClasses);
        ArrayList<Map<String, Integer>> arrayList = new ArrayList<>();
        for (Classes calsses : allClasses) {

            arrayList.add(
                    classesService.countGenderMaleAndFemaleAndTotal(
                            calsses.getStudents()
                    )
            );
        }
        int total = 0;
        int male = 0;
        int fmale = 0;
        for (Map<String, Integer> map : arrayList) {
            total += map.get("Total");
            male += map.get("countMale");
            fmale += map.get("countFmale");
        }

//        model.addAttribute("allClasses", allClasses);
        model.addAttribute("classesService", classesService);
        model.addAttribute("Total", total);
        model.addAttribute("countMale", male);
        model.addAttribute("countFmale", fmale);
//        report();
//        getCompiledFile("SchoolBudget", re);

        return "Classes/Classes";
    }

//    @RequestMapping(value = "/addClass", method = RequestMethod.GET)
//    public String addClass(Model model, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//
//        Classes classes = new Classes();
//        model.addAttribute("classes", classes);
//
//        return "Classes/addClass";
//
//    }
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public String addClass(@ModelAttribute("classes") Classes classes, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        Classes findByName = classesService.findByName(classes.getName());
        if (findByName == null) {
            Classes classToSave = new Classes();
            classToSave.setName(classes.getName());
            classesService.save(classToSave);
        } else {
            model.addAttribute("classesExist", true);
        }
        return "redirect:";

    }

//    @RequestMapping(value = "/updateClass", method = RequestMethod.GET)
//    public String updateClass(@RequestParam("id") Long id, Model model, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        if (id > 0 && id != null) {
//            try {
//                Classes classes = classesService.findById(id);
//                if (classes != null) {
//                    model.addAttribute("classes", classes);
//                    return "Classes/updateClass";
//                } else {
//                    return "redirect:/NotFoundPage";
//                }
//            } catch (Exception e) {
//                return "redirect:/BadRequestPage";
//
//            }
//
//        } else {
//            return "redirect:/BadRequestPage";
//        }
//
//    }
    @RequestMapping(value = "/updateClass", method = RequestMethod.POST)
    public String updateClass(@ModelAttribute("classes") Classes classes,
            HttpServletRequest re,
            Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        try {
            Classes findByName = classesService.findByName(classes.getName());
            if (findByName != null
                    && findByName.getId() != classes.getId()) {
                model.addAttribute("classesExist", true);
                return "Classes/classesStudents";

            } else {
                classesService.save(classes);
                return "redirect:";
            }

        } catch (Exception e) {
            return "redirect:/BadRequestPage";

        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteClass(@ModelAttribute("classes") Classes classes,
            HttpServletRequest re,
            Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        try {

            if (classes != null) {
                Classes findById = classesService.findById(classes.getId());
                int size = findById.getStudents().size();
                System.out.println("student size = " + size);
                boolean deleted = false;
                if (size <= 0) {
                    deleted = classesService.delete(classes);
                } else {
                    model.addAttribute("classes", findById);
                    model.addAttribute("classesCantBeDeleted", true);
                    return "Classes/classesStudents";
                }

                if (deleted) {
                    return "redirect:";
                } else {
                    return "redirect:/BadRequestPage";

                }

            } else {
                model.addAttribute("classesNotExist", true);
                return "Classes/classesStudents";

            }

        } catch (Exception e) {
            return "redirect:/BadRequestPage";

        }

    }

    @RequestMapping(value = "/getAllStudent", method = RequestMethod.GET)
    public String getAllStudent(@PathParam("id") Long id, Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        if (id > 0 && id != null) {
            try {
                Classes classes = classesService.findById(id);
                if (classes != null) {
                    System.out.println(classes.toString());
                    model.addAttribute("classes", classes);
                    return "Classes/classesStudents";
                } else {
                    return "redirect:/NotFoundPage";
                }
            } catch (Exception e) {
                return "redirect:/BadRequestPage";
            }
        } else {
            return "redirect:/BadRequestPage";
        }
    }

    @RequestMapping(value = "/reportSchoolBudget/{requestParam}", method = RequestMethod.GET)
    public void reportSchoolBudget(
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
                            getResourceAsStream("/static/Reports/SchoolBudget.jrxml");

//            String path = 
//                    resourceLoader.getResource("classpath:/static/Reports/SchoolBudget.jrxml").getURI().getPath();
//            System.out.println("path :" + path);
            logger.info(inputStream.toString());
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/reportClass/{requestParam}", method = RequestMethod.GET)
    public void report(
            @PathParam(value = "requestParam") String requestParam,
            @RequestParam("id") Long id,
            HttpServletResponse response
    ) {
        try {

            response.setContentType("application/x-pdf");
            response.addHeader("Content-Disposition", "attachment;inline=class.pdf");

            InputStream inputStream
                    = this.getClass().
                            getResourceAsStream("/static/Reports/ClassStudent.jrxml");
//             URL url = getClass().getResource("/static/Reports/");
            InputStream subInputStream1 = this.getClass().getResourceAsStream("/static/Reports/ClassFmaleStudent.jasper");
            InputStream subInputStream2 = this.getClass().getResourceAsStream("/static/Reports/ClassMaleStudent.jasper");
//            InputStream subInputStream2 = this.getClass().getResourceAsStream("/static/Reports/ClassFmaleStudent.jasper");
//            parameterList.put("SUBREPORT_INPUT_STREAM", subInputStream);

//            prameters.put("ClassesID", id);
//            prameters.put("SUBREPORT_DIR", url);
//            String path = resourceLoader.getResource("classpath:/static/Reports/ClassStudent.jrxml").getURI().getPath();
//            System.out.println("path :" + path);
            logger.info(inputStream.toString());

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            Map<String, Object> prameters = new HashMap<>();
            prameters.put("ClassesID", id);
            prameters.put("SUBREPORT_INPUT_STREAM1", subInputStream1);
            prameters.put("SUBREPORT_INPUT_STREAM2", subInputStream2);
//            System.out.println("SUBREPORT_DIR = " + url);
            System.out.println("classesID = " + id);
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, prameters, conn);

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package glue.pudding.web;

import glue.pudding.entity.*;
import glue.pudding.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by GUIXu on 2020/6/20.
 */
@RestController
@RequestMapping(value = "/employee/basic")
public class EmployeeController {

    EmployeeService employeeService;

    JobLevelService jobLevelService;

    NationService nationService;

    PositionService positionService;

    PoliticsStatusService politicsStatusService;

    DepartmentService departmentService;

    JavaMailSender mailSender;

    ExecutorService executorService;

    TemplateEngine engine;

    public EmployeeController(EmployeeService employeeService,
                              JobLevelService jobLevelService,
                              NationService nationService,
                              PositionService positionService,
                              PoliticsStatusService politicsStatusService,
                              DepartmentService departmentService,
                              JavaMailSender mailSender,
                              TemplateEngine engine) {
        this.employeeService = employeeService;
        this.jobLevelService = jobLevelService;
        this.nationService = nationService;
        this.positionService = positionService;
        this.politicsStatusService = politicsStatusService;
        this.departmentService = departmentService;
        this.mailSender = mailSender;

        this.executorService = Executors.newFixedThreadPool(10);
        this.engine = engine;
    }

    @GetMapping
    public ResponsePageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
    }

    @PostMapping
    public ResponseBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmployee(employee) == 1) {
            employee.setDepartment(departmentService.getDepartmentById(employee.getDepartmentId()));
            employee.setPosition(positionService.getPositionById(employee.getPosId()));
            executorService.execute(new EMailRunnable(employee, mailSender, engine));
            return ResponseBean.ok("添加成功！");
        }
        return ResponseBean.error("添加失败");
    }

    @GetMapping("/jobLevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.getAllPoliticsStatusService();
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

}

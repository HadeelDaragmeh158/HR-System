package com.hadeel.HR_System.Controllers;

import com.hadeel.HR_System.Models.Department;
import com.hadeel.HR_System.Models.Directorate;
import com.hadeel.HR_System.Models.Employee;
import com.hadeel.HR_System.Models.Leave;
import com.hadeel.HR_System.Reposetoreis.DepartmentRepository;
import com.hadeel.HR_System.Reposetoreis.DirectorateRepository;
import com.hadeel.HR_System.Reposetoreis.EmployeeRepository;
import com.hadeel.HR_System.Reposetoreis.LeaveRepository;
import com.hadeel.HR_System.services.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@Controller
public class HrSystemController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DirectorateRepository directorateRepository;

    @Autowired
    DepartmentRepository departmentRepository;
    DepartmentServiceImpl dept  = new DepartmentServiceImpl();

    @Autowired
    LeaveRepository leaveRepository;

    @GetMapping("/")
    public String  getHome(){
        return "index.html";
    }

//------------------------------------------------------------------------------------------------------------
    //Directorate
    @PostMapping("adddirectorate")
    public RedirectView addDirectorate(Directorate directorate){
       directorateRepository.save(directorate);
       return new RedirectView("alldirectorate");
    }

    @GetMapping("alldirectorate") // GET ALL DIRICTORATE
    public String getAllDirectorate(Model model){
        model.addAttribute("directorateList",directorateRepository.findAll());
        return "index.html";
    }

    @GetMapping("/edit/{directorateId}") //get the directorate page to edit it
    public String showEditDirectoratePage(@PathVariable(name = "directorateId") Long directorateId,Model m) {
        Directorate directorate= directorateRepository.findById(directorateId).orElseThrow();
        m.addAttribute("departmenr", directorate );
        return "edit_directorate";
    }

    @PostMapping("/edit_directorate/{directorateId}") // ADD EDITED INFORMATION
    public RedirectView updateDirectorate (@ModelAttribute Directorate directorate ,@PathVariable Long directorateId){
        Directorate currentUser = directorateRepository.findById(directorateId).orElseThrow();
        currentUser.setDirectorateName(directorate.getDirectorateName());

        directorateRepository.save(currentUser);
        return new RedirectView("/");
    }

    @PostMapping("/deletedirectorate/{id}") // DELETE THE DIRECTORATE
    public RedirectView deleteDirectorate(@PathVariable(name = "id") Long id) {
        Directorate directorate = directorateRepository.findById(id).orElseThrow();
        directorateRepository.delete(directorate);
        return new RedirectView("index.html");
    }

//------------------------------------------------------------------------------------------------------------
    //Department
    @PostMapping("/adddepartment")
    public RedirectView createNewDepartment(Department department){
        departmentRepository.save(department);
        return  new RedirectView("alldepartments");
    }
    @GetMapping("/alldepartments")
    public String allDepartment(Model model ){
        model.addAttribute("departmentList", departmentRepository.findAll());
        return "index.html";
    }

    @GetMapping("/edit/{departmentid}")
    public String showEditDepartmentPage(@PathVariable(name = "departmentid") Long departmentid,Model m) {
        Department department = departmentRepository.findById(departmentid).orElseThrow();
        m.addAttribute("department", department);
        return "edit_department";
    }

    @PostMapping("/edit_department/{id}")
    public RedirectView updateDepartment (@ModelAttribute Department department ,@PathVariable Long id){
        Department currentUser = departmentRepository.findById(id).orElseThrow();
        currentUser.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(currentUser);
        return new RedirectView("/");
    }

    //      DELETE THE Department
    @PostMapping("/deletedepartment/{id}")
    public RedirectView deleteDepartment(@PathVariable(name = "id") Long id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);
        return new RedirectView("index.html");
    }

//------------------------------------------------------------------------------------------------------------
    //Employee
    @PostMapping("/addemployee")
    public RedirectView addEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return new RedirectView("allemployees");
    }

    @GetMapping("/allemployees")
    public String getAllEmployees(Model model){
        model.addAttribute("employeeList",employeeRepository.findAll());
        return "index.html";
    }

    @GetMapping("/allemployees/{employeeId}")
    ResponseEntity<?> geEmployees(@PathVariable Long employeeId  ){
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow();
        return new ResponseEntity<>(employee.getFirstName(), HttpStatus.OK );
    }

    @GetMapping("/employee/{employeeId}")//TODO
   public ResponseEntity<?> getEmployee (@PathVariable Long employeeId, Model model) {
        List<Employee> employees = employeeRepository.findAllByDepartmentId(employeeId);
        model.addAttribute("EmployeesInDepartment", employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
//        return "index.html";
    }

    @GetMapping("/edit/{id}")
    public String showEditEmployeePage(@PathVariable(name = "id") Long id,Model m) {
        Employee myUser = employeeRepository.getById(id);
        m.addAttribute("myUser", myUser);
        return "index.html";
    }

    @PostMapping("/edit_employee/{employeeId}")
    public RedirectView updateEmployee (@ModelAttribute Employee employee ,@PathVariable Long employeeId){
    Employee currentUser = employeeRepository.findById(employeeId).orElseThrow();
    currentUser.setFirstName(employee.getFirstName());
    currentUser.setLastName(employee.getLastName());
    currentUser.setLeaves(employee.getLeaves());
    employeeRepository.save(currentUser);
    return new RedirectView("/");
    }


    //      DELETE THE EMPLOYEE
//    @GetMapping("/deleteemployee/{id}")
//    public  String getDelete(@PathVariable Long id ){
//        Employee employee = employeeRepository.findById(id).orElseThrow();
//        return "/deleteemployee/"+(employee.getEmployeeId()).toString();
//    }

    @PostMapping("/deleteemployee/{id}")
    public  ResponseEntity<?>   deleteProduct(@PathVariable(name = "id") Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return new  ResponseEntity<>(employee,HttpStatus.OK);
    }

    //GET DY DATE
    @GetMapping("/employeesbydate/{start}/{end}")
    public String getEmployeesByCreationDate(@PathVariable  Date from , @PathVariable Date to , Model model){
        model.addAttribute(employeeRepository.findByExpiryDateBetween(from,to));
        return "index.html";
    }

//------------------------------------------------------------------------------------------------------------
    //APPLY FOR LEAVE
    @PostMapping("/createleave/{employeeId}")
    public RedirectView addLeave(@ModelAttribute Leave leave,@PathVariable Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        leave.setEmployee(employee);
        leave.setManager(employee.getDepartment().getManager());
        leaveRepository.save(leave);
        return new RedirectView("approveleave"); //
    }

    @PostMapping("/approveleave/{leaveId}")
    public RedirectView approveLeaveByManager (@PathVariable Long leaveId){
        Leave leave = leaveRepository.findById(leaveId).orElseThrow();
        leave.setAccepted(true);
        leaveRepository.save(leave);
        return new RedirectView("leave/{leaveId}");
    }

    @GetMapping("/leave/{leaveId}")
    public ResponseEntity<?> getApproveLeave(@PathVariable Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow();
        return new ResponseEntity<>(leave, HttpStatus.OK);
    }

    @GetMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }


}

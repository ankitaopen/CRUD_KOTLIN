package com.example.demo.controller

import com.example.demo.exception.ResourceNotFoundException
import com.example.demo.model.Employee
import com.example.demo.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class EmployeeController {
    @Autowired
    private val employeeRepository: EmployeeRepository? = null

    //creating the following REST APIs
    //get employees
    //get all employee REST api
    @get:GetMapping("employees")
    val allEmployee: List<Employee?>
        get() = employeeRepository!!.findAll() as List<Employee?>

    //get employees by id
    @GetMapping("/employees/{id}")
    @Throws(ResourceNotFoundException::class)
    fun getEmployeeById(@PathVariable(value = "id") employeeId: Long): ResponseEntity<Employee> {
        val employee = employeeRepository!!.findById(employeeId)
                .orElseThrow { ResourceNotFoundException("Employee not found for this id :: $employeeId") }!!
        return ResponseEntity.ok().body(employee)
    }

    //save employee
    @PostMapping("employees")
    fun createEmployee(@RequestBody employee: Employee): Employee {
        return employeeRepository!!.save(employee)
    }

    //update employee
    @PutMapping("employees/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateEmployee(@PathVariable(value = "id") employeeId: Long,
                       @Valid @RequestBody employeeDetails: Employee): ResponseEntity<Employee> {
        val employee = employeeRepository!!.findById(employeeId)
                .orElseThrow { ResourceNotFoundException("Employee not found for this id :: $employeeId") }!!
        employee.emailId = employeeDetails.emailId
        employee.lastName = employeeDetails.lastName
        employee.firstName = employeeDetails.firstName
        val updatedEmployee = employeeRepository.save(employee)
        return ResponseEntity.ok(updatedEmployee)
    }

    //delete employee
    @DeleteMapping("employees/{id}")
    @Throws(ResourceNotFoundException::class)
    fun deleteEmployee(@PathVariable(value = "id") employeeId: Long): Map<String, Boolean> {
        val employee = employeeRepository!!.findById(employeeId)
                .orElseThrow { ResourceNotFoundException("Employee not found for this id :: $employeeId") }!!
        employeeRepository.delete(employee)
        val response: MutableMap<String, Boolean> = HashMap()
        response["deleted"] = java.lang.Boolean.TRUE
        return response
    }
}
package com.polygalov.springrest.sr.controller

import com.polygalov.springrest.sr.model.Employee
import com.polygalov.springrest.sr.model.NewEmployeeForm
import com.polygalov.springrest.sr.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class EmployeeController (@Autowired private val employeeRepository: EmployeeRepository ) {

    //gets all employees
    @GetMapping("/employees")
    fun getAllEmployees() : List<Employee> = employeeRepository.findAll()

    //creates a employee
    @PostMapping("/employees")
    fun createEmployee(@Valid @RequestBody employee : Employee) : List<Employee>{
        employeeRepository.save(employee)
        return getAllEmployees()
    }

    //gets new employee form
    @GetMapping("/employees/getForm")
    fun getNewEmployeeForm() : NewEmployeeForm {
        return NewEmployeeForm("Введите ФИО", "", "Введите номер телефона","")
    }

    //gets employee bu name
    @GetMapping("employees/{name}")
    fun getEmployeeByName(@PathVariable name : Long) : ResponseEntity<Employee> =
            employeeRepository.findById(name).map {
                ResponseEntity.ok(it)
            }.orElse(ResponseEntity.notFound().build())

    //updates employee
    @PutMapping("/employees/{name}")
    fun updateEmployee(@PathVariable name : Long, @Valid @RequestBody updatedEmployee: Employee)
            : ResponseEntity<Employee> =
            employeeRepository.findById(name).map{
                val newEmployee = it.copy(name = updatedEmployee.name, phone =  updatedEmployee.phone)
                ResponseEntity.ok().body(employeeRepository.save(newEmployee))
            }.orElse(ResponseEntity.notFound().build())

    // deletes employee
    @DeleteMapping("/employees/{employeeId}")
    fun deleteEmployee(@PathVariable employeeId : Long) : ResponseEntity<Void> =
            employeeRepository.findById(employeeId).map{
                employeeRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
}
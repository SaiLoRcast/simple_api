package com.polygalov.springrest.sr.repository

import com.polygalov.springrest.sr.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
}
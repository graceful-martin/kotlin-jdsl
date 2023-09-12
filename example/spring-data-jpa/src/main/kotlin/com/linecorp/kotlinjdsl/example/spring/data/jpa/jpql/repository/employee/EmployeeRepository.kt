package com.linecorp.kotlinjdsl.example.spring.data.jpa.jpql.repository.employee

import com.linecorp.kotlinjdsl.example.spring.data.jpa.jpql.entity.employee.Employee
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>, KotlinJdslJpqlExecutor

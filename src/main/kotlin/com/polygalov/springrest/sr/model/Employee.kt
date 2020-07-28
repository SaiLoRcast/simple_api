package com.polygalov.springrest.sr.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity(name = "employees")
data class Employee(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator="employees_id_seq")
        val id : Long ,
        @get: NotBlank val name : String = "",
        @get: NotBlank val phone : String = ""
)
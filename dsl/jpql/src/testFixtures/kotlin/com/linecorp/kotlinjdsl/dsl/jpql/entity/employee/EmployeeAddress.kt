package com.linecorp.kotlinjdsl.dsl.jpql.entity.employee

data class EmployeeAddress(
    val zipCode: String,
    val streetAddress1: String,
    val streetAddress2: String?,
    val city: String,
    val province: String,
)

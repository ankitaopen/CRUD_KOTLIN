package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "employees")
class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@Column(nullable = true)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    @Column(name = "email")
    var emailId: String? = null

    //adding new column
    //changes to the DB
    @Column(name = "salary")
    private val salary: String? = null

    @Column(name = "department")
    private val department: String? = null

    constructor() : super() {}

    //    public Employee(long id)
    //    {
    //        this.id = id;
    //    }
    constructor(firstName: String?, lastName: String?, emailId: String?) : super() {
        this.firstName = firstName
        this.lastName = lastName
        this.emailId = emailId
    }
}
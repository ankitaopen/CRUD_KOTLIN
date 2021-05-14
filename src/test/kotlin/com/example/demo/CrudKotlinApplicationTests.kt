package com.example.demo

import com.example.demo.model.Employee
import com.example.demo.repository.EmployeeRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation

import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(OrderAnnotation::class)
@SpringBootTest
class CrudKotlinApplicationTests {

//	@Test
//	fun contextLoads() {
//	}
@Autowired
var employeeRepository: EmployeeRepository? = null

	//unit test case to save product details to the database
	//creating test case for create or save operation
	//TEST CASE FOR SAVE OPERATION
	@Test
	@Order(1)
	fun employeeTest() {
		val e = Employee()
		e.id = 1L
		e.firstName = "ankita"
		e.lastName = "sahoo"
		e.emailId = "ankita.sahoo@bankopen.co"
		employeeRepository!!.save(e)
		//with this we are saving the above information to the database
		//using assertNotNull to write the test case
		Assertions.assertNotNull(employeeRepository!!.findById(1L).get())
		//with this we are going to get the employee details with the employee id
		//and check if its not null
		//this becomes true
		//and the test case should run
	}

	//test case for read operation
	//unit test case to read all the employee details from the database
	//TEST CASE FOR READ OPERATION
	@Test
	@Order(2)
	fun readAllTest() {
		val list: List<Employee?> = employeeRepository!!.findAll()
		//currently there are 5 records in our db
		org.assertj.core.api.Assertions.assertThat(list).size().isGreaterThan(0)
	}

	//read single record
	//unit test case to read single employee from database
	@Test
	@Order(3)
	fun singleEmployeeTest() {
		val employee = employeeRepository!!.findById(1L).get()
		//this get() method gives the employee with id 1
		//with the assertEquals() method we can check the:
		//first_name,last_name,email,salary,department
		//enter any of the above value to fetch the respective employee details
		Assertions.assertEquals("sahoo", employee.lastName)
	}

	//TEST CASE FOR UPDATE OPERATION
	@Test
	@Order(4)
	fun updateEmployeeTest() {
		val e = employeeRepository!!.findById(1L).get()
		//retrieving details of employee with id 1
		e.firstName = "nicky"
		employeeRepository!!.save(e)
		Assertions.assertNotEquals("abhilash", employeeRepository!!.findById(1L).get())
		//here the first name is changed to nicky
		//we are checking with assertNotEquals method and passing abhilash as the first_ name
		//with this the method should returns true and the test case will pass
	}

	//TEST CASE FOR DELETE OPERATION
	@Test
	@Order(5)
	fun deleteEmployeeTest() {
		employeeRepository!!.deleteById(1L)
		org.assertj.core.api.Assertions.assertThat(employeeRepository!!.existsById(1L)).isFalse()
		//as id 1 is already deleted
		//isFalse() will return true
	}

}

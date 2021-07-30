package com.te.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.springboot.bean.EmployeeBean;
import com.te.springboot.bean.EmployeeResponse;
import com.te.springboot.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@ApiOperation(value = "get employeee Data" ,produces =  "JSON and  XML" 
		,httpMethod = "GET" ,nickname = "getdata" ,notes = "pass the id as quert string",response = EmployeeResponse.class
			)
	@GetMapping(path = "/getEmp", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getEmp(@ApiParam(required = true) Integer id) {
		EmployeeResponse response = new EmployeeResponse();
		EmployeeBean employeeBean = service.getEmployee(id);
		if (employeeBean != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Employee Data found for id : " + id);
			response.setBean(employeeBean);
		} else {
			response.setStatusCode(404);
			response.setMsg("failure");
			response.setDescription("Employee Data Not found for id : " + id);
		}
		return response;
	}// end of getEmp

	@GetMapping(path = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getEmpAll() {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBean> employeeBeans = service.getAllEmp();
		if (employeeBeans != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Employee Details found");
			response.setEmployeeBeans(employeeBeans);
		} else {
			response.setStatusCode(404);
			response.setMsg("failure");
			response.setDescription("Employee Detials not found");
		}
		return response;
	}// end of getEmp

	@PostMapping(path = "/add", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse addEmp(@RequestBody EmployeeBean bean) {
		EmployeeResponse response = new EmployeeResponse();

		if (service.addEmployee(bean)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Employee Details are added");

		} else {
			response.setStatusCode(404);
			response.setMsg("failure");
			response.setDescription("Something went wrong");
		}
		return response;
	}

	@DeleteMapping(path = "/delete/{emp_id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse delete(@PathVariable(name = "emp_id") int id) {
		EmployeeResponse response = new EmployeeResponse();

		if (service.deleteEmpData(id)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Employee Details are Deleted");

		} else {
			response.setStatusCode(404);
			response.setMsg("failure");
			response.setDescription("Could not Delete");
		}
		return response;
	}

	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse updateEmp(@RequestBody EmployeeBean bean) {
		EmployeeResponse response = new EmployeeResponse();

		if (service.updateEmployee(bean)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Employee Details are Updated");

		} else {
			response.setStatusCode(404);
			response.setMsg("failure");
			response.setDescription("Could not Update");
		}
		return response;
	}

}

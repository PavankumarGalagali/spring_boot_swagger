package com.te.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.springboot.bean.EmployeeResponse;
import com.te.springboot.exp.EmployeeExp;

@RestControllerAdvice
public class EmpployeeControllerAdvice {

	@ExceptionHandler(EmployeeExp.class)
	public EmployeeResponse handleExp(EmployeeExp employeeExp) {
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(500);
		response.setMsg(employeeExp.getMessage());
		return response;
	}
}

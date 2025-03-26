package com.project.microservices.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservices.adminservice.model.ShowReqBody;
import com.project.microservices.adminservice.model.ShowSeatsResponse;
import com.project.microservices.adminservice.service.AdminService;
import com.project.microservices.adminservice.show.entity.ShowEntity;

@RestController
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@PostMapping("/api/addShow")
	public ResponseEntity<ShowEntity> addShow(@RequestBody ShowReqBody showInfo){
		return ResponseEntity.ok(adminService.addShow(showInfo));
	}
	
	@PostMapping("/api/addShowSeatDetails")
	public ResponseEntity<ShowSeatsResponse> addShowSeatDetails(@RequestBody ShowReqBody showInfo){
		return ResponseEntity.ok(adminService.addShowSeatDetails(showInfo));
	}
}


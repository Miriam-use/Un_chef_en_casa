package api.cocinacasa.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.cocinacasa.back.service.DashboardService;

@RestController
@RequestMapping("/ecomove/v0.1/dashboard")
@CrossOrigin(origins= {"*"})
public class DashboardController {
		
	@Autowired
	private DashboardService dashboardService;
	
	Map<String,Object> response=new HashMap<>();
		 
}
package com.example.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Building;
import com.example.demo.repository.BuildingRepository;
import com.example.demo.viewandmodel.BuildingViewModel;


@Controller
public class RegisterBuildingControlle {
	
	@Autowired
	private BuildingRepository buildingRepository;

	@RequestMapping(value = "/register-building", method = RequestMethod.GET)
	private ModelAndView getRegisterPage() {
		
		ModelAndView modelAndView = new ModelAndView("register-building");
		modelAndView.addObject("buildingViewModel", new BuildingViewModel());

		return modelAndView;
	}
	
	@PostMapping("/register-building")
	private String submit(@ModelAttribute BuildingViewModel buildingViewModel) {
		
		Building building = new Building();
		
		building.setName(buildingViewModel.getName());
		building.setAddress(buildingViewModel.getAddress());
		building.setPhone(buildingViewModel.getPhone());
		
		
		buildingRepository.save(building);
		
		return "redirect:/building";
		
	}
}

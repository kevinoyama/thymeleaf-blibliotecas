package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Building;
import com.example.demo.repository.BuildingRepository;

@Controller
public class BuildingsController {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@RequestMapping(value = "/building", method = RequestMethod.GET)
	private ModelAndView index() {
		List<Building> buildingsList = buildingRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("buildings");
		
		modelAndView.addObject("buildings", buildingsList);
		
		return modelAndView;
	}
	
	
}

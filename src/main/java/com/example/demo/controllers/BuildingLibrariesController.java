package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Building;
import com.example.demo.entity.Library;
import com.example.demo.repository.BuildingRepository;
import com.example.demo.repository.LibraryRepository;

@Controller
public class BuildingLibrariesController {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@RequestMapping(value = "/{buildingId}/libraries", method = RequestMethod.GET)
	private ModelAndView index( @PathVariable(name = "buildingId") Long buildingId ) {
		Optional<Building> buildingExist = buildingRepository.findById(buildingId);
		
		if(!buildingExist.isPresent()) {
			return new ModelAndView("buildings");
		}
		
		List<Library> libraries = libraryRepository.findByBuildingId(buildingExist.get().getId());
		
		ModelAndView modelAndView = new ModelAndView("building-libraries");
		
		modelAndView.addObject("building", buildingExist.get());
		modelAndView.addObject("libraries", libraries);
		
		return modelAndView;
	}
	
}

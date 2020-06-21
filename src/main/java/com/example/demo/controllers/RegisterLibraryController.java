package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Building;
import com.example.demo.entity.Library;
import com.example.demo.repository.BuildingRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.viewandmodel.LibraryViewModel;

@Controller
public class RegisterLibraryController {
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private LibraryRepository LibraryRepository;

	@GetMapping("/{buildingId}/register-library")
	public ModelAndView index(@PathVariable(name = "buildingId") Long buildingId ) {
		
		Optional<Building> buildingExist = buildingRepository.findById(buildingId);
		
		ModelAndView modelAndView = new ModelAndView("register-library");
		
		modelAndView.addObject("building", buildingExist.get());
		modelAndView.addObject("libraryViewModel", new LibraryViewModel());
		
		return modelAndView;
		
	}
	
	@PostMapping("/{buildingId}/register-library")
	public String submit(@PathVariable(name = "buildingId") Long buildingId, @ModelAttribute LibraryViewModel libraryViewModel) {
		
		Optional<Building> buildingExist = buildingRepository.findById(buildingId);
		
		Library library = new Library();
		
		library.setName(libraryViewModel.getName());
		library.setRoom(libraryViewModel.getRoom());
		library.setPhoneCode(libraryViewModel.getPhoneCode());
		library.setBuilding(buildingExist.get());
		
		try {
			LibraryRepository.save(library);
		} catch (Error err) {
			System.out.println(err.getMessage());
		}
		

		
		return "redirect:/" + buildingId + "/libraries";
		
	}
}

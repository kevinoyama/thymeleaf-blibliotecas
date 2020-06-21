package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Library;
import com.example.demo.repository.LibraryRepository;

@Controller
public class BooksController {

	@Autowired
	private LibraryRepository libraryRepository;
	
	@GetMapping("/{libraryId}/books")
	public ModelAndView index(@PathVariable(name = "libraryId") Long libraryId) {
		
		Optional<Library> libraryExist = libraryRepository.findById(libraryId);
		
		ModelAndView view = new ModelAndView("books");
		
		view.addObject("library", libraryExist.get());
		view.addObject("books", libraryExist.get().getBooks());
		
		return view;
		
	}
	
}

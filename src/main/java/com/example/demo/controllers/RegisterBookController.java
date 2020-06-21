package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.viewandmodel.BookViewModel;

@Controller
public class RegisterBookController {

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/{libraryId}/register-book")
	public ModelAndView index(@PathVariable("libraryId") Long libraryId) {
		
		Optional<Library> library = libraryRepository.findById(libraryId);
		
		ModelAndView view = new ModelAndView("register-book");
		
		view.addObject("library", library.get());
		view.addObject("request", new BookViewModel());
		
		return view;
		
	}
	
	@PostMapping("/{libraryId}/register-book")
	public String submit(@PathVariable("libraryId") Long libraryId, @ModelAttribute BookViewModel request) {
		
		Optional<Library> library = libraryRepository.findById(libraryId);
		
		Book book = new Book();
		
		book.setTitle(request.getTitle());
		book.setAuthor(request.getAuthor());
		book.setEdition(request.getEdition());
		book.setYear(request.getYear());
		book.setPublishingCompany(request.getPublishingCompany());
		book.setLibrary(library.get());
		
		bookRepository.save(book);
		
		return "redirect:/" + libraryId + "/books";
	}
}

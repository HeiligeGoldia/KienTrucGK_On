package com.se.gk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.gk.entity.Book;
import com.se.gk.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookService serv;
	
	@GetMapping("/book")
	public List<Book> getAll() {
		return serv.findAll();
	}
	
	@PostMapping("/book")
	public Book add(@RequestBody Book book) {
		return serv.save( book);
	}

}

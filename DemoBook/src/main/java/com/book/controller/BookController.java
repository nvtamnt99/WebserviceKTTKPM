package com.book.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.model.Book;

@RestController
@RequestMapping("/book")
public class BookController {
	private List<Book> list = new ArrayList<Book>();

	public BookController() {
		list.add(new Book(1, "ruby", "Onrails", "rubyOnrails", 1000, 12));
		list.add(new Book(2, "Java", "Java", "Java", 1000, 12));
		list.add(new Book(3, "GoLAng", "GoGo", "GoLabg", 1000, 12));
	}

	@GetMapping("")
	public  Object getAllBook() {
		return new ResponseEntity<List<Book>>(list,HttpStatus.OK);
	}

	@PostMapping("")
	public void addBook(@RequestBody Book book) {
		list.add(book);
	}

	@PutMapping("")
	public void editBook(@RequestBody Book book) {
		for (Book item : list) {
			if (item.getId() == book.getId()) {
				item.setDescription(book.getDescription());
				item.setName(book.getName());
				item.setTitle(book.getTitle());
				item.setQuantity(book.getQuantity());
				item.setPrice(book.getPrice());
				return;
			}

		}
	}

	@DeleteMapping("{id}")
	public void remove(@PathVariable("id") int id) {
		for (Book item : list) {
			if (item.getId() == id) {
				list.remove(item);
				return;
			}

		}
	}

	@GetMapping("{id}")
	public Book getBookById(@PathVariable("id") int id) {
		for (Book item : list) {
			if (item.getId() == id) {
				return item;
			} 
		}
		return null;
	}
}

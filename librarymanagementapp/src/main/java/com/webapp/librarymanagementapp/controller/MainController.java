package com.webapp.librarymanagementapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.webapp.librarymanagementapp.entities.Book;
import com.webapp.librarymanagementapp.entities.User;
import com.webapp.librarymanagementapp.service.UserService;
@Controller
public class MainController {
	
	@Autowired

	private UserService userService;
	
	static String userLogged;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String indexHandler() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginHandler(Model m, @RequestParam String username, @RequestParam String password) {
		User user = userService.findByUsernameAndPassword(username, password);
			if(user == null) {
				m.addAttribute("errormsg", "Invalid username or password");
				return "login";
			}
			
			userLogged = username;
			
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public List<Book> dashboardHandler(Model m) {
		
		m.addAttribute("username",userLogged);
		RestTemplate rest= new RestTemplate();
		System.out.println(userLogged);
		//List<Book> bookList = bookService.getBooks();
		List bookList = rest.getForObject("http://localhost:8011/dashboard",List.class );
		m.addAttribute("books", bookList);
		return bookList;
	}
	

	@GetMapping("/addnewbook")
	public String addNewBookHandler(Model m) {
		RestTemplate rest= new RestTemplate();
		List authorList = rest.getForObject("http://localhost:8011/author",List.class );
		System.out.println(authorList);
		m.addAttribute("authors", authorList);
		return "addnewbook";
	}
	
	@PostMapping("/addnewbook")
	public String addNewBookHandler(@RequestParam String book_code,@RequestParam String book_name,@RequestParam String added_date,@RequestParam String author) {
		Book book=new Book();
		book.setBook_code(book_code);
		book.setBook_name(book_name);
		book.setAdded_date(added_date);
		book.setAuthor_name(author);
		RestTemplate rest= new RestTemplate();
		rest.postForLocation("http://localhost:8011/addnewbook", book);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/deletebook/{id}")
	public String deleteBookHandler(@PathVariable int id) {
		RestTemplate rest= new RestTemplate();
		rest.delete("http://localhost:8011/deletebook/"+id);
		
		return "redirect:/dashboard";
	}
	
	
	@GetMapping("/editbook/{id}")
	public String editBookHandler(Model m,@PathVariable int id) {
		RestTemplate rest= new RestTemplate();
		Object editList = rest.getForObject("http://localhost:8011/editbook/"+id,Object.class );
		List authorList = rest.getForObject("http://localhost:8011/author",List.class );
		System.out.println(authorList);
		m.addAttribute("authors", authorList);
		m.addAttribute("book",editList );
		return "editbook";
	}
	
	@PostMapping("/editbook/{id}")
	public String editBookHandler(@PathVariable int id,@RequestParam String book_code,@RequestParam String book_name,@RequestParam String added_date,@RequestParam String author) {
		Book book=new Book();
		book.setBook_code(book_code);
		book.setBook_name(book_name);
		book.setAdded_date(added_date);
		book.setAuthor_name(author);
		RestTemplate rest= new RestTemplate();
		rest.postForLocation("http://localhost:8011/editbook/"+id, book);
		return "redirect:/dashboard";	}
	

	
}

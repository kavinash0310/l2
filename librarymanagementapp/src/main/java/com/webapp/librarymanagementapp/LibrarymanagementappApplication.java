package com.webapp.librarymanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.webapp.librarymanagementapp.dao.UserRepository;


@SpringBootApplication
public class LibrarymanagementappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LibrarymanagementappApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		
	}

}

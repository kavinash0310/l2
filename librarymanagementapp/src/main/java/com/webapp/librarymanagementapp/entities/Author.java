package com.webapp.librarymanagementapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author {
	@Override
	public String toString() {
		return "Author [author_name=" + author_name + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="author_name")
	private String author_name;
	
	public Author() {
		super();
	}

	public Author(String author_name) {
		super();
		this.author_name = author_name;
	}

	public String getauthor_name() {
		return author_name;
	}

	public void setauthor_name(String author_name) {
		this.author_name = author_name;
	}
	
}

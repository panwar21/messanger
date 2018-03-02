package org.shabga.restProj01.messanger.model;

import java.util.Date;

public class Comment {

	
	private long id;
	private String message;
	private String author;
	private Date created;
	
	
	public Comment(long id, String message, String author, Date created) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = created;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}

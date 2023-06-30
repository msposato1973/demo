package com.fsb.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
 

 

public class Game implements Serializable{
	
	private String name;
	private LocalDate creationDate;
    private boolean active;
    
   

	public String getName() {
		return name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	 public Game(String name, LocalDate creationDate, boolean active) {
			super();
			this.name = name;
			this.creationDate = creationDate;
			this.active = active;
	}
	
	public Game() {
		super();
	}
	
	 
	
	
	
	 
	
	
}

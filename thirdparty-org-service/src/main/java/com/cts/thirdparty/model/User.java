package com.cts.thirdparty.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {
	@Id
	private String username;
	private String name;
	private String password;
	private Date date;

	public User() {
		date = new Date();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return username;
		//return new String(Base64Utility.getDecoder().decode(username));
	}

	public void setPassword(String password) {
		this.password = password;//Base64Utility.getEncodedString().encodeToString(password.getBytes());
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

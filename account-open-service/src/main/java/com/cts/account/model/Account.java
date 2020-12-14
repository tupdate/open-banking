package com.cts.account.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Account {
	@Id
	private String accountid;
	private String firstname;
	private String lastname;
	private String address;
	private String accounttype;
	private Date createddate;
	private Date lastactivitydate;
	private String status;
	private String remarks;
	private String emailId;
	private String contactNo;
	private String dob;
	private String nextStepLink;

	public Account() {
		createddate = new Date();
		lastactivitydate = new Date();
	}

	
	public Account(String accountId, String firstName, String lastName, String address, String accountType,
			String status, String emailId, String contactNo, String dob) {
		this();
		this.accountid = accountId;
		this.firstname = firstName;
		this.lastname = lastName;
		this.address = address;
		this.accounttype = accountType;
		this.status = "PENDING";
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.dob = dob;
		this.remarks = "AWAITING DOCUMENTS";
	}


	public String getAccountid() {
		return accountid;
	}


	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAccounttype() {
		return accounttype;
	}


	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}


	public Date getCreateddate() {
		return createddate;
	}


	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}


	public Date getLastactivitydate() {
		return lastactivitydate;
	}


	public void setLastactivitydate(Date lastactivitydate) {
		this.lastactivitydate = lastactivitydate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getNextStepLink() {
		return nextStepLink;
	}


	public void setNextStepLink(String nextStepLink) {
		this.nextStepLink = nextStepLink;
	}
	
	

}

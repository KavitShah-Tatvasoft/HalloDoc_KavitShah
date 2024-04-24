package com.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrower")
public class Borrower {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="borrower_id")
	private Integer borrowerId;
	
	@Column(name="borrower_name")
	private String borrowerName;

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	@Override
	public String toString() {
		return "Borrower [borrowerId=" + borrowerId + ", borrowerName=" + borrowerName + "]";
	}

	public Borrower(Integer borrowerId, String borrowerName) {
		super();
		this.borrowerId = borrowerId;
		this.borrowerName = borrowerName;
	}

	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

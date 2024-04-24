package com.lms.dto;

import java.time.LocalDateTime;

public class AddBookRecordDto {

	private String borrowerName;
	private String bookName;
	private String authorName;
	private String dateOfIssuance;
	private String genere;
	private String city;
	private Integer borrowerId;
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getDateOfIssuance() {
		return dateOfIssuance;
	}
	public void setDateOfIssuance(String dateOfIssuance) {
		this.dateOfIssuance = dateOfIssuance;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}
	@Override
	public String toString() {
		return "AddBookRecordDto [borrowerName=" + borrowerName + ", bookName=" + bookName + ", authorName="
				+ authorName + ", dateOfIssuance=" + dateOfIssuance + ", genere=" + genere + ", city=" + city
				+ ", borrowerId=" + borrowerId + "]";
	}
	public AddBookRecordDto(String borrowerName, String bookName, String authorName, String dateOfIssuance,
			String genere, String city, Integer borrowerId) {
		super();
		this.borrowerName = borrowerName;
		this.bookName = bookName;
		this.authorName = authorName;
		this.dateOfIssuance = dateOfIssuance;
		this.genere = genere;
		this.city = city;
		this.borrowerId = borrowerId;
	}
	public AddBookRecordDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.lms.dto;

public class BookRecordsHomeData {
	
	String bookId;
	String bookName;
	String author;
	String borrowerName;
	String dateOfIssuance;
	String city;
	String genere;
	String bookRecordId;
	
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getDateOfIssuance() {
		return dateOfIssuance;
	}
	public void setDateOfIssuance(String dateOfIssuance) {
		this.dateOfIssuance = dateOfIssuance;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getBookRecordId() {
		return bookRecordId;
	}
	public void setBookRecordId(String bookRecordId) {
		this.bookRecordId = bookRecordId;
	}
	@Override
	public String toString() {
		return "BookRecordsHomeData [bookName=" + bookName + ", author=" + author + ", borrowerName=" + borrowerName
				+ ", dateOfIssuance=" + dateOfIssuance + ", city=" + city + ", genere=" + genere + ", bookRecordId="
				+ bookRecordId + "]";
	}
	public BookRecordsHomeData(String bookName, String author, String borrowerName, String dateOfIssuance, String city,
			String genere, String bookRecordId) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.borrowerName = borrowerName;
		this.dateOfIssuance = dateOfIssuance;
		this.city = city;
		this.genere = genere;
		this.bookRecordId = bookRecordId;
	}
	public BookRecordsHomeData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

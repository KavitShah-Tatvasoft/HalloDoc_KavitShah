package com.lms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book_records")
public class BookRecords {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_record_id")
	private Integer bookRecordId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="borrower_id")
	private Borrower borrower;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="book_id")
	private Book book;
	
	private String city;
	
	@Column(name="date_of_issuance")
	private LocalDate dateOfIssuance;
	
	@Column(name="date_of_return")
	private LocalDateTime dateOfReturn;

	public Integer getBookRecordId() {
		return bookRecordId;
	}

	public void setBookRecordId(Integer bookRecordId) {
		this.bookRecordId = bookRecordId;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDateOfIssuance() {
		return dateOfIssuance;
	}

	public void setDateOfIssuance(LocalDate dateOfIssuance) {
		this.dateOfIssuance = dateOfIssuance;
	}

	public LocalDateTime getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(LocalDateTime dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	@Override
	public String toString() {
		return "BookRecords [bookRecordId=" + bookRecordId + ", borrower=" + borrower + ", book=" + book + ", city="
				+ city + ", dateOfIssuance=" + dateOfIssuance + ", dateOfReturn=" + dateOfReturn + "]";
	}

	public BookRecords(Integer bookRecordId, Borrower borrower, Book book, String city, LocalDate dateOfIssuance,
			LocalDateTime dateOfReturn) {
		super();
		this.bookRecordId = bookRecordId;
		this.borrower = borrower;
		this.book = book;
		this.city = city;
		this.dateOfIssuance = dateOfIssuance;
		this.dateOfReturn = dateOfReturn;
	}

	public BookRecords() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}

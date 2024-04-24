package com.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	
	@Column(name="book_name")
	private String bookName;
	
	@Column(name="book_number")
	private String bookNumber;
	
	@Column(name="book_price")
	private Double bookPrice;
	
//	@Column(name="author")
//	private String author;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;
	
	@Column(name="genere")
	private String genere;
	
	@CreationTimestamp
	@Column(name="added_date")
	private LocalDate addedDate;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public LocalDate getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookNumber=" + bookNumber + ", bookPrice="
				+ bookPrice + ", author=" + author + ", genere=" + genere + ", addedDate=" + addedDate + "]";
	}

	public Book(Integer bookId, String bookName, String bookNumber, Double bookPrice, Author author, String genere,
			LocalDate addedDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookNumber = bookNumber;
		this.bookPrice = bookPrice;
		this.author = author;
		this.genere = genere;
		this.addedDate = addedDate;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

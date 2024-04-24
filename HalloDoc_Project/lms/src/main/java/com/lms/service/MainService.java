package com.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.AddBookRecordDto;
import com.lms.dto.BookRecordsHomeData;
import com.lms.model.Book;
import com.lms.model.BookRecords;
import com.lms.model.Borrower;
import com.lms.repository.BookDao;
import com.lms.repository.BookRecordsDao;
import com.lms.repository.BorrowerDao;

@Service
public class MainService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BorrowerDao borrowerDao;
	
	@Autowired
	private BookRecordsDao bookRecordsDao;
	
	public String getBookAvailbility(String bookName) {
		List<Book> books = this.bookDao.getBookByName(bookName);
		if(books.size()>0) {
			Book book = books.get(0);
			return book.getAuthor().getAuthorName();
		}
		else
		{
			return "false";
		}
	}
	
	
	public int getOldBorrowerId(String name) {
		List<Borrower> borrowers = this.borrowerDao.getOldBorrowerId(name);
		if(borrowers.size()>0) {
			Borrower borrower = borrowers.get(0);
			return borrower.getBorrowerId();
		}else {
			return -1;
		}
	}
	
	public void addBookRecord(AddBookRecordDto addBookRecordDto) {
		
		BookRecords bookRecords = new BookRecords();
		List<Book> books = this.bookDao.getBookByName(addBookRecordDto.getBookName());
		Book book = books.get(0);
		int newBorrowerId = 0;
		
		if(addBookRecordDto.getBorrowerId() == -1) {
			Borrower borrower = new Borrower();
			borrower.setBorrowerName(addBookRecordDto.getBorrowerName());
			newBorrowerId = this.borrowerDao.addBorrower(borrower);
			bookRecords.setBorrower(borrower);
		}else {
			List<Borrower> borrowers = this.borrowerDao.getOldBorrowerId(addBookRecordDto.getBorrowerName());
			Borrower borrower = borrowers.get(0);
			bookRecords.setBorrower(borrower);
		}
		LocalDate date = LocalDate.parse(addBookRecordDto.getDateOfIssuance());
		bookRecords.setBook(book);
		bookRecords.setCity(addBookRecordDto.getCity());
		bookRecords.setDateOfIssuance(date);
		
		int bookRecordsId = (Integer)this.bookRecordsDao.addBookRecordEntry(bookRecords);
		
		
	}
	
	public List<BookRecordsHomeData> getHomeData(){
		List<BookRecords> records = this.bookRecordsDao.getAllData();
		List<BookRecordsHomeData> list = new ArrayList<BookRecordsHomeData>();		
		
		for (BookRecords record : records) {
			BookRecordsHomeData data = new BookRecordsHomeData();
			data.setAuthor(record.getBook().getAuthor().getAuthorName());
			data.setBookName(record.getBook().getBookName());
			data.setBorrowerName(record.getBorrower().getBorrowerName());
			data.setCity(record.getCity());
			data.setDateOfIssuance(record.getDateOfIssuance().toString());
			data.setGenere(record.getBook().getGenere());
			data.setBookRecordId(record.getBookRecordId().toString());
			data.setBookId(record.getBook().getBookId().toString());
			list.add(data);
		}
		
		return list;
	}
	
	public BookRecordsHomeData getUpdateData(int id) {
		BookRecords record = this.bookRecordsDao.getBookRecordData(id);
		BookRecordsHomeData data = new BookRecordsHomeData();
		data.setAuthor(record.getBook().getAuthor().getAuthorName());
		data.setBookName(record.getBook().getBookName());
		data.setBorrowerName(record.getBorrower().getBorrowerName());
		data.setCity(record.getCity());
		data.setDateOfIssuance(record.getDateOfIssuance().toString());
		data.setGenere(record.getBook().getGenere());
		data.setBookRecordId(record.getBookRecordId().toString());
		data.setBookId(record.getBook().getBookId().toString());
		
		return data;
	}

}

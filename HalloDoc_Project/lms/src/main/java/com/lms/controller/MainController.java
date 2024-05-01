package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lms.dto.AddBookRecordDto;
import com.lms.dto.BookRecordsHomeData;
import com.lms.model.Book;
import com.lms.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/home")
	public String home(Model m) {
		List<BookRecordsHomeData> datas = this.mainService.getHomeData();
		m.addAttribute("initailData",datas);
		return "lms-home";
	}
	
	@ResponseBody
	@RequestMapping(value="/getBookAvaibility", method = RequestMethod.POST)
	public String getBookAvaibility(@RequestParam("bookName") String bookName) {
		String status = this.mainService.getBookAvailbility(bookName);
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOldBorrower", method = RequestMethod.POST)
	public int getOldBorrower(@RequestParam("name") String name) {
		int id = this.mainService.getOldBorrowerId(name);
		return id;
	}
	
	@ResponseBody
	@RequestMapping(value="/addBookRecord", method = RequestMethod.POST)
	public String addBookRecord(AddBookRecordDto addBookRecordDto) {
		 ;
		this.mainService.addBookRecord(addBookRecordDto);
		return "done";
	
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/updateTheRecord", method = RequestMethod.POST )
	public String updateTheRecord(AddBookRecordDto addBookRecordDto) {
		this.mainService.updateTheRecord(addBookRecordDto);
		return "updated";
	}
	
	@ResponseBody
	@RequestMapping(value="/getBookRecordData", method = RequestMethod.POST)
	public List<BookRecordsHomeData> getBookRecordData() {
		List<BookRecordsHomeData> datas = this.mainService.getHomeData();
		return datas;
	}
	
	@ResponseBody
	@RequestMapping(value="/getUpdateData", method = RequestMethod.POST)
	public BookRecordsHomeData getUpdateData(@RequestParam("id") int id) {
		BookRecordsHomeData bookRecordsHomeData = this.mainService.getUpdateData( id);
		return bookRecordsHomeData;
	}
}

package hallodoc.helper;

import java.util.*;

public class DateHelper {
	
	public static String getMonthName(int month) {
		String[] months = {"January","February","March","April","May","June","July", "August", "September","October","November","December"};
		
		return months[month-1];
	}
	
	public static String getDateStrigSepratedBySpace(Date date) {
		
		// Input 2003-01-15
		//Output January 15, 2003
		
		String[] tokens = date.toString().split(" ")[0].split("-");
		String year = tokens[0];
		String day = tokens[2];
		String month = getMonthName(Integer.parseInt(tokens[1]));
		
		return month + " " + day + ", " + year;
	}

}

package hallodoc.helper;

public class DateHelper {
	
	public static String getMonthName(int month) {
		String[] months = {"January","February","March","April","May","June","July", "August", "September","October","November","December"};
		
		return months[month-1];
	}

}

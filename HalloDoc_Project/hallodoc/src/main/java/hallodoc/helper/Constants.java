package hallodoc.helper;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Constants {
	
	public static final String CONTEXT_PATH = "/hallodoc";
	
	public static final String ACCOUNT_SID = "ACe8a35d6938d4380a6b3ec5fe8f6a74e4";
	
	public static final String AUTH_TOKEN = "33c746896cc4590677665324b9b3a6b1";
	
	public static final String TWILIO_NUMBER = "+16562269725";
	
	public static final String getUplaodPath(HttpSession session) {
		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "fileuploads" + File.separator + "patient" + File.separator;
		
		return path;
	}

}

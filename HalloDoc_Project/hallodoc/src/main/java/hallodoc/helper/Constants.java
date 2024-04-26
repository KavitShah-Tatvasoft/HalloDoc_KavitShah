package hallodoc.helper;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Constants {
	
	public static final String CONTEXT_PATH = "/hallodoc";
	
	public static final String ACCOUNT_SID = "ACe8a35d6938d4380a6b3ec5fe8f6a74e4";
	
	public static final String AUTH_TOKEN = "e68e4e1004ee10e2729eb1a86fca712f";
	
	public static final String TWILIO_NUMBER = "+16562269725";
	
	public static final String getUplaodPath(HttpSession session) {
		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "fileuploads" + File.separator + "patient" + File.separator;
		
		return path;
	}
	
	
	public static final String getProviderUplaodPath(HttpSession session) {
		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "fileuploads" + File.separator + "provider" + File.separator;
		
		return path;
	}

}

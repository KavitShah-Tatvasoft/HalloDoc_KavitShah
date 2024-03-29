package hallodoc.helper;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Constants {
	
	public static final String CONTEXT_PATH = "/hallodoc";
	
	public static final String getUplaodPath(HttpSession session) {
		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "fileuploads" + File.separator + "patient" + File.separator;
		
		return path;
	}

}

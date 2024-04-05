package hallodoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import hallodoc.model.Request;
import hallodoc.repository.RequestDao;



@Service
public class AdminService {
	
	@Autowired
	private RequestDao requestDao;
	
	public List<Request> getNewStatusRequests(int id){
		List<Request> requestData = requestDao.getRequstStatusData(id);
		return requestData;
	}
	
}

package hallodoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hallodoc.repository.AspNetUserDao;

@Service
public class PatientRequestsService {
	
	@Autowired
	AspNetUserDao apsnetuserdao;

	public String isPatientAvailable(String email) {
		
		String isValid = apsnetuserdao.isUserPresent(email);
		return isValid;
	}
}

package hallodoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.model.AspNetUsers;
import hallodoc.repository.AspNetUserDao;

@Service
public class UserService {

	@Autowired
	AspNetUserDao apsnetuserdao;
	
	public int validateUser(String username, String password) {

		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);

		if (list.isEmpty()) {
			System.out.println("No such user found");
			return -1;
		}

		else {
			AspNetUsers user = list.get(0);
			String passwordHash = user.getPassword_hash();
			
			if(passwordHash==null)
			{
				return -2;
			}
			else {
				BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
				int role = user.getUser().getAspNetRoles().getId();
				boolean verified = Password.check(password, passwordHash).with(bcrypt);
				
				if (verified) {
					return role;
				} else {
					return -3;
				}
				
				
			}
			
			
			

		}
	}

}

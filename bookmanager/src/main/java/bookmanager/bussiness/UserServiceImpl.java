package bookmanager.bussiness;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import bookmanager.dao.IUserDao;
import bookmanager.model.User;

public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean userLogin(String userName, String password) {
		try {
			User user = userDao.loadUserByUserName(userName);
			if(user == null)
				return false;
			return passwordEncoder.matches(password, user.getPassword());
		} catch (SQLException e) {
			return false;
		}
	}

}

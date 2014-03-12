package bookmanager.dao;

import java.sql.SQLException;

import bookmanager.model.User;

public interface IUserDao {
	User loadUserByUserName(String userName) throws SQLException;
}

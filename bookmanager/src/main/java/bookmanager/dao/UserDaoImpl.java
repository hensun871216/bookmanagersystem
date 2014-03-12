package bookmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bookmanager.model.User;
import bookmanager.util.IJDBCHelper;
import bookmanager.util.MyRowMapper;

public class UserDaoImpl implements IUserDao {
	@Autowired
	IJDBCHelper ijdbcHelper;

	public User loadUserByUserName(String userName) throws SQLException {
		String sql = "select username, password from tb_user_info where username=?";
		List<User> users = ijdbcHelper.executeQuery(sql, new Object[]{userName}, new MyRowMapper<User>() {
			public User mapRow(ResultSet rs) {
				try {
					if(rs.next()) {
						User user = new User();
						user.setUserName(rs.getString(1));
						user.setPassword(rs.getString(2));
						return user;
					}
					return null;
				} catch (SQLException e) {
					return null;
				}
				
			}
		});
		
		if(users.size() != 1)
			return null;
		return users.get(0);
	}
}

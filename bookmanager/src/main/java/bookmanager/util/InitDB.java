package bookmanager.util;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class InitDB {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	IJDBCHelper ijdbcHelper;
	
	private String username = "admin";
	private String password = "123456";
	
	public void initDb() {
		createTables();
		String sql = "insert into tb_user_info(userName, password) values(?, ?)";
		try {
			String encryptPassword = passwordEncoder.encode(password);
			ijdbcHelper.update(sql, new Object[]{username, encryptPassword});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanTables() {
		String sql = "drop table if exists tb_user_info;";
		try {
			ijdbcHelper.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		cleanTables();
		String sql = "create table tb_user_info("
				+ "userid int auto_increment primary key, username varchar(15) not null, password varchar(300) not null);";
		try {
			ijdbcHelper.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

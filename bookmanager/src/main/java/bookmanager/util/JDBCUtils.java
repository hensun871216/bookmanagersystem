package bookmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class JDBCUtils implements IJDBCHelper {
	private static String dbUserName = "root";
	private static String dbPassword = "871216";
	private static String dbUrl = "jdbc:mysql://127.0.0.1/test";
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static Connection connection;
	private static Logger logger = Logger.getLogger(JDBCUtils.class);

	private Connection getConnection() {
		logger.info("open the connect");
		if (connection == null) {
			try {
				Class.forName(driverClass);
				connection = DriverManager.getConnection(dbUrl, dbUserName,
						dbPassword);
			} catch (Exception e) {
				logger.error("Can't open the connect!");
			}
		}
		logger.info("open the connect successfully!");
		return connection;
	}

	private PreparedStatement getPreparedStatement(String sql, Object[] params) {
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			int index = 1;
			logger.info("excute sql : " + sql + " objects :" + params);
			for (Object param : params) {
				preparedStatement.setObject(index, param);
				index++;
			}
			return preparedStatement;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("create preparedStatement failed!!");
			return null;
		}
	}

	public <T> List<T> executeQuery(String sql, Object[] params,
			MyRowMapper<T> rowMapper) throws SQLException {
		List<T> resultList = new ArrayList<T>();
		try {

			PreparedStatement preparedStatement = getPreparedStatement(sql,
					params);
			ResultSet rs = preparedStatement.executeQuery();
			T resultObeject = rowMapper.mapRow(rs);
			if (resultObeject != null)
				resultList.add(resultObeject);
			return resultList;
		} catch (SQLException e) {
			logger.warn("excute query failed!!");
			throw e;
		}

	}

	public boolean update(String sql) throws SQLException {
		return update(sql, new Object[0]);
	}

	public boolean update(String sql, Object[] params) throws SQLException {
		try {
			PreparedStatement preparedStatement = getPreparedStatement(sql, params);
			return preparedStatement.execute();
		} catch (SQLException e) {
			logger.warn("excute query failed!!");
			throw e;
		}
	}
}

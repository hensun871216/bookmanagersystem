package bookmanager.util;

import java.sql.SQLException;
import java.util.List;

public interface IJDBCHelper {
	<T> List<T> executeQuery(String sql, Object[] params, MyRowMapper<T> rowMapper) throws SQLException;
	boolean update(String sql) throws SQLException;
	boolean update(String sql, Object[] params) throws SQLException;
}

package bookmanager.util;

import java.sql.ResultSet;

public interface MyRowMapper<T> {
	T mapRow(ResultSet rs) ;
}

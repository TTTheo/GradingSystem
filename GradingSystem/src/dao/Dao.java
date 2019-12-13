package dao;

import java.sql.*;
import java.util.ArrayList;

public abstract class Dao<T> {
	final String url = "jdbc:sqlite:./grader.db";

	// Calls executeUpdate in a try-with-resources statement 
	// Automatically closes connections and statements
	// Returns the auto generated ID by the DB (can ignore if not needed)
	public int executeUpdate(String query) throws SQLException {
		try (
			Connection conn = getConnection();
			Statement st = conn.createStatement();
		) {
			st.executeUpdate(query);
//			https://stackoverflow.com/a/7357118/10634812
			ResultSet rs = st.getGeneratedKeys();
			return rs.getInt("last_insert_rowid()");
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}

	public abstract ArrayList<T> executeQuery(String query) throws SQLException;
	public abstract void insert(T t) throws SQLException;
}

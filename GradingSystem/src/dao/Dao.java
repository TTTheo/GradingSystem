package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Dao<T> {
	final String url = "jdbc:sqlite:grader.db";
	private Connection conn;

	// Calls executeUpdate in a try-with-resources statement 
	// Automatically closes connections and statements
	public void executeUpdate(String query) throws SQLException {
		try (
			Connection conn = getConnection();
			Statement st = conn.createStatement();
		) {
			st.executeUpdate(query);
		}
	}

	public abstract ArrayList<T> executeQuery(String query) throws SQLException;

	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}

	// public abstract boolean insert(T t);
	// public abstract boolean delete(String s);
	// public abstract boolean update(T t);
	// public abstract T select(String s);
}

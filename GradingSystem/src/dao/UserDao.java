package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.User;

public class UserDao extends Dao<User> {

	// Calls executeQuery in a try-with-resources statement
	// Source: https://stackoverflow.com/a/14853625
	@Override
	public ArrayList<User> executeQuery(String query) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		try (
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		) {
			while (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				users.add(u);
			}
		}
		return users;
	}

	// Gets all users with username = user, and password = pass (should just be one).
	public ArrayList<User> getUsersByLogin(String user, String pass) throws SQLException {
		String query = String.format(
			"SELECT * FROM User WHERE User.username = '%s' AND User.password = '%s'",
			user,
			pass
		);

		return executeQuery(query);
	}

	// Gets users by username
	public ArrayList<User> getUsersByName(String username) throws SQLException {
		String query = String.format(
				"SELECT * FROM User WHERE User.username = '%s'",
				username
		);

		return executeQuery(query);
	}

	public void insert(User user) throws SQLException {
		String query = String.format(
				"INSERT INTO User (username, password) VALUES ('%s', '%s')",
				user.getUsername(),
				user.getPassword()
		);

		int uid = executeUpdate(query);
		user.setUid(uid);
	}
}

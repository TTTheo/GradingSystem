package backend;

import java.util.ArrayList;
import java.sql.SQLException;

import dao.UserDao;
import objects.User;

public class UserBackend {
	UserDao dao = new UserDao();

	// Returns the User if username and password were correct
	// else returns null if no such user exists
	// Throws exception if failure in the DB
	public User loginUser(String user, String pass) throws SQLException {
		ArrayList<User> userList = dao.getUsersByLogin(user, pass);

		if (userList.size() == 0) {
			// No such user exists
			return null;
		} else if (userList.size() > 1) {
			System.out.println("Warning: multiple users with same login");
		}

		User existingUser = userList.get(0);
		return existingUser;
	}
}

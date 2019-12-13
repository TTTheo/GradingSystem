package objects;

public class User {
	private int uid;
	private String username;
	private String password;

	public User() {
		username = "";
		password = "";
	}

	public User(String user, String pass) {
		username = user;
		password = pass;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String u) {
		username = u;
	}

	public void setPassword(String p) {
		password = p;
	}
}

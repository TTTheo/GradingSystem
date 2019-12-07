package objects;

public class User {
	public static int courseidCount = 1 ;
	public static int cidCount = 1 ;
	public static int sidCount = 1 ;
	public static int pidCount = 1 ;
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

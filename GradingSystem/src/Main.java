import gui.*;
import gui.grade.*;
import backend.*;
import objects.*;
import dao.*;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		// Skip the login frame it is easier to grade and run
		SemesterFrame login = new SemesterFrame(new Backend());
		login.setLocationRelativeTo(null);
		login.setVisible(true);
	}
}

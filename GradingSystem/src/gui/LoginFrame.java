package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import backend.UserBackend;
import objects.User;
import java.awt.Font;

// Reuses parts of ATM's Login class
public class LoginFrame extends JFrame implements FrameActions {
	private JPanel contentPane;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel signupLabel;
	private JLabel welcomeLabel ;
	private JTextField username;
	private JPasswordField password;
	private JButton loginBtn;
	private JButton signupBtn;

	// userBackend exposes functions that we can call.
	private UserBackend userBackend;
	
	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 320);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create buttons and labels
		usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(71, 100, 74, 32);
		contentPane.add(usernameLabel);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(71, 144, 74, 32);
		contentPane.add(passwordLabel);

		signupLabel = new JLabel("Don't have an account?");
		signupLabel.setBounds(257, 204, 200, 15);
		contentPane.add(signupLabel);

		username = new JTextField();
		username.setBounds(157, 100, 198, 32);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(157, 144, 198, 32);
		contentPane.add(password);

		loginBtn = new JButton("Login");
		loginBtn.setBounds(89, 222, 116, 23);
		contentPane.add(loginBtn);

		signupBtn = new JButton("Sign up");
		signupBtn.setBounds(278, 222, 116, 23);
		contentPane.add(signupBtn);
		
		welcomeLabel = new JLabel("Welcom to Grading System");
		welcomeLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		welcomeLabel.setBounds(112, 31, 251, 57);
		contentPane.add(welcomeLabel);
		userBackend = new UserBackend();

		// Define button handlers
		addActions();
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(this, message);
    }

	// Open the semester frame next
	public void openNext() {
		SemesterFrame next = new SemesterFrame();
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}

	// Use userBackend as an abstraction. We don't directly look up into
	// the db or authenticate, just call the backend function "loginUser()"
	// Handle any exceptions that the backend may throw
	public void addActions() {
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String pass = String.valueOf(password.getPassword());
				try {
					User login = userBackend.loginUser(user, pass);
					if (login == null) {
						alert("Invalid login, try again");
					} else {
					    // Login was successful
						openNext();
					}
				} catch (SQLException ex) {
					//FrameActions.alert(ex.toString());
					alert(ex.toString());
				}
			}
		}); 

		// This function should get and validate user input
		// then call the backend eg: userBackend.signup(user, pass);
		signupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String pass = String.valueOf(password.getPassword());
				try {
					User newUser = userBackend.signUp(user, pass);
					if (newUser == null) {
						alert("Username " + user + " already exists!");
					} else {
						alert("Successfully signed up");
						openNext();
					}
				} catch (SQLException ex) {
				    alert(ex.toString());
				}
			}
		}); 
	}
}
